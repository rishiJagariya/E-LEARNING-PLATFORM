import { TestBed } from '@angular/core/testing';

import { ElpServiceService } from './elp-service.service';

describe('ElpServiceService', () => {
  let service: ElpServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ElpServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
