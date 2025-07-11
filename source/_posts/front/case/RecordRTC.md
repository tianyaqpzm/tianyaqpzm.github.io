





[RecordRTC github地址](https://github.com/muaz-khan/RecordRTC)





[前端人脸识别框架tracking.js，解决ios浏览器调摄像头黑屏的问题，兼容pc、安卓、ios。](https://blog.csdn.net/qq_24692477/article/details/106024088)



[前端人脸识别框架tracking.js，解决ios浏览器调摄像头黑屏的问题，兼容pc、安卓、ios。](https://blog.csdn.net/qq_24692477/article/details/106024088)

[人脸识别JavaScript也可以轻松搞定](https://www.toutiao.com/article/6661849672778252814/?timestamp=1575847785&app=news_article&group_id=6661849672778252814&req_id=2019120907294501001404009123D82470&wid=1672647563775)



[trackingjs的官网](https://trackingjs.com/ )。

引入tracking-min.js，根据需要引入face-min.js、eye-min.js、mouth-min.js。



### getUserMedia

[getUserMedia详解](https://developer.mozilla.org/zh-CN/docs/Web/API/MediaDevices/getUserMedia)

```
// 想要获取一个最接近 1280x720 的相机分辨率
var constraints = { audio: true, video: { width: 1280, height: 720 } };

var constraints = { video: { frameRate: { ideal: 10, max: 15 } } };

```



当请求包含一个 ideal（应用最理想的）值时，这个值有着更高的权重，意味着浏览器会先尝试找到最接近指定的理想值的设定或者摄像头（如果设备拥有不止一个摄像头）。





​    1、video.play() 可以开启摄像头，

​    tracking.track('#video', tracker,{camera:true});也可以调用摄像头，提前加载tracker。去掉camera：true，否则会闪屏。

​    2、手机端使用canvas把video视频流输出的时候，注意画布的大小，宽高不要超过屏幕的宽高，

​    var vm = $(window).width();

3、使用mediaDevices.getUserMedia在ios调用摄像头，需要系统版本11以上，只支持safari浏览器，

ios系统11会显示空白，因为浏览器没有开启摄像头的权限。

ios系统12会显示黑屏，ios系统13可以显示视频流但是一帧一帧的出现。

这些统统都是因为video需要加上playsinline属性 ！



具体使用方法可以查看官网，其中：rect.x, rect.y, rect.width, rect.height这四个参数表示左上角的坐标和框出来人脸的大小。





## 一、基本介绍

### 1，什么是 dat.GUI?

dat.GUI 是一个轻量级的图形用户界面库（GUI 组件），使用这个库可以很容易地创建出能够改变代码变量的界面组件。

- GitHub 主页：https://github.com/dataarts/dat.gui


原文出自：[www.hangge.com](http://www.hangge.com/) 转载请保留原文链接：http://www.hangge.com/blog/cache/detail_1785.html

### 2 使用步骤

（1）首先在页面的 <head> 标签中添加这个库。

|`<script type=``"text/javascript"` `src=``"../libs/dat.gui.js"``></script>` 

（2）定义一个 JavaScript 对象（这里假设叫做 controls），该对象将保存希望通过 dat.GUI 改变的属性。

`var` `controls = ``new` `function` `() {``  ``this``.rotationSpeed = 0.02;``  ``//......``};` 

（3）接下来需要将这个 JavaScript 对象传递给 dat.gui 对象，并设置各个属性的取值范围。

`var` `gui = ``new` `dat.GUI();``gui.add(controls, ``'rotationSpeed'``, 0, 0.5);``//......` 


（4）最后当用户对 dat.GUI 控件进行操作时，controls 里的属性值也会同步修改。我们在程序中直接引用这个属性值就好了。



npm instal dat.gui -D

npm i --save-dev @types/dat.gui



### 3、dat.GUI 打造可视化工具(一)

[dat.GUI 打造可视化工具(一)](http://t.zoukankan.com/fangdongdemao-p-13949773.html)





[微信小程序人脸识别功能（wx.faceDetect）、带扫脸动画、人脸图片获取（upng.js）及位置展示](https://blog.csdn.net/thj13896076523/article/details/126248555)

[H5人脸识别（tracking.js），并保存人脸为图片（也可保存人脸部分）](https://blog.csdn.net/thj13896076523/article/details/126246056?spm=1001.2014.3001.5501)

> 已实现 http://localhost:4200/angular#/video2



[前端录屏功能(JS)](https://blog.csdn.net/weixin_49119584/article/details/121830665)



