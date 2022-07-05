



# npx 使用教程

https://www.ruanyifeng.com/blog/2019/02/npx.html

### 调用项目内部安装

npx 想要解决的主要问题，就是调用项目内部安装的模块。比如，项目内部安装了测试工具 [Mocha](https://www.ruanyifeng.com/blog/2015/12/a-mocha-tutorial-of-examples.html)。

> ```bash
> $ npm install -D mocha
> ```

一般来说，调用 Mocha ，只能在项目脚本和 package.json 的[`scripts`](https://www.ruanyifeng.com/blog/2016/10/npm_scripts.html)字段里面， 如果想在命令行下调用，必须像下面这样。

> ```bash
> # 项目的根目录下执行
> $ node-modules/.bin/mocha --version
> ```

npx 就是想解决这个问题，让项目内部安装的模块用起来更方便，只要像下面这样调用就行了。

> ```bash
> $ npx mocha --version
> ```

npx 的原理很简单，就是运行的时候，会到`node_modules/.bin`路径和环境变量`$PATH`里面，检查命令是否存在。



## 升级node

/usr/local/bin/n -> /usr/local/lib/node_modules/n/bin/n

1.首先安装n模块：

sudo npm install -g n

2.升级node.js到最新稳定版
 sudo n stable

3.升级到最新版
 sudo n latest

4.n后面也可以跟随版本号,升级到任意版本
 sudo n v0.10.26或sudo n 0.10.26

5.切换使用版本
 sudo n 7.10.0

6.删除制定版本
 sudo n rm 7.10.0

7.用制定的版本执行脚本
 n use 7.10.0 some.js

8.sudo n
 可以查看所有已安装的node版本，可以根据上下和回车选择要使用的版本





yarn global add  @angular/cli@latest

