



## learn + yarn

[yarn和lerna初始化项目](https://juejin.cn/post/7216182414698643517/)







yarn workspance company-ui [**add**/**remove**] lodash  

yarn workspace company-ui add lodash 



```scss
lerna add [内部未发布的包] --scope company-ui // 因为未发布的包使用yarn workspace会报错 
```



### 包发布 lerna publish



### 跨项目模块复用方案

https://blog.csdn.net/qiwoo_weekly/article/details/131587761

优点
1、配置简单灵活
2、支持独立部署，上线
3、运行时加载模块，以及共享依赖，减少应用包体积
4、相比 externals 以及 dll，模块联邦能够更好的做到按需热插拔

缺点
1、必须使用 webpack5
2、模块联邦对runtime 运行时做了大量改造，在运行时要做的事情也因此增加很多，会对我们页面的运行时性能造成一定的负面影响
3、由于是运行时共享，那么远程模块的版本管理也是应该去考虑的问题

