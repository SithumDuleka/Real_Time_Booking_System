package ticketbooking.Users;

import TicketPool.ticketPool;

public class Vendor implements Runnable{
    private ticketPool ticketPool;
    private String VendorName;
    private int ticketReleaseRate;
    public Vendor(ticketPool ticketPool,int ticketReleaseRate,String VendorName){
        this.ticketPool=ticketPool;
        this.ticketReleaseRate=ticketReleaseRate;
        this.VendorName=VendorName;

    }
    @Override
    public void run(){
        while(!Thread.currentThread().isInterrupted()) {
            try {
                ticketPool.addTickets(VendorName);
                Thread.sleep(ticketReleaseRate*1000);
            } catch (InterruptedException e){
                Thread.currentThread().interrupt();
                System.out.println(VendorName+" thread interrupted !");
                break;
            }
        }
    }
}
