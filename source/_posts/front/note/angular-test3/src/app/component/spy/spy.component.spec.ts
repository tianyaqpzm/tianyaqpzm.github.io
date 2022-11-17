import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Title } from '@angular/platform-browser';

import { SpyComponent } from './spy.component';

/**
 *  Spying 方法需要创建一个真实依赖的实例，但是在测试过程中，我们能够获得更多的信息，比如方法调用次数，传入的参数等。
 */
describe('SpyComponent', () => {
  let component: SpyComponent;
  let fixture: ComponentFixture<SpyComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [SpyComponent],
      providers: [Title]
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SpyComponent);
    component = fixture.componentInstance;
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  /**
   * 使用 spy 测试标题设置是否正确。
   * spyOn 方法接收两个参数：要监控的对象和要调用的方法。
   * expect 方法验证了传递给 setTitle 方法的参数。
   * 在一个组件的生命周期里，一个方法可能被调用很多次，使用 spy.calls.mostRecent() 方法， 验证最近一次的调用是比较安全的。
   */
  it('should set the title', () => {
    const title = TestBed.inject(Title);
    const spy = spyOn(title, 'setTitle');
    fixture.detectChanges();
    console.log(spy.calls.mostRecent());
    expect(spy.calls.mostRecent().args[0]).toBe('Angular Spying');
  });

  it('should be return open', () => {
    expect(component.testField()).toBe('open');
  });
});
