import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { Utils } from 'src/utils/CommonUtils';

declare const window: any;

// require('tracking/build/tracking-min.js')
// require('tracking/build/data/face-min.js')
// require('tracking/build/data/mouth-min.js')
// require('tracking/examples/assets/stats.min.js')
// declare const tracking: {
//   ObjectTracker: new (arg0: any) => any;
//   track: (arg0: string, arg1: any, arg2?: { camera: boolean }) => any;
// };
import 'tracking';
@Component({
  selector: 'app-video-tracking',
  templateUrl: './video-tracking.component.html',
  styleUrls: ['./video-tracking.component.css'],
})
export class VideoTrackingComponent implements OnInit {
  @ViewChild('targetOrigin', { static: true }) target!: ElementRef;

  @ViewChild('targetVideo', { static: true }) targetVideo!: ElementRef;

  trackerTask: any;
  facevideo: any;
  facecanvas: any;
  videoWidth: any;
  videoHeight: any;
  facecontext: any;
  tra: any;
  buffer: any;
  flag = true;
  logger: any;
  recorder: any;

  // 绘制动画
  requestAnimationFrame: any;
  informationTitle: any;
  videoBitsPerSecond: number;
  mineType: string;
  seasons: string[] = ['video/webm;codecs=vp9', 'video/webm;codecs=vp8', 'video/webm;codecs=h264', 'video/webm'];

  constructor() {
    this.videoBitsPerSecond = 64000;
    this.mineType = MediaRecorder.isTypeSupported('video/webm; codecs=vp9') ? 'video/webm; codecs=vp9' : 'video/webm';
  }

  ngOnInit(): void {
    this.init();
  }

