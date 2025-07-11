import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';

import * as dat from 'dat.gui';
// const dat = require('dat.gui');
// import 'tracking';

declare const tracking: {
  ObjectTracker: new (arg0: string) => any;
  track: (arg0: string, arg1: any, arg2: { camera: boolean }) => any;
};

@Component({
  selector: 'app-tracking-face-camera',
  templateUrl: './tracking-face-camera.component.html',
  styleUrls: ['./tracking-face-camera.component.css'],
})
export class TrackingFaceCameraComponent implements OnInit {
  @ViewChild('video1', { static: true }) video!: ElementRef;
  @ViewChild('canvas1', { static: true }) canvas!: ElementRef;

  constructor() {}

  ngOnInit(): void {
    // let canvas = document.getElementById('canvas');
    let context = this.canvas.nativeElement.getContext('2d');

    var tracker = new tracking.ObjectTracker('face');
    tracker.setInitialScale(4);
    tracker.setStepSize(2);
    tracker.setEdgesDensity(0.1);

    tracking.track('#video', tracker, { camera: true });

    tracker.on('track', (event: any) => {
      context.clearRect(0, 0, this.canvas.nativeElement.width, this.canvas.nativeElement.height);

      event.data.forEach((rect: any) => {
        context.strokeStyle = '#a64ceb';
        context.strokeRect(rect.x, rect.y, rect.width, rect.height);
        context.font = '11px Helvetica';
        context.fillStyle = '#fff';
        context.fillText('x: ' + rect.x + 'px', rect.x + rect.width + 5, rect.y + 11);
        context.fillText('y: ' + rect.y + 'px', rect.x + rect.width + 5, rect.y + 22);
      });
    });

    var gui = new dat.GUI();
    gui.add(tracker, 'edgesDensity', 0.1, 0.5).step(0.01);
    gui.add(tracker, 'initialScale', 1.0, 10.0).step(0.1);
    gui.add(tracker, 'stepSize', 1, 5).step(0.1);
  }
}
