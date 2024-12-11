import { TestBed } from '@angular/core/testing';

import { ProgressbarServiceService } from './services/progressbar-service.service';

describe('ProgressbarServiceService', () => {
  let service: ProgressbarServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ProgressbarServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
