---
title: Angular Material
date: 2022-12-31 03:30:00
tags: [前端,组件库]
categories:
  -front
description: Angular Material是基于metarial design 的angular UI.
top: true
---



[TOC]

# 简介

Angular Material是基于metarial design 的angular UI.

Material Design 是谷歌推出的全新的设计理念,采用大胆的色彩.流畅的动画播放,以及卡片式的简洁设计.Material Design 风格的设计拥有干净的排版和简单的布局,容易理解,内容才是焦点. Material UI 是一个 CSS 框架和一组实现谷歌 Material Design 设计规范的 React 组件.





# 安装

npm install --save @angular/material @angular/cdk @angular/animations



引入一个material UI 的css样式

在全局css文件style.css 文件中引入material的默认UI样式

@import "~@angular/material/prebuilt-themes/indigo-pink.css";

也可以不引用，使用自定义的css样式

某些组件需要用到第三方js依赖，比如(mat-slide-toggle, mat-slider, matTooltip)需要用到HammerJS，需要自己将依赖安装到应用程序文件里

npm:

npm install --save hammerjs

Yarn:

yarn add hammerjs

安装icon组件(如果需要)

在index.html 里添加声明：

icon组件还可以使用自定义的svg格式的icon。

在组件页面文件中使用需要用到的组件

注意：安装的时候注意angular material的版本要同angular cli的版本兼容。

## 组件

[官方组件库](https://v13.material.angular.io/components/categories)





# 主题

[Angular Material主题配置](http://t.zoukankan.com/ylp0617-p-6902856.html)
