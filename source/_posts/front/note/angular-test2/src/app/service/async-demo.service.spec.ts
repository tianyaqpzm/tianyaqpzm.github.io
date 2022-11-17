import { TestBed } from '@angular/core/testing';

import { AsyncDemoService } from './async-demo.service';

describe('AsyncDemoService', () => {
  let service: AsyncDemoService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AsyncDemoService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should set data', () => {
    const result = service.setData('《图书 零》');
    expect(result.length).toBe(6);
  });

  /**
   * 因为 Karma 不知道异步操作何时执行完成，
   * 需要使用 done 方法通知测试框架，表明异步操作已完成，可以进行结果验证。
   */
  it('should get data', (done: DoneFn) => {
    service.getData().subscribe(books => {
      expect(books.length).toBe(5);
      done();
    });
  });
});
