package com.example.ticketbooking.dto;

/**
 * Represents the configuration for a ticket booking simulation.
 *
 * @author [Your Name]
 * @version 1.0
 */
public class Configuration {

    /**
     * Total number of tickets available for the simulation.
     */
    private int totalTickets;

    /**
     * Rate at which tickets are released to the pool.
     */
    private int releaseRate;

    /**
     * Rate at which customers retrieve tickets from the pool.
     */
    private int customerRetrievalRate;

    /**
     * Maximum capacity of the ticket pool.
     */
    private int maxTicketCapacity;

    // Getters and Setters

    /**
     * Gets the total number of tickets.
     *
     * @return Total number of tickets.
     */
    public int getTotalTickets() {
        return totalTickets;
    }

    /**
     * Sets the total number of tickets.
     *
     * @param totalTickets Total number of tickets to set.
     */
    public void setTotalTickets(int totalTickets) {
        this.totalTickets = totalTickets;
    }

    /**
     * Gets the ticket release rate.
     *
     * @return Ticket release rate.
     */
    public int getReleaseRate() {
        return releaseRate;
    }

    /**
     * Sets the ticket release rate.
     *
     * @param releaseRate Ticket release rate to set.
     */
    public void setReleaseRate(int releaseRate) {
        this.releaseRate = releaseRate;
    }

    /**
     * Gets the customer retrieval rate.
     *
     * @return Customer retrieval rate.
     */
    public int getCustomerRetrievalRate() {
        return customerRetrievalRate;
    }

    /**
     * Sets the customer retrieval rate.
     *
     * @param customerRetrievalRate Customer retrieval rate to set.
     */
    public void setCustomerRetrievalRate(int customerRetrievalRate) {
        this.customerRetrievalRate = customerRetrievalRate;
    }

    /**
     * Gets the maximum ticket capacity.
     *
     * @return Maximum ticket capacity.
     */
    public int getMaxTicketCapacity() {
        return maxTicketCapacity;
    }

    /**
     * Sets the maximum ticket capacity.
     *
     * @param maxTicketCapacity Maximum ticket capacity to set.
     */
    public void setMaxTicketCapacity(int maxTicketCapacity) {
        this.maxTicketCapacity = maxTicketCapacity;
    }

    /**
     * Returns a string representation of the configuration object.
     *
     * @return String representation of the configuration.
     */
    @Override
    public String toString() {
        return "Configuration{" +
                "totalTickets=" + totalTickets +
                ", releaseRate=" + releaseRate +
                ", customerRetrievalRate=" + customerRetrievalRate +
                ", maxTicketCapacity=" + maxTicketCapacity +
                '}';
    }
}