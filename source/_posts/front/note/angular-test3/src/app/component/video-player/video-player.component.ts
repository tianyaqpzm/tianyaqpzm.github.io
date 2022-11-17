import {
  AfterViewInit,
  Component,
  ElementRef,
  Input,
  OnInit,
  ViewChild
} from '@angular/core';
import videojs from 'video.js';

declare const navigator: any;
export interface VideoOption {
  fluid: boolean;
  controls: boolean;
  // aspectRatio: string;
  autoplay: boolean;
  height?: number;
  sources?: {
    src: string;
    type: string;
  }[];
}

@Component({
  selector: 'app-video-player',
  templateUrl: './video-player.component.html',
  styleUrls: ['./video-player.component.css']
})
export class VideoPlayerComponent implements OnInit, AfterViewInit {
  @ViewChild('target', { static: true }) target!: ElementRef;

  // @Input()
  options: VideoOption = {
    fluid: true,
    autoplay: true,
    controls: false
  };
  player!: videojs.Player;

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
    this.options.height = 1000;
    this.player = videojs(this.target.nativeElement, this.options, () => {
      console.log('onPlayerReady', this);
    });
  }

  ngAfterViewInit() {
    // debugger;
    // this.oCapture = this.elementRef.nativeElement.querySelector(
    //   'video#videoNew'
    // );
    // console.log(this.oCapture);
  }

  ngOnDestory() {
    if (this.player) {
      this.player.dispose();
    }
  }

  invokingCarera() {
    if (navigator.mediaDevices && navigator.mediaDevices.getUserMedia) {
      navigator.mediaDevices
        .getUserMedia({
          audio: false,
          video: { width: 720, height: 720 }
          // video: { facingMode: 'user' }
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
  getVideoStream(stream: any) {
    this.buffer = stream;
    if (this.target.nativeElement?.mozSrcObject !== undefined) {
      this.target.nativeElement.mozSrcObject = this.buffer;
    } else {
      try {
        this.target.nativeElement.srcObject = this.buffer;
      } catch (error) {
        this.target.nativeElement.src =
          window.URL && window.URL.createObjectURL(this.buffer);
      }
    }
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
