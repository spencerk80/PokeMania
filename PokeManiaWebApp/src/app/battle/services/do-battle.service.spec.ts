import { TestBed } from '@angular/core/testing';

import { DoBattleService } from './do-battle.service';

describe('DoBattleService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: DoBattleService = TestBed.get(DoBattleService);
    expect(service).toBeTruthy();
  });
});
