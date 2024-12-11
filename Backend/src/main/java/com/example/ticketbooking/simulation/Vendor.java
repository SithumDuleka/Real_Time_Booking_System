package com.example.ticketbooking.simulation;

import com.example.ticketbooking.model.TicketPool;

public class Vendor implements Runnable {
    private  TicketPool ticketPool;
    private  String vendorName;
    private  int ticketReleaseRate;

    public Vendor(TicketPool ticketPool, String vendorName, int ticketReleaseRate) {
        this.ticketPool = ticketPool;
        this.vendorName = vendorName;
        this.ticketReleaseRate = ticketReleaseRate;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                ticketPool.addTickets(vendorName);
                Thread.sleep(ticketReleaseRate*1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println(vendorName + " thread interrupted.");
                break;
            }
        }
    }
}