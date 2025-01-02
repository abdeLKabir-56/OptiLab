import { TestBed } from '@angular/core/testing';

import { AnalysesService } from './analyses.service';

describe('AnalysesService', () => {
  let service: AnalysesService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AnalysesService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
