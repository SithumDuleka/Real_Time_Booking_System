package TicketPool;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Random;

public class ticketPool {
    public ArrayList<Ticket> pool;
    private final int totalTicket;
    private final int maxTicketcapacity;
    private int totalSoldTickets;
    private boolean isTerminated;
    private int totalAddedticketsCount;

    public ticketPool(int totalTicket,int maxTicketcapacity){
        this.totalTicket=totalTicket;
        this.maxTicketcapacity=maxTicketcapacity;
        pool=new ArrayList<>();
        isTerminated=false;
        totalAddedticketsCount=0;
    }

    public synchronized void addTickets(String VendorName){
        Random random=new Random();
        int max=random.nextInt(10)+1;
        if(isTerminated || totalAddedticketsCount>=totalTicket){
            return;
        }
        while(pool.size()>=maxTicketcapacity){
            try{
                System.out.println("Ticket Pool is Full "+VendorName+" is waiting");
                wait();
            }catch(InterruptedException e){
                e.printStackTrace();
                throw new RuntimeException(e.getMessage());
            }
        }
        //compare the number of tickets to avoid not adding last tickets to the pool
        int pendingTicketsForthePool=Math.min(max,totalTicket-totalAddedticketsCount);
        totalAddedticketsCount+=pendingTicketsForthePool;
        for(int i=0;i<pendingTicketsForthePool;i++){
            Ticket ticket=new Ticket(1000,"Spandana",new BigDecimal(2500.00));
            pool.add(ticket);
        }

        System.out.println(VendorName+" "+pendingTicketsForthePool+" tickets added to pool... Current pool size :"+pool.size()+"  Total added tickets :"+totalAddedticketsCount);
        if(totalAddedticketsCount>=totalTicket){
            System.out.println("Reache the ticket limit");
        }
        notifyAll();

    }
    public synchronized void getTickets(String CustomerName) {
        Random random=new Random();
        int max=random.nextInt(10)+1;
        if (isTerminated || totalSoldTickets >=totalTicket) {
            return;
        }
        while (pool.isEmpty() || Math.min(max, totalTicket - totalSoldTickets) > pool.size()) {
            try {
                System.out.println("No tickets Available..." + CustomerName + " is waiting");
                wait();

            } catch (InterruptedException e) {
            }
        }
        int pendinggettingTickets = Math.min(max,totalTicket-totalSoldTickets);
        totalSoldTickets += pendinggettingTickets;
        for (int i = 0; i < pendinggettingTickets; i++) {
            pool.remove(0);
        }
        System.out.println(CustomerName + " is purchased... " + pendinggettingTickets + " Total number of ticket sold : " + totalSoldTickets);
        notifyAll();
        if (totalSoldTickets >= totalTicket) {
            isTerminated = true;
            System.out.println("All tickets sold..");

        }


    }
    public synchronized boolean isTerminated(){
        return isTerminated;
    }

}