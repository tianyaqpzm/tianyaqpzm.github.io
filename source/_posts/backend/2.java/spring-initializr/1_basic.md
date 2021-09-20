## 简介

Spring Initializr [[http://start.spring.io/](https://link.segmentfault.com/?url=http%3A%2F%2Fstart.spring.io%2F)]是引导你快速构建 Spring Boot 项目的不二选择。

它允许你通过简单的操作步骤，就可以构建出一个完整的 Spring Boot 应用程序。你可以通过 Spring Initializr 引导界面构建如下类型的 Spring Boot 应用：

- Web 应用程序
- Restful 应用程序
- Batch 应用程序

Spring Boot 对很多第三方框架提供了良好的支持，可以通过对应的 artifactId 获得他们，这里列举其中的一部分供参考：

- **spring-boot-starter-web-services**:用于构建可与外界交互的 SOAP Web 服务应用程序
- **spring-boot-starter-web**:可用于构建 Web 应用程序或者基于 Restful 风格的应用程序
- **spring-boot-starter-test**:可用于构建并编写单元测试和集成测试
- **spring-boot-starter-jdbc**:构建基于 JDBC 的应用程序
- **spring-boot-starter-hateoas**:通过引入 HATEOAS 功能，让你轻松实现 RESTful 服务
- **spring-boot-starter-security**:使用 Spring Security 对系统用户进行身份验证和鉴权
- **spring-boot-starter-data-jpa**:基于 Hibernate 实现的 Spring Data JPA
- **spring-boot-starter-cache**:开启基于 Spring Framework 的缓存支持
- **spring-boot-starter-data-rest**:使用 Spring Data REST 提供 REST 服务



组件功能：

1、支持输入一系列参数生成SpringBoot工程

2、支持自定义属性、支持不同打包方式、语言、版本 Jar包

3、支持下载、在线预览、分享





功能分析：

选择不同属性、功能项生成的工程不一样

生成的文件不一样

选择不同Jar包，存在依赖性，

扩展点：

支持可扩展的组件：jar包的配置化，

支持可扩展的生成动作： 统计分析，生成后续处理，比如生成仓库



自己的实现：

提供工程，

https://github.com/spring-io/start.spring.io



## 安装

下载 release 包，

1. 项目级的 checkstyle 等编译检查
2. mvn install ,  报错进行 格式代码 mvn spring-javaformat:apply，进行 build



MavenResolverDependencyManagementVersionResolverTests   UT跑不过  @Disable 本地无法联网

1. 入口: simple 模块，启动 默认 8080 端口

启动initiaizer-service-sample  





## 源码分析

1.



