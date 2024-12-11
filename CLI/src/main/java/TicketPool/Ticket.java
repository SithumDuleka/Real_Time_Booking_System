package TicketPool;

import java.math.BigDecimal;

public class Ticket {
    private int ticketID;
    private String eventName;
    private BigDecimal ticketPrice;

    public Ticket(int ticketID,String eventName, BigDecimal ticketPrice) {
        this.ticketID=ticketID;
        this.eventName = eventName;
        this.ticketPrice = ticketPrice;
    }

    public int getTicketID() {
        return ticketID;
    }

    public void setTicketID(int ticketID) {
        this.ticketID = ticketID;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public BigDecimal getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(BigDecimal ticketPrice) {
        this.ticketPrice = ticketPrice;
    }
}
