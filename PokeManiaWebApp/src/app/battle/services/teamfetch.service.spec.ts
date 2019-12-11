import { TestBed } from '@angular/core/testing';

import { TeamfetchService } from './teamfetch.service';

describe('TeamfetchService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: TeamfetchService = TestBed.get(TeamfetchService);
    expect(service).toBeTruthy();
  });
});
