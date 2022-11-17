import { ElementRef } from '@angular/core';
import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VideoOption, VideoPlayerComponent } from './video-player.component';

describe('VideoPlayerComponent', () => {
  let component: VideoPlayerComponent;
  let fixture: ComponentFixture<VideoPlayerComponent>;
  let elementRef: ElementRef;
  let options: VideoOption = {
    fluid: true,
    controls: false,
    // aspectRatio: string;
    autoplay: false
  };
  beforeEach(async () => {
    // const elementRef = jasmine.createSpyObj('ElementRef');
    await TestBed.configureTestingModule({
      declarations: [VideoPlayerComponent]
      // providers: [{ ElementRef, elementRef }]
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(VideoPlayerComponent);
    component = fixture.componentInstance;
    component.options = options;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
