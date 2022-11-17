import { TestBed } from '@angular/core/testing';
import { features } from 'process';

import { StubsService } from './stubs.service';

describe('StubsService', () => {
  let service: StubsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(StubsService);
    service.isBusy = true;
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
