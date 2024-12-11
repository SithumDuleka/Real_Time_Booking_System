import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Configuration } from '../models/configuration.model';

@Injectable({
  providedIn: 'root',
})
export class SimulationService {
  private apiUrl = 'http://localhost:8080/api/simulation';

  constructor(private http: HttpClient) {}
  saveConfiguration(payload: { 
      totalTickets: number; 
       releaseRate: number; 
       customerRetrievalRate: number; 
       maxTicketCapacity: number; 
  }): Observable<{ message: string }> {
    return this.http.post<{ message: string }>(`${this.apiUrl}/save`, payload);
  }
  stopSimulation(): Observable<string> {
    return this.http.post(`${this.apiUrl}/stop`, {}, { responseType: 'text' });
  }
  startSimulation(): Observable<{ message: string }> {
    return this.http.post<{ message: string }>(`${this.apiUrl}/start`, {});
  }
}