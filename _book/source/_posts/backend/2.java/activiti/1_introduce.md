





ProcessEngineConfiguration:流程引擎配置。 

ProcessEngine:流程引擎 

核心七大接口 

RepositoryService：提供一系列管理流程部署和流程定义的API。 

RuntimeService：在流程运行时对流程实例进行管理与控制。 

TaskService：对流程任务进行管理，例如任务提醒、任务完成和创建任务等。 

IdentityService：提供对流程角色数据进行管理的API，这些角色数据包括用户组、用户及它们之间的关系。 

ManagementService：提供对流程引擎进行管理和维护的服务，提供对activiti数据库的直接访问【一般不用】 

HistoryService：对流程的历史数据进行操作，包括查询、删除这些历史数据。FormService：表单服务 




pom依赖
```
<dependency>
   <groupId>org.activiti</groupId>
   <artifactId>activiti-spring-boot-starter-basic</artifactId>
   <version>6.0.0</version>
</dependency>
```

yml 配置
```
spring:
  activiti:
    check-process-definitions: true #自动检查、部署流程定义文件
    database-schema-update: true #自动更新数据库结构
    history-level: full #保存历史数据级别设置为full最高级别，便于历史数据的追溯
    # process-definition-location-prefix: classpath:/processes/ #流程定义文件存放目录
    #process-definition-location-suffixes: #流程文件格式
    #  - **.bpmn20.xml
    #  - **.bpmn
```

springboot环境下不再以activiti.cfg.xml文件的形式配置，activiti使用starter配置后属于spring下，所以在yml里配置。



idea 和eclipse编译器的区别：

idea对activiti工作流的支持没有eclipse那么好, 一般我们都是使用actiBPM插件来支持工作流。
但是有一个问题，就是创建bpmn文件时不会自动创建png流程图。

后来找到问题所在，原来是因为有一个jbpm2.0的插件没有开启：https://blog.csdn.net/wk52525/article/details/79362904

乱码：

.vmoptions 两个文件都最后加上一行：-Dfile.encoding=UTF-8





**3、执行编译命令，指向自己maven配置文件。**
mvn compile -Dmaven.test.skip=true -s /myapp/apache-maven-3.5.0/conf/settings.xml

modules文件夹下就存储了Activiti6.0项目所有模块的java源文件



# [Activiti6系列（4）- 三个war包的数据源及密码修改](https://www.cnblogs.com/fulongyuanjushi/p/11291952.html)

**1、找到Activiti6.0.zip中的三个war包**
activiti-app.war
activiti-admin.war
activiti-rest.war

**2、将三个war包拷贝到Tomcat下，运行后会自行解压为三个文件夹。**
PS：这里注意，运行过后，最好将原有war包删除掉，以免后续修改配置重启时war包再次解压将原来的文件夹覆盖。

**3、启动tomcat，访问http://127.0.0.1:8080/activiti-app**
默认账号密码：admin test

http://127.0.0.1:8080/activiti-admin

http://127.0.0.1:8080/activiti-app





log4j.logger.org.activiti.engine.impl.persistence.entity=DEBUG





```
部署流程定义
 ACT_RE_DEPLOYMENT 部署对象表
 
 ACT_RE_PROCDEF  流程定义表
 
 act_ge_bytearray  资源文件表
 
 act_ge_Property  主键生成策略
 
```



