package com.example.ticketbooking.model;

import java.math.BigDecimal;

/**
 * Represents a ticket for an event.
 */
public class Ticket {

    /**
     * Unique identifier for the ticket.
     */
    private int ticketID;

    /**
     * Name of the event associated with the ticket.
     */
    private String eventName;

    /**
     * Price of the ticket.
     */
    private BigDecimal ticketPrice;

    /**
     * Constructs a new Ticket object with the specified ID, event name, and price.
     *
     * @param ticketID    Unique identifier for the ticket.
     * @param eventName   Name of the event associated with the ticket.
     * @param ticketPrice Price of the ticket.
     */
    public Ticket(int ticketID, String eventName, BigDecimal ticketPrice) {
        this.ticketID = ticketID;
        this.eventName = eventName;
        this.ticketPrice = ticketPrice;
    }

    // Getters and Setters

    /**
     * Gets the unique identifier of the ticket.
     *
     * @return Ticket ID.
     */
    public int getTicketID() {
        return ticketID;
    }

    /**
     * Sets the unique identifier of the ticket.
     *
     * @param ticketID Ticket ID to set.
     */
    public void setTicketID(int ticketID) {
        this.ticketID = ticketID;
    }

    /**
     * Gets the name of the event associated with the ticket.
     *
     * @return Event name.
     */
    public String getEventName() {
        return eventName;
    }

    /**
     * Sets the name of the event associated with the ticket.
     *
     * @param eventName Event name to set.
     */
    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    /**
     * Gets the price of the ticket.
     *
     * @return Ticket price.
     */
    public BigDecimal getTicketPrice() {
        return ticketPrice;
    }

    /**
     * Sets the price of the ticket.
     *
     * @param ticketPrice Ticket price to set.
     */
    public void setTicketPrice(BigDecimal ticketPrice) {
        this.ticketPrice = ticketPrice;
    }
}