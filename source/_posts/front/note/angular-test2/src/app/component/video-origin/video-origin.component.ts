import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';

declare const navigator: any;

@Component({
  selector: 'app-video-origin',
  templateUrl: './video-origin.component.html',
  styleUrls: ['./video-origin.component.css']
})
export class VideoOriginComponent implements OnInit {
  @ViewChild('targetOrigin', { static: true }) target!: ElementRef;

  player!: any;

  oCapture: any;
  userMedia: any;
  buffer: any;

  constructor(private elementRef: ElementRef) {
    // const oCapture = document.querySelector("video"),
    this.userMedia = navigator.getUserMedia =
      navigator.getUserMedia ||
      navigator.webKitGetUserMedia ||
      navigator.mozGetUserMedia ||
      navigator.msGetUserMedia;
  }

  ngOnInit(): void {
    alert('navigator.userAgentData:' + navigator.userAgentData);

    console.log('invokingCarera');
  }

  ngAfterViewInit() {
    //  document.getElementById('videoNew');
    // debugger;
    // this.oCapture = this.elementRef.nativeElement.querySelector(
    //   'video#videoNew'
    // );
    // console.log(this.oCapture);
  }

  ngOnDestory() {}

  invokingCarera() {
    debugger;
    console.log('invokingCarera');
    if (navigator.mediaDevices && navigator.mediaDevices.getUserMedia) {
      navigator.mediaDevices
        .getUserMedia({
          audio: false,
          video: { width: 720, height: 720 }
          // facingMode: 'user'
        })
        .then((mediaStream: any) => {
          this.getVideoStream(mediaStream);
        })
        .catch((error: any) => {
          alert(error);
        });
    } else if (navigator.getUserMedia) {
      navigator.getUserMedia(
        {
          video: true,
          audio: true
        },
        this.getVideoStream,
        () => {
          alert('failed');
        }
      );
    } else {
      alert('不支持摄像头调用！');
    }
  }
  //调用成功
  private getVideoStream(stream: any) {
    this.buffer = stream;
    console.log('this.target.nativeElement:', this.target);
    if (this.target.nativeElement.mozSrcObject !== undefined) {
      this.target.nativeElement.mozSrcObject = this.buffer;
    } else {
      try {
        this.target.nativeElement.srcObject = this.buffer;
      } catch (error) {
        this.target.nativeElement.src =
          window.URL && window.URL.createObjectURL(this.buffer);
      }
    }
    this.target.nativeElement.play();
  }

  closeCamera() {
    debugger;
    if (this.buffer) {
      let fn =
        typeof this.buffer.stop === 'function'
          ? this.buffer
          : this.buffer.getTracks()[0];
      fn.stop();
      // this.buffer.getTracks()[1].stop();
    }
  }
}
