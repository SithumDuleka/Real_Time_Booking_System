package ticketbooking.Configuration;

public class Configuration {
    private int totalTicket;
    private int totalReleaseRate;
    private int customerRetrievalRate;
    private int maxTicketcapacity;

    public Configuration(){}
    public Configuration(int totalTicket,int totalReleaseRate,int customerRetrievalRate,int maxTicketcapacity){
        setTotalTicket(totalTicket);
        setTotalReleaseRate(totalReleaseRate);
        setCustomerRetrievalRate(customerRetrievalRate);
        setMaxTicketcapacity(maxTicketcapacity);
    }

    public void setTotalTicket(int totalTicket){
        this.totalTicket=totalTicket;
    }
    public void setTotalReleaseRate(int totalReleaseRate){
        this.totalReleaseRate=totalReleaseRate;
    }
    public void setCustomerRetrievalRate(int customerRetrievalRate){
        this.customerRetrievalRate=customerRetrievalRate;
    }
    public void setMaxTicketcapacity(int maxTicketcapacity){
        this.maxTicketcapacity=maxTicketcapacity;
    }
    public int getTotalTicket(){
        return totalTicket;
    }
    public int getTotalReleaseRate(){
        return totalReleaseRate;
    }
    public int getMaxTicketcapacity(){
        return maxTicketcapacity;
    }
    public int getCustomerRetrievalRate(){
        return customerRetrievalRate;
    }
}
