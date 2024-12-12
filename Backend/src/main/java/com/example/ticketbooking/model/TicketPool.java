package com.example.ticketbooking.model;

import com.example.ticketbooking.websocket.ActivityWebSocketHandler;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Random;

/**
 * Represents a pool of tickets that can be added and removed.
 */
@Component
public class TicketPool {

    /**
     * The underlying list to store the tickets.
     */
    private final ArrayList<Integer> pool = new ArrayList<>();

    /**
     * Total number of tickets to be added to the pool.
     */
    private int totalTickets;

    /**
     * Maximum capacity of the ticket pool.
     */
    private int maxCapacity;

    /**
     * Total number of tickets sold.
     */
    private int totalSoldTickets;

    /**
     * Total number of tickets added to the pool.
     */
    private int totalAddedTickets;

    /**
     * Flag indicating whether the pool is terminated.
     */
    private boolean isTerminated;

    /**
     * WebSocket controller for broadcasting messages.
     */
    private final ActivityWebSocketHandler webSocketController;

    /**
     * Constructs a new TicketPool instance.
     *
     * @param webSocketController WebSocket controller for broadcasting messages.
     */
    public TicketPool(ActivityWebSocketHandler webSocketController) {
        this.webSocketController = webSocketController;
    }

    /**
     * Initializes the ticket pool with the given total tickets and maximum capacity.
     *
     * @param totalTickets  Total number of tickets to be added.
     * @param maxCapacity   Maximum capacity of the pool.
     */
    public synchronized void initialize(int totalTickets, int maxCapacity) {
        this.totalTickets = totalTickets;
        this.maxCapacity = maxCapacity;
        this.isTerminated = false;
        this.totalSoldTickets = 0;
        this.totalAddedTickets = 0;
    }

    /**
     * Adds tickets to the pool, simulating vendor activity.
     *
     * @param vendorName Name of the vendor adding the tickets.
     */
    public synchronized void addTickets(String vendorName) {
        if (isTerminated() || totalAddedTickets >= totalTickets) {
            return;
        }

        while (pool.size() >= maxCapacity) {
            try {
                System.out.println("Ticket Pool is full, " + vendorName + " waiting");
                webSocketController.broadcastMessage("Ticket Pool is full, " + vendorName + " waiting");
                wait();
            } catch (InterruptedException e) {
                // Handle the interruption
            }
        }

        Random random = new Random();
        int ticketsToAdd = Math.min(totalTickets - totalAddedTickets, random.nextInt(10) + 1); // Simulate up to 10 tickets per vendor cycle
        totalAddedTickets += ticketsToAdd;

        for (int i = 0; i < ticketsToAdd; i++) {
            pool.add(1);
        }

        System.out.println(vendorName + " added " + ticketsToAdd + " tickets. Pool size: " + pool.size());
        webSocketController.broadcastMessage(vendorName + " added " + ticketsToAdd + " tickets. Pool size: " + pool.size());

        if (totalAddedTickets >= totalTickets) {
            System.out.println("Reached the ticket limit!");
        }

        notifyAll();
    }

    /**
     * Removes tickets from the pool, simulating customer purchases.
     *
     * @param customerName Name of the customer purchasing the tickets.
     */
    public synchronized void purchaseTickets(String customerName) {
        if (isTerminated() || totalSoldTickets >= totalTickets) {
            return;
        }

        while (pool.isEmpty() || Math.min(5, totalTickets - totalSoldTickets) > pool.size()) {
            try {
                System.out.println("No tickets available, " + customerName + " is waiting");
                wait();
            } catch (InterruptedException e) {
                // Handle the interruption
            }
        }

        int ticketsToBuy = Math.min(5, totalTickets - totalSoldTickets); // Simulate up to 5 tickets per customer cycle
        totalSoldTickets += ticketsToBuy;

        for (int i = 0; i < ticketsToBuy; i++) {
            pool.remove(0);
        }

        System.out.println(customerName + " purchased " + ticketsToBuy + " tickets. Total sold: " + totalSoldTickets);
        webSocketController.broadcastMessage(customerName + " purchased " + ticketsToBuy + " tickets. Total sold: " + totalSoldTickets);

        if (totalSoldTickets >= totalTickets) {
            isTerminated = true;
            System.out.println("All tickets sold");
        }

        notifyAll();
    }

    /**
     * Gets the total number of tickets sold.
     *
     * @return Total number of tickets sold.
     */
    public int getTotalSoldTickets() {
        return totalSoldTickets;
    }

    /**
     * Checks if the ticket pool is terminated.
     *
     * @return True if the pool is terminated, false otherwise.
     */
    public synchronized boolean isTerminated() {
        return isTerminated;
    }
}