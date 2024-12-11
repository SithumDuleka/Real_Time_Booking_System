import { Component } from '@angular/core';
import { BootstComponent } from './bootst/bootst.component';
import { SimulationLogComponent } from './simulation-log/simulation-log.component';
import { StartstopboxComponent } from './startstopbox/startstopbox.component';
import { TicketMonitorComponent } from './ticket-monitor/ticket-monitor.component';
import { LoggerCardComponent } from './logger-card/logger-card.component';



@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    BootstComponent,
    StartstopboxComponent,
    LoggerCardComponent,
  ],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent {
  title = 'my-angular-app';
}