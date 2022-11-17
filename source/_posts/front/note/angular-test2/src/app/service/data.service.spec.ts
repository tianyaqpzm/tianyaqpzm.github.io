import { TestBed } from '@angular/core/testing';

import { DataService } from './data.service';

import {
  HttpClientTestingModule,
  HttpTestingController
} from '@angular/common/http/testing';

/**
 * 在一个服务的业务逻辑中，经常会包含访问远程 API 的情况，
 * 那么该如何测试这样的服务呢？
 */
describe('DataService', () => {
  let service: DataService;
  let httpClientMock: HttpTestingController;

  // 没有引入 HttpClientTestingModule 模块, 执行测试用例时找不到模块。
  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule]
    });
    service = TestBed.inject(DataService);
    httpClientMock = TestBed.inject(HttpTestingController);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  /**
   * HttpTestingController 类提供了 expectOne 方法，
   * 接受一个网络地址参数（URL）。
   * expectOne 方法会创建一个模拟的请求对象，并确保请求只发一次。
   */
  it('should get books', () => {
    const books = ['图书 零', '图书 陆'];
    service.getBooks().subscribe(books => expect(books.length).toBe(2));
    const req = httpClientMock.expectOne('api/books');
    expect(req.request.method).toEqual('GET');
    req.flush(books);
  });

  it('should add books', () => {
    service.addBook('《球状闪电》').subscribe();
    const req = httpClientMock.expectOne('api/books');
    expect(req.request.method).toEqual('POST');
    expect(req.request.body).toEqual({ book: '《球状闪电》' });
    req.flush('');
  });
});
