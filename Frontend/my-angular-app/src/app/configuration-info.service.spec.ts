import { TestBed } from '@angular/core/testing';

import { SimulationService } from './services/simulation.service';

describe('ConfigurationInfoService', () => {
  let service: SimulationService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SimulationService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