  init(): void {
    console.log(this.videoBitsPerSecond);
    // ua: string;
    // browser: IBrowser;
    // device: IDevice;
    // engine: IEngine;
    // os: IOS;
    // cpu: ICPU;
    const { browser, ua, device, os } = Utils.getUserAgent();

    // this.logger = `browser:${browser.name}; device:${device.type}; os:${os.name}${os.version};\n`;
    this.logger = `ua: ${ua};  `;
    // 针对ios特殊处理
    if (window.isIOS) {
      let videoId = document.getElementById('video_bind');
      videoId?.removeAttribute('controls');
    }
    var tipFlag = false; // 是否检测
    var faceflag = false; // 是否进行拍照
    this.informationTitle = document.querySelector('.tip-box'); //人脸提示
    // 获取video、canvas实例
    this.facevideo = document.getElementById('video_bind');
    this.facecanvas = document.getElementById('canvas_bind');
    this.facecanvas.width = this.facecanvas.clientWidth;
    this.facecanvas.height = this.facecanvas.clientHeight;

    // var videoWidth = (window.videoHeight = 0);
    // "noImplicitAny": false, tsconfig.json
    this.facevideo.addEventListener('canplay', function (this: any, err: any) {
      debugger;
      window.videoWidth = this.videoWidth;
      window.videoHeight = this.videoHeight;
    });

    this.facecontext = this.facecanvas.getContext('2d');
    // var tracker = new tracking.ObjectTracker('face');
    var tracker = new tracking.ObjectTracker(['face', 'eye', 'mouth']);

    // 每次打开弹框先清除canvas没拍的照片
    this.facecontext.clearRect(0, 0, this.facecanvas.width, this.facecanvas.height);

    // 2. 判断有无摄像头权限
    if (navigator.mediaDevices === undefined) {
      (navigator as any).mediaDevices = {};
    }
    if (navigator.mediaDevices.getUserMedia === undefined) {
      navigator.mediaDevices.getUserMedia = (constraints) => {
        var getUserMedia =
          (navigator as any).webkitGetUserMedia ||
          (navigator as any).mozGetUserMedia ||
          (navigator as any).mozGetUserMedia ||
          (navigator as any).msGetUserMedia ||
          (navigator as any).oGetUserMedia;
        if (!getUserMedia) {
          this.logger = 'getUserMedia is not implemented in this browser';
          return Promise.reject(new Error('getUserMedia is not implemented in this browser'));
        }
        return new Promise(function (resolve, reject) {
          getUserMedia.call(navigator, constraints, resolve, reject);
        });
      };
    }
    (navigator as any).getUserMedia = navigator.mediaDevices.getUserMedia;

    // 3. 打开摄像头，并开始监听
    // framerate: {   min: 15,     ideal: 30,     max: 60     },

    navigator.mediaDevices
      .getUserMedia({
        audio: false,
        video: {
          facingMode: 'user',
        },
      })
      .then((stream) => {
        this.logger += '【3.getUserMedia then,】';
        this.getVideoStream(this.target, stream);
        // 使用监听人脸的包
        tracker.setInitialScale(4);
        tracker.setStepSize(2);
        tracker.setEdgesDensity(0.1);

        this.logger += '【4.tracking.track start】';
        //打开摄像头
        this.tra = tracking.track('#video_bind', tracker);
        // ele.nativeElement.onloadedmetadata = (e) => {
        //   this.trackerTask.setInitialScale(4);
        //   this.trackerTask.setStepSize(2);
        //   this.trackerTask.setEdgesDensity(0.1);
        //   this.trackerTask.track('#video', tracking);
        //   ele.nativeElement?.play();
        // };
        this.logger += '【5.tracking.track end】';

        var timer: NodeJS.Timeout | null = null;

        // tracker.on('track', function(event) {
        //   context.clearRect(0, 0, canvas.width, canvas.height);

        //   event.data.forEach(function(rect) {
        //     context.font = '11px Helvetica';
        //    context .fillText("已识别到人脸，请点击拍照",100,40);
        //     context.strokeStyle = '#a64ceb';
        //     context.strokeRect(rect.x, rect.y, rect.width, rect.height);
        //   });
        // });
        // 创建监听 每帧都会触发
        tracker.on('track', (event: any) => {
          console.log('event.data:', event.data);

          if (!tipFlag) {
            this.facecontext.clearRect(0, 0, this.facecanvas.width, this.facecanvas.height);
            if (event.data.length === 0) {
              //未检测到人脸
              if (!faceflag && !timer) {
                timer = setTimeout(() => {
                  this.informationTitle.innerHTML = '未检测到人脸';
                }, 500);
              }
            } else if (event.data.length === 1) {
              // 长度为多少代表检测到几张人脸
              window.clearTimeout(timer);
              timer = null;
              this.informationTitle.innerHTML = '请将脸部置于屏幕中央';
              event.data.forEach((rect: any) => {
                console.log(
                  `rect.x: ${rect.x}, rect.y:${rect.y}, rect.width:${rect.width}, rect.height:${rect.height}`
                );
              });
              //检测到一张人脸
              if (!tipFlag) {
                // 给检测到的人脸绘制矩形
                event.data.forEach((rect: any) => {
                  // this.facecontext.font = '11px Helvetica';
                  // this.facecontext.fillText(
                  //   '已识别到人脸，请点击拍照',
                  //   100,
                  //   40
                  // );
                  this.facecontext.strokeStyle = '#a64ceb';
                  this.facecontext.strokeRect(rect.x, rect.y, rect.width, rect.height);
                });
                let rect = event.data[0];
                console.log(
                  `rect.x: ${rect.x}, rect.y:${rect.y}, rect.width:${rect.width}, rect.height:${rect.height}`
                );

                //判断脸部是否在屏幕中间
                if (
                  !faceflag &&
                  rect.x > this.facevideo.clientWidth * 0.3 &&
                  rect.x < this.facevideo.clientWidth * 0.7
                ) {
                  // 检测到人脸进行拍照，延迟0.5秒
                  // 4. 监听到人脸后自动截取当前帧并保存为图片-tackPhoto()
                  this.informationTitle.innerHTML = '识别中，请勿乱动~';

                  faceflag = true;
                  tipFlag = true;
                  setTimeout(() => {
                    // this.tackPhoto(); // 拍照
                    // 开始录制
                    // this.getRecorder(stream);
                  }, 1000);
                }
              }
            } else {
              //检测到多张人脸
              if (!faceflag) {
                this.informationTitle.innerHTML = '只可一人进行人脸识别！';
              }
            }
          }
        });
      })
      .catch((err) => {
        this.logger += `【 ${err}】`;
        this.informationTitle = '打开摄像头失败';
      });
  }
  // plot(x, y, w, h) {
  //   var rect = document.createElement('div');
  //   document.querySelector('.demo-container').appendChild(rect);
  //   rect.classList.add('rect');
  //   rect.style.width = w + 'px';
  //   rect.style.height = h + 'px';
  //   rect.style.left = img.offsetLeft + x + 'px';
  //   rect.style.top = img.offsetTop + y + 'px';
  // }
  sleep(m: number) {
    return new Promise((r) => setTimeout(r, m));
  }

