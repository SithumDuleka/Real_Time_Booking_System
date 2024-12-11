import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { SimulationService } from '../services/simulation.service';
import { Configuration } from '../models/configuration.model';

@Component({
  standalone: true,
  selector: 'app-bootst',
  imports: [FormsModule],
  templateUrl: './bootst.component.html',
  styleUrls: ['./bootst.component.css'],
})
export class BootstComponent {
  config: Configuration = new Configuration();

  constructor(private simulationService: SimulationService) {}

  submitConfiguration(): void {
    if (!this.config.isValid()) {
      alert('Please provide valid configuration values.');
      return;
    }
  
    console.log('Payload being sent:', this.config);
  
    this.simulationService.saveConfiguration(this.config).subscribe({
      next: (response) => {
        console.log('Simulation Response:', response);
        alert('Simulation started successfully!');
      },
      error: (err) => {
        console.error('Error starting simulation:', err);
        alert('Error starting simulation. Please try again.');
      },
    });
  }
}