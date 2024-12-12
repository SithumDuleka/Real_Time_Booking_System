package com.example.ticketbooking.simulation;

import com.example.ticketbooking.model.TicketPool;

/**
 * Represents a vendor who adds tickets to the pool.
 */
public class Vendor implements Runnable {

    /**
     * Reference to the ticket pool.
     */
    private final TicketPool ticketPool;

    /**
     * Name of the vendor.
     */
    private final String vendorName;

    /**
     * Ticket release rate of the vendor in milliseconds.
     */
    private final int ticketReleaseRate;

    /**
     * Constructs a new Vendor object.
     *
     * @param ticketPool       The ticket pool.
     * @param vendorName      The name of the vendor.
     * @param ticketReleaseRate The ticket release rate of the vendor.
     */
    public Vendor(TicketPool ticketPool, String vendorName, int ticketReleaseRate) {
        this.ticketPool = ticketPool;
        this.vendorName = vendorName;
        this.ticketReleaseRate = ticketReleaseRate;
    }

    /**
     * Runs the vendor's behavior, which involves adding tickets to the pool at a specified rate.
     */
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                ticketPool.addTickets(vendorName);
                Thread.sleep(ticketReleaseRate * 1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println(vendorName + " thread interrupted.");
                break;
            }
        }
    }
}