  getRecorder(stream: any) {
    // 获取录屏类型
    this.logger += `【6. startRecording ,mime} 】${this.mineType}`;

    // 按下Enter后自动添加到列表
    // fromEvent<KeyboardEvent>(this.AddTodoInput.nativeElement, 'keyup')
    // .pipe(
    //   filter((event) => event.key === 'Enter'),
    //   map((event) => (<HTMLInputElement>event.target).value),
    //   map((title) => title.trim()),
    //   filter((title) => title !== '')
    // )
    // .subscribe((title) => {
    //   this.store.dispatch(addTodo({ title }))
    //   this.AddTodoInput.nativeElement.value = ''
    // })

    this.recorder = window.RecordRTC(stream, {
      type: 'video',
      // only for video track
      videoBitsPerSecond: this.videoBitsPerSecond,
      mimeType: this.mineType,
      // mimeType: 'video/webm',
    });
    window.stream = stream;
    this.recorder.startRecording();
    // await this.sleep(3000);
    // await this.recorder.stopRecording();
    // 4s后停止录制
    this.circle();
  }

  circle() {
    setTimeout(() => {
      this.informationTitle.innerHTML = '请点头～～';
    }, 2 * 1000);
    setTimeout(() => {
      this.endVideo();
    }, 4 * 1000);
  }
  endVideo() {
    this.logger += `【7. stopRecording } 】`;
    this.recorder.stopRecording(async () => {
      this.requestAnimationFrame && window.cancelAnimationFrame(this.requestAnimationFrame);
      await this.sleep(1000);
      window.stream
        .getVideoTracks()
        .concat(window.stream.getAudioTracks())
        .forEach((track: any) => {
          track.stop();
          this.uploadVideo();
        });
    });
  }
  uploadVideo() {
    const videoFile = this.recorder.getBlob();
    const size = this.bytesToSize(videoFile.size);
    this.logger += `【8. start upload video size: ${size} 】`;

    this.getVideoStream(this.targetVideo, window.stream);
  }
  // throw new Error('Method not implemented.');

  bytesToSize(e: number) {
    if (0 === e) return '0 Bytes';
    let t = parseInt(String(Math.floor(Math.log(e) / Math.log(1024))), 10);
    return (e / Math.pow(1e3, t)).toPrecision(3) + ' ' + ['Bytes', 'KB', 'MB', 'GB', 'TB'][t];
  }

  //调用成功 将摄像头 显示出来
  private getVideoStream(ele: ElementRef, stream: any) {
    this.buffer = stream;
    console.log('this.target.nativeElement:', ele);
    if (ele.nativeElement.mozSrcObject !== undefined) {
      ele.nativeElement.mozSrcObject = this.buffer;
    } else {
      try {
        ele.nativeElement.srcObject = this.buffer;
      } catch (error) {
        ele.nativeElement.src = window.URL && window.URL.createObjectURL(this.buffer);
      }
    }
    // ele.nativeElement?.play();
  }

  tackPhoto() {
    // 为什么调用getObjectFitSize，因为摄像头获取的图片和绘制的图片大小和区域可能不一致
    // 所以需要把人脸置于屏幕中间，绘制中间部分图片,如果不需要直接调用第二种方式
    const { sx, sy, swidth, sheight, x, y, width, height } = this.getObjectFitSize(
      'cover',
      this.facevideo.clientWidth,
      this.facevideo.clientHeight,
      window.videoWidth,
      window.videoHeight
    );
    this.facecontext.drawImage(this.facevideo, sx, sy, swidth, sheight, x, y, width, height);
    // 第二种方式
    // facecontext.drawImage(facevideo, 0, 0, facevideo.clientWidth, facevideo.clientHeight);
    var snapData = this.facecanvas.toDataURL('image/png');
    var imgSrc = 'data:image/png;' + snapData;
    // document.querySelector("img").src = imgSrc;

    sessionStorage.setItem('faceImage', imgSrc);
    // history.go(-1);
    // history.back();
    this.facevideo.srcObject.getTracks().forEach((track: { stop: () => any }) => track.stop());
    // 取消监听
    this.tra.stop();
    this.flag = false;
  }

  /**
   * 计算图片裁剪或者摆放位置
   * @param {*} type  contain, cover 暂时只兼容这两个模式
   * @param {*} containerWidth  容器宽度
   * @param {*} containerHeight  容器高度
   * @param {*} imgWidth   图片宽度
   * @param {*} imgHeight  图片高度
   * @return {*} canvas drawImage的所有入参
   */
  getObjectFitSize(type = 'cover', containerWidth: any, containerHeight: any, imgWidth: any, imgHeight: any) {
    let radio = 1, // 容器与图片的比例
      sx = 0, // 开始剪切的 x 坐标位置。
      sy = 0, // 开始剪切的 y 坐标位置。
      swidth = imgWidth, // 被剪切图像的宽度。
      sheight = imgHeight, // 被剪切图像的高度。
      x = 0, // 在画布上放置图像的 x 坐标位置。
      y = 0, // 在画布上放置图像的 y 坐标位置。
      width = containerWidth, // 要使用的图像的宽度（伸展或缩小图像）。
      height = containerHeight; // 要使用的图像的高度（伸展或缩小图像）。
    let cWHRatio = containerWidth / containerHeight;
    let iWHRatio = imgWidth / imgHeight;
    if (type === 'cover') {
      // cover模式，需要裁剪
      if (iWHRatio >= cWHRatio) {
        // 横图，高先匹配，裁剪宽度
        radio = containerHeight / imgHeight;
        sx = (imgWidth - containerWidth / radio) / 2;
        swidth = containerWidth / radio;
        sheight = imgHeight;
      } else {
        // 竖图，宽先匹配，裁剪高度
        radio = containerWidth / imgWidth;
        sy = (imgHeight - containerHeight / radio) / 2;
        swidth = imgWidth;
        sheight = containerHeight / radio;
      }
    } else if (type === 'contain') {
      if (iWHRatio >= cWHRatio) {
        // 横图，宽先匹配，高度自适应
        radio = containerWidth / imgWidth;
        y = (containerHeight - imgHeight * radio) / 2;
        height = imgHeight * radio;
      } else {
        // 竖图，高先匹配，宽度自适应
        radio = containerHeight / imgHeight;
        x = (containerWidth - imgWidth * radio) / 2;
        width = imgWidth * radio;
      }
    }
    return {
      sx,
      sy,
      swidth,
      sheight,
      x,
      y,
      width,
      height,
    };
  }
  public openCamera() {
    let video = document.getElementById('video');
    let canvas: any = document.getElementById('canvas');
    let context = canvas?.getContext('2d');

    let tracker = new tracking.ObjectTracker('face');
    tracker.setInitialScale(4);
    tracker.setStepSize(2);
    tracker.setEdgesDensity(0.1);

    this.trackerTask = tracking.track('#video', tracker, { camera: true });

    tracker.on('track', function (event: any) {
      context.clearRect(0, 0, canvas.width, canvas.height);

      event.data.forEach(function (rect: any) {
        context.font = '11px Helvetica';
        context.fillText('已识别到人脸，请点击拍照', 100, 40);
        context.strokeStyle = '#a64ceb';
        context.strokeRect(rect.x, rect.y, rect.width, rect.height);
      });
    });
  }
  public submit() {
    let canvas: any = document.getElementById('canvas');
    let context = canvas.getContext('2d');
    let video = document.getElementById('video');
    context.drawImage(video, 0, 0, 500, 400);
    canvas.toBlob((blob: any) => {
      console.log('上传成功');
    });
  }
  public destroyed() {
    // 停止侦测
    this.trackerTask?.stop();
    // 关闭摄像头
    this.trackerTask?.closeCamera();
  }
}
