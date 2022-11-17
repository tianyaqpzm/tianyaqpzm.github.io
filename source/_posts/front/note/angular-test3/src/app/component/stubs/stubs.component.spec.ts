import { ComponentFixture, TestBed } from '@angular/core/testing';
import { StubsService } from 'src/app/service/stubs.service';

import { StubsComponent } from './stubs.component';
let service: StubsService;

/**
 * Stubbing: 该方法会让依赖注入器注入一个依赖的 stub 对象，而不是真实的依赖对象。一个 stub 就是一个实际依赖的仿制对象，可以控制他的行为，满足单元测试需要。
 *
 */
describe('StubsComponent', () => {
  let component: StubsComponent;
  let fixture: ComponentFixture<StubsComponent>;
  let messageToDisplay: HTMLElement;

  // beforeEach(async () => {
  //   await TestBed.configureTestingModule({
  //     declarations: [StubsComponent]
  //   }).compileComponents();
  // });

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [StubsComponent],
      providers: [{ provide: StubsService, useValue: serviceStubs }]
    });
    fixture = TestBed.createComponent(StubsComponent);
    component = fixture.componentInstance;
    messageToDisplay = fixture.nativeElement.querySelector('span');
    service = TestBed.inject(StubsService);
    // fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  // 使用 stubbing 方法创建一个依赖的仿品，有两个方式：
  const serviceStubs: Partial<StubsService> = {
    name: 'HelloWorld'
  };
  it('should display the service name', () => {
    fixture.detectChanges();
    console.log(messageToDisplay.textContent);
    expect(messageToDisplay.textContent).toContain(serviceStubs.name);
  });

  describe('status', () => {
    it('should be unavailable', () => {
      service.isBusy = true;
      fixture.detectChanges();
      expect(messageToDisplay.textContent).toContain('服务正忙');
    });
    // describe('init', () => {
    //   it('should be message', () => {
    //     fixture.detectChanges();
    //     expect(messageToDisplay.textContent).toContain('服务正忙');
    //   });
    // });
    it('should be available', () => {
      service.isBusy = false;
      fixture.detectChanges();
      expect(messageToDisplay.textContent).toContain('服务可用');
    });
  });
});
