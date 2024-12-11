export class Configuration {
    totalTickets: number = 0;
    releaseRate: number = 0;
    customerRetrievalRate: number = 0;
    maxTicketCapacity: number = 0;
  
    constructor( totalTickets: number = 0,releaseRate: number = 0,customerRetrievalRate: number = 0,maxTicketCapacity: number = 0) {
      this.totalTickets = totalTickets;
      this.releaseRate = releaseRate;
      this.customerRetrievalRate = customerRetrievalRate;
      this.maxTicketCapacity = maxTicketCapacity;
    }
  
    isValid(): boolean {
      return (
        this.totalTickets > 0 &&
        this.releaseRate > 0 &&
        this.customerRetrievalRate > 0 &&
        this.maxTicketCapacity > 0
      );
    }
  }