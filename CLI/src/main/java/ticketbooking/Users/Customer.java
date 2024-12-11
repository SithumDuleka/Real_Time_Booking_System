package ticketbooking.Users;

import TicketPool.ticketPool;

public class Customer implements Runnable{
    private ticketPool ticketPool;
    private int retrievalRate;
    private String CustomerName;
    public Customer(ticketPool ticketPool,int retrievalRate,String CustomerName){
        this.ticketPool=ticketPool;
        this.retrievalRate=retrievalRate;
        this.CustomerName=CustomerName;
    }
    @Override
    public void run(){
        while(!Thread.currentThread().isInterrupted()){
            try {
                ticketPool.getTickets(CustomerName);
                Thread.sleep(retrievalRate*1000);
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
                System.out.println(CustomerName+" thread interrupted");
                break;
            }

        }
    }
}
