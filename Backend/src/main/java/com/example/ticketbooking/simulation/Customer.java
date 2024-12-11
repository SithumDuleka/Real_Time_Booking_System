package com.example.ticketbooking.simulation;

import com.example.ticketbooking.model.TicketPool;

public class Customer implements Runnable {
    private final TicketPool ticketPool;
    private final String customerName;
    private final int retrievalRate;

    public Customer(TicketPool ticketPool, String customerName, int retrievalRate) {
        this.ticketPool = ticketPool;
        this.customerName = customerName;
        this.retrievalRate = retrievalRate;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                ticketPool.purchaseTickets(customerName);
                Thread.sleep(retrievalRate*1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println(customerName + " thread interrupted.");
                break;
            }
        }
    }
}