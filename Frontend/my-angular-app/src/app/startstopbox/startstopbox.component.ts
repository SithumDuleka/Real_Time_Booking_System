import { Component } from '@angular/core';
import { SimulationService } from '../services/simulation.service';

@Component({
  selector: 'app-startstopbox',
  standalone: true,
  imports: [],
  templateUrl: './startstopbox.component.html',
  styleUrls: ['./startstopbox.component.css'],
})
export class StartstopboxComponent {
  constructor(private simulationService: SimulationService) {}

  startSimulation(): void {
    this.simulationService.startSimulation().subscribe({
      next: (response) => {
        console.log('Simulation started successfully:', response);
        alert('Simulation started successfully!');
      },
      error: (err) => {
        console.error('Error starting simulation:', err);
        alert('Failed to start simulation. Please try again.');
      },
    });
  };
  // }

  // Method to stop the simulation
  stopSimulation(): void {
    this.simulationService.stopSimulation().subscribe({
      next: (response) => {
        console.log('Simulation stopped successfully:', response);
        alert('Simulation stopped successfully!');
      },
      error: (err) => {
        console.error('Error stopping simulation:', err);
        alert('Failed to stop simulation. Please try again.');
      },
    });
  }
}