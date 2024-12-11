import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ProgressBarService {
  private baseUrl = 'http://localhost:8080/ticket-system';
  private socket: WebSocket | null = null;
  public availableTicketsSubject: Subject<number> = new Subject<number>();
  private isConnected = false; // Track WebSocket connection state

  constructor(private http: HttpClient) {}

  // Connect to WebSocket (only if not already connected)
  connectToWebSocket(): WebSocket {
    if (this.isConnected) {
      return this.socket!;  // If already connected, return the existing WebSocket
    }

    this.socket = new WebSocket('ws://localhost:8080/live-updates'); // WebSocket URL
    this.socket.onmessage = (event) => {
      const data = JSON.parse(event.data);
      this.availableTicketsSubject.next(data.availableTickets); // Update available tickets
    };

    this.socket.onopen = () => {
      this.isConnected = true; // Mark the WebSocket as connected
      console.log('WebSocket connection established.');
    };

    this.socket.onclose = () => {
      this.isConnected = false; // WebSocket is disconnected
      console.log('WebSocket connection closed.');
    };

    return this.socket;
  }

  // Send a message to the WebSocket server to set the total number of tickets
  setTotalTickets(tickets: number) {
    if (this.socket) {
      const message = `SET_TOTAL_TICKETS:${tickets}`;
      this.socket.send(message); // Send total ticket count to backend via WebSocket
    }
  }

  // Send a message to the WebSocket server to simulate buying tickets
  buyTickets(tickets: number) {
    if (this.socket) {
      const message = `BUY_TICKET:${tickets}`;
      this.socket.send(message); // Send ticket purchase request to backend via WebSocket
    }
  }

  // You can use the HttpClient to make HTTP requests to your backend
  getTickets() {
    return this.http.get(`${this.baseUrl}/tickets`);
  }

  // Send HTTP request to set up or update tickets (optional)
  updateTicketCount(tickets: number) {
    return this.http.post(`${this.baseUrl}/update-tickets`, { tickets });
  }
}