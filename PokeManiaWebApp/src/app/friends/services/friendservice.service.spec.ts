import { TestBed } from '@angular/core/testing';

import { FriendserviceService } from './friendservice.service';

describe('FriendserviceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: FriendserviceService = TestBed.get(FriendserviceService);
    expect(service).toBeTruthy();
  });
});
