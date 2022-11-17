import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VideoOriginComponent } from './video-origin.component';

describe('VideoOriginComponent', () => {
  let component: VideoOriginComponent;
  let fixture: ComponentFixture<VideoOriginComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ VideoOriginComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(VideoOriginComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
