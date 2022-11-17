import { TestBed } from '@angular/core/testing';

import { MasterService } from './master.service';
import { StubsService } from './stubs.service';

describe('MasterService', () => {
  let service: MasterService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MasterService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

// https://www.w3cschool.cn/angular13/angular13-3ie23p0o.html
describe('MasterService without Angular testing support', () => {
  let masterService: MasterService;

  it('#getValue should return real value from the real service', () => {
    const stbService = new StubsService();
    stbService.isBusy = true;
    masterService = new MasterService(stbService);
    expect(masterService.getValue()).toBe('real value');
  });

  // it('#getValue should return faked value from a fakeService', () => {
  //   masterService = new MasterService(new FakeStubsService());
  //   expect(masterService.getValue()).toBe('faked service value');
  // });

  it('#getValue should return faked value from a fake object', () => {
    const fake = { getValue: () => 'fake value' };
    masterService = new MasterService(fake as StubsService);
    expect(masterService.getValue()).toBe('fake value');
  });

  it('#getValue should return stubbed value from a spy', () => {
    // create `getValue` spy on an object representing the StubsService
    const StubsServiceSpy = jasmine.createSpyObj('StubsService', ['getValue']);

    // set the value to return when the `getValue` spy is called.
    const stubValue = 'stub value';
    StubsServiceSpy.getValue.and.returnValue(stubValue);

    masterService = new MasterService(StubsServiceSpy);

    expect(masterService.getValue())
      .withContext('service returned stub value')
      .toBe(stubValue);
    expect(StubsServiceSpy.getValue.calls.count())
      .withContext('spy method was called once')
      .toBe(1);
    expect(StubsServiceSpy.getValue.calls.mostRecent().returnValue).toBe(
      stubValue
    );
  });
});
