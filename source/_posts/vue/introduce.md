

# VUE

淘宝 NPM 镜像是一个完整 npmjs.org 镜像，你可以用此代替官方版本(只读)，同步频率目前为 10分钟 一次以保证尽量与官方服务同步。
你可以使用淘宝定制的 cnpm (gzip 压缩支持) 命令行工具代替默认的 npm:
$ npm install -g cnpm --registry=https://registry.npm.taobao.org
这样就可以使用 cnpm 命令来安装模块了：

[Vue.js 教程](http://www.runoob.com/vue2/vue-install.html)

$  cnpm install [name]
$ cnpm install --global vue-cli
 创建一个基于 webpack 模板的新项目
$ vue init webpack my-project
这里需要进行一些配置，默认回车即可
       To get started:
       

```   
 cd my-project
 npm install
 npm run dev
```
Documentation can be found at https://vuejs-templates.github.io/webpack
进入项目，安装并运行：

$ cd my-project
$ cnpm install
$ cnpm run dev
 DONE  Compiled successfully in 4388ms

> Listening at http://localhost:8080
```

## 插件 vscode

1. [nodejs全局安装和本地安装的区别](https://www.cnblogs.com/PeunZhang/p/5629329.html)

2. [prettier只关注格式化，并不具有lint检查语法等能力](https://juejin.im/post/5a791d566fb9a0634853400e)
> 配置文件出现prettier.
2. 加入scss的编译支持
    
```
    npm install --save-dev sass-loader
npm install --save-dev node-sass
```
3. 生命周期函数(钩子函数)
组件挂载以及组件更新、销毁时，触发的一系列的方法，

4. 请求数据的模版
    * vue-resource
    * axios
    * fetch-jsonp
5. 为了支持es6语法检查，需要安装babel-eslint，eslint-plugin-import，eslint-plugin-promise
6. 为了继承airbnb扩展，需要安装[eslint-config-airbnb]，eslint-pugin-jsx-a11y，
7. 为了实现html和vue检测，需要安装eslint-plugin-html,
为了实现react语法规范检查，需要安装eslint-plugin-react
8. [eslint 的三大通用规则](https://blog.csdn.net/txl910514/article/details/76178988)

9. [nodejs全局安装和本地安装的区别](https://www.cnblogs.com/PeunZhang/p/5629329.html)

airbnb 规范在 npm 中有两个版本

eslint-config-airbnb-base 包含了针对 ES6+ 代码的检查
eslint-config-airbnb 在 eslint-config-airbnb-base 的基础上增加了对 react 和 jsx 语法的检查。 
以上两种根据自己实际情况选择即可。以下以 eslint-config-airbnb 为例演示。


查看依赖包版本：

npm info "eslint-config-airbnb@latest" peerDependencies



5. Promise对象，就是代表了未来某个将要发生的事件（通常是一个异步操作）。它的好处在于，有了Promise对象，就可以将异步操作以同步操作的流程表达出来，避免了层层嵌套的回调函数
6. 箭头函数表达式的语法比函数表达式更短，并且没有自己的this，arguments，super或 new.target。这些函数表达式更适用于那些本来需要匿名函数的地方，并且它们不能用作构造函数。
7. 父子组件传值的三种方式
    
```
    //父组件给子组件传值 
    绑定动态属性方法
    <header :title="title"></header>
    在子组件中通过props 接受父组件传过来的数据
    props:['title']
    props:{
        'title':String,
        'header':Object
    }
    直接在子组件里面引用


​    
​    //子组件主动获取父组件的数据和方法
​    this.$parent.数据
​    this.$parent.方法
​    
    父组件获取子组件的数据和方法
    <v-header ref="header"></header>
    this.$refs.header.属性
    this.$refs.header(引用时定义ref='header').方法
    
    // 非父子组件的传值方法
    vuex方式  更简单
    1. 新建js文件，引入vue，
    import Vue from 'vue'
    var vueEvent = new Vue()
    export default vueEvent
    2.  在广播的地方引入定义的实例
    vueEvent.$emit('to-news', this.msg)
    3. vueEvent.$on('to-news', function (data) {
      console.log(data)
    })
    
    ```

vue-loader依赖postcss 插件，解决浏览器兼容问题


.babelrc 文件的作用： 通常es6，浏览器不支持，通过.babelrc 将其编译成es5
stage-0  stage0到3  
stage-2  stage2到3    包括一些es6存在，但es5中没有的语法
comments  转换后的代码不生成注释
"vue": "^2.5.2", 最低安装版本
webpack :前端编译打包的工具

//    "dev": "webpack-dev-server --inline --progress --config build/webpack.dev.conf.js",

<!--vue-loader 依赖postcss 插件，自动搞定css兼容问题-->

[flex设置成1和auto有什么区别](https://segmentfault.com/q/1010000004080910)
.item {flex: 1;}
.item {
    flex-grow: 1;
    flex-shrink: 1;
    flex-basis: 0%;
}



@media(-webkit-min-device-pixel-ratio: 1.5),(min-device-pixel-ratio: 1.5)
dpr 的概念，物理像素是设备像素的两倍

增加一个伪类 after  将其设置为一条线 在dpr为2 或3 的手机上，通过mix- 做缩放，达到一像素的效果
mixin.style：  css 预处理器提供的一个方法，

```
border-1px($color)
  position: relative
  &:after
    display: block
    position: absolute
    left: 0
    bottom: 0
    width: 100%
    border-top: 1px solid $color
    content: ''

// 针对dpi=1.5 设备 进行缩放
@media (-webkit-min-device-pixel-ratio: 1.5),(min-device-pixel-ratio: 1.5)
  .border-1px
    &::after
      -webkit-transform: scaleY(0.7)
      transform: scaleY(0.7)
```

[



Web前端面试题第八道—绝对定位与相对定位](http://www.php.cn/div-tutorial-359954.html)
父级的相对定位：是要把子DIV的绝对定位的起点以父DIV左上角为起点，如果没有就默认屏幕左上角了。
子DIV的绝对定位：是无视父DIV下别的元素。然后就是left:0; top:0; 这个是用来定位的

v-for="(item,index)"
这是ESLint的功能。对vue进行了eslint检查。
那么我们就把eslint对该插件的检查关闭，
在vscode中，打开 文件>首选项>设置 找到 
vetur.configuration 把  
"vetur.validation.template": true  改成
"vetur.validation.template": false

v-for="item in items" :key="item.id"

