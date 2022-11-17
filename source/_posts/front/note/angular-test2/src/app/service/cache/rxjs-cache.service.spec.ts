import { TestBed } from '@angular/core/testing';

import { RxjsCacheService } from './rxjs-cache.service';

describe('RxjsCacheService', () => {
  let service: RxjsCacheService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RxjsCacheService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
