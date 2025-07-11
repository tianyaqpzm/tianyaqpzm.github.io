---
title: 汇总
date: 2021-10-07 16:18:00
tags: [前端, html]
categories:
  - 前端
description: 前端
top: false
---



[TOC]



# 知识点

## 1、什么是跨域 CORS

跨域资源共享（简称 CORS）是一种机制，它使用额外的 HTTP 标头告诉浏览器让在一个域中运行的 Web 应用程序有权访问来自不同域的服务器的选定资源。

### 为什么跨域会发生

出于安全原因，浏览器会限制从脚本中发起的跨域 HTTP 请求。 例如，XMLHttpRequest 和 Fetch API 遵循同源策略。 这意味着使用这些 API 的 Web 应用程序只能从加载应用程序的同一源请求 HTTP 资源，除非来自其他源的响应包含正确的 CORS 标头。



### 如何修复跨域问题

在解决 Angular 应用程序中的 CORS 问题时，我们可以通过两种不同的方式解决该问题：

#### 使用 Angular CLI 代理

我们可以使用 Webpack 提供的代理解决 CORS 问题。 首先，打开 Angular 项目并在 src 目录中创建一个名为 **proxy.conf.json** 的新文件，其内容如下：

```json
{
    "/api": {
        "target": "http://localhost:3000",
        "secure": false
    }
}
```

这将告诉我们的开发服务器代理将 /api 端点发出的任何请求转发到 localhost:3000。 这个文件本质上是使用 [Webpack](https://www.jiyik.com/w/webpack) 的 **devServer.proxy** 的配置。

接下来，我们需要通过 **angular.json** 使用 proxyConfig 键将 Angular 指向此文件，从而让 Angular 了解我们的代理配置：

```json
"architect": {
    "serve": {
        "builder": "@angular-devkit/build-angular:dev-server",
        "options": {
            "browserTarget": "your-application-name:build",
            "proxyConfig": "src/proxy.conf.json"
       }
    }
}
```

最后，配置到位后，现在我们应该能够在没有 CORS 问题的情况下为应用程序提供服务。 如果需要更改代理配置，请确保在这样做后重新启动我们的开发环境。

```bash
$ ng serve
```

#### 使用正确的标头 - headers

CORS 问题也可以通过从服务器发送正确的 HTTP 标头来解决。 大多数语言和框架已经提供了现有的包或 API 从而以简单的方式配置正确的标头。 例如，如果使用 Express，则可以使用 cors 包来解决 CORS 错误：

```javascript
const express = require('express');
const cors = require('cors');
const app = express();
 
app.use(cors());
```

这样就可以轻松解决跨域资源共享的问题了。





