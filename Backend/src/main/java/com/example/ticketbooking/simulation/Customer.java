package com.example.ticketbooking.simulation;

import com.example.ticketbooking.model.TicketPool;

/**
 * Represents a customer who attempts to purchase tickets from the pool.
 */
public class Customer implements Runnable {

    /**
     * Reference to the ticket pool.
     */
    private final TicketPool ticketPool;

    /**
     * Name of the customer.
     */
    private final String customerName;

    /**
     * Retrieval rate of the customer in milliseconds.
     */
    private final int retrievalRate;

    /**
     * Constructs a new Customer object.
     *
     * @param ticketPool      The ticket pool.
     * @param customerName   The name of the customer.
     * @param retrievalRate  The retrieval rate of the customer.
     */
    public Customer(TicketPool ticketPool, String customerName, int retrievalRate) {
        this.ticketPool = ticketPool;
        this.customerName = customerName;
        this.retrievalRate = retrievalRate;
    }

    /**
     * Runs the customer's behavior, which involves attempting to purchase tickets from the pool at a specified rate.
     */
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                ticketPool.purchaseTickets(customerName);
                Thread.sleep(retrievalRate * 1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println(customerName + " thread interrupted.");
                break;
            }
        }
    }
}