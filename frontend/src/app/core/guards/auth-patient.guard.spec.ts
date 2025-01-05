import { TestBed } from '@angular/core/testing';
import { CanActivateFn } from '@angular/router';

import { authPatientGuard } from './auth-patient.guard';

describe('authPatientGuard', () => {
  const executeGuard: CanActivateFn = (...guardParameters) => 
      TestBed.runInInjectionContext(() => authPatientGuard(...guardParameters));

  beforeEach(() => {
    TestBed.configureTestingModule({});
  });

  it('should be created', () => {
    expect(executeGuard).toBeTruthy();
  });
});
