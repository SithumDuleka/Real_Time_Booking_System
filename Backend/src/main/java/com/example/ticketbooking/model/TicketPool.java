package com.example.ticketbooking.model;

import com.example.ticketbooking.websocket.ActivityWebSocketHandler;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Random;

@Component
public class TicketPool {
    private final ArrayList<Integer> pool = new ArrayList<>();
    private int totalTickets;
    private int maxCapacity;
    private int totalSoldTickets;
    private int totalAddedTickets;
    private boolean isTerminated;
    private final ActivityWebSocketHandler webSocketController;

    public TicketPool(ActivityWebSocketHandler webSocketController) {
        this.webSocketController = webSocketController;
    }


    public synchronized void initialize(int totalTickets, int maxCapacity) {
        this.totalTickets = totalTickets;
        this.maxCapacity = maxCapacity;
        this.isTerminated = false;
        this.totalSoldTickets = 0;
        this.totalAddedTickets = 0;
    }

    public synchronized void addTickets(String vendorName) {
        Random randomnum=new Random();
        if (isTerminated() || totalAddedTickets >= totalTickets) {
            return;
        }
        while(pool.size()>=maxCapacity){
            try {
                System.out.println("Ticket Pool is full" + vendorName + " waiting");
                webSocketController.broadcastMessage("Ticket Pool is full" + vendorName + " waiting");
                wait();
            }catch (InterruptedException e){

            }
        }

        int remainingCapacity = totalTickets - totalAddedTickets;
        int max= randomnum.nextInt(10)+1;
        int ticketsToAdd = Math.min(remainingCapacity, max); // Simulate 10 tickets per vendor cycle
        totalAddedTickets += ticketsToAdd;
        for (int i = 0; i < ticketsToAdd; i++){
            pool.add(1);
        }


        System.out.println(vendorName + " added " + ticketsToAdd + " tickets. Pool size: " + pool.size());
        String logMessage=vendorName + " added " + ticketsToAdd + " tickets. Pool size: " + pool.size();
        webSocketController.broadcastMessage(logMessage);
        if (totalAddedTickets >= totalTickets) {
            System.out.println("Reach the ticket Limit !!!!");
        }

        notifyAll();
    }

    public synchronized void purchaseTickets(String customerName) {
        if (isTerminated() || totalSoldTickets >= totalTickets){
            return;
        }

        while (pool.isEmpty() || Math.min(5, totalTickets - totalSoldTickets) > pool.size()) {
            try {
                System.out.println("No tickets Available..." + customerName + " is waiting");
                wait();

            } catch (InterruptedException e) {
            }
        }

        int ticketsToBuy = Math.min(5,totalTickets-totalSoldTickets); // Simulate 5 tickets per customer cycle
        totalSoldTickets += ticketsToBuy;
        for (int i = 0; i < ticketsToBuy; i++) {
            pool.remove(0);
        }


        System.out.println(customerName + " purchased " + ticketsToBuy + " tickets. Total sold: " + totalSoldTickets);
        String logMessage2=customerName + " purchased " + ticketsToBuy + " tickets. Total sold: " + totalSoldTickets;
        webSocketController.broadcastMessage(logMessage2);
        if (totalSoldTickets >= totalTickets) {
            isTerminated = true;
            System.out.println("All tickets sold");
        }

        notifyAll();
    }
    public int getTotalSoldTickets(){
        return totalSoldTickets;
    }

    public synchronized boolean isTerminated() {
        return isTerminated;
    }
}