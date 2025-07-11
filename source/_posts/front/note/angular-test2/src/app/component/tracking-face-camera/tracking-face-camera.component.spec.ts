import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TrackingFaceCameraComponent } from './tracking-face-camera.component';

describe('TrackingFaceCameraComponent', () => {
  let component: TrackingFaceCameraComponent;
  let fixture: ComponentFixture<TrackingFaceCameraComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TrackingFaceCameraComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TrackingFaceCameraComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
