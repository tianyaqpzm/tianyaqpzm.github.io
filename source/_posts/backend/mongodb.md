---
title: mongoDB
date: 2022-01-16 17:07:58
tags: [后端,数据库]
categories:
  -backend
description: 
top: true
---

# MongoDB

[TOC]

## 简介

MongoDB是一种**文件导向**的数据库管理系统，属于一种通称为**NoSQL**的数据库，是10gen公司旗下的开源产品，其内部数据存储的方式与传统的关系型数据有很大差别。

MongoDB以一种直观文档的方式来完成数据的存储。它很像JavaScript中定义的JSON格式，不过数据在存储的时候MongoDB数据库为文档增加了序列化的操作，最终存进磁盘的其实是一种叫做**BSON**的格式，即**Binary-JSON**。

```mssql
"_id" : "10001",
"score" : {
           "Maths" : 71,
           "English" : 62,
}
"_id" : "10002",
"score" : {
           "Maths" : 81,
           "Chemistry" : 74,
           "Sport" : {
                       "Basketball" : 67,
                       "Badminton" : 76,
                   },
}
```

### MongoDB的应用场景

在另一方面，对开发者来说，如果是因为业务需求或者是项目初始阶段，而导致数据的具体格式无法明确定义的话，MongoDB的这一鲜明特性就脱颖而出了。相比传统的关系型数据库，它**非常容易被扩展**，这也为写代码带来了极大的方便。

不过MongoDB对**数据之间事务关系支持比较弱**，如果业务这一方面要求比较高的话，MongoDB还是并不适合此类型的应用。

另外，MongoDB出现的时机比较晚，还具备一些非常鲜明的特性。比如：

1. 它里面自带了一个名叫**GirdFS的分布式文件系统**，这就为MongoDB的部署提供了很大便利。而像MySQL这种比较早的数据库，虽然市面上有很多不同的分表部署的方案，但这种终究不如MongoDB直接官方支持来得便捷实在。

2. 另外，MongoDB内部还自建了**对map-reduce运算框架的支持**，虽然这种支持从功能上看还算是比较简单的，相当于MySQL里GroupBy功能的扩展版，不过也为数据的统计带来了方便。

3. MongoDB在启动后会将数据库中的数据**以文件映射的方式加载到内存中**。如果内存资源相当丰富的话，这将**极大地提高数据库的查询速度**，毕竟内存的I/O效率比磁盘高多了。

 

但是，作为一个新鲜的事务，MongoDB也存在着很多不足。它在为开发人员提供了便利的情况下，却在**运维上面临着不少难题**，比如：

1. 比起MySQL，MongoDB**没有成熟的运维经验**，需要不断地探索。

2. MongoDB中的**数据存放具有相当的随意性**，不具有MySQL在开始就定义好了。对运维人员来说，他们可能不清楚数据库内部数据的数据格式，这也会数据库的运维带来了麻烦。

### 与MySql 对比

1. 相比较MySQL，**MongoDB数据库更适合那些读作业较重的任务模型**。MongoDB能充分利用机器的内存资源。如果机器的内存资源丰富的话，MongoDB的查询效率会快很多。

2. 在带”_id”插入数据的时候，MongoDB的插入效率其实并不高。如果想充分利用MongoDB性能的话，**推荐采取不带”_id”的插入方式，然后对相关字段作索引来查询**。



## 基本操作



https://www.runoob.com/mongodb/mongodb-create-database.html





## 监控状态



```
db.currentOp()获取当前正在执行的操作
db.serverStatus() 获取服务器的状态
db.serverStatus().connections
db.serverStatus().mem  查看其内存使用情况

db.stat() 获取当前数据库的信息，比如Obj总数、数据库总大小、平均Obj大小等

类似于MySQL的slow log, MongoDB可以监控所有慢的以及不慢的查询。
http://www.mongodb.org/display/DOCS/Database+Profiler

>use test
switched to db test 
>db.setProfilingLevel(2);
{"was" : 0 , "slowms" : 100, "ok" : 1} // "was" is the old setting
>db.getProfilingLevel()


> db.system.profile.find().sort({$natural:-1})
{"ts" : "Thu Jan 29 2009 15:19:32 GMT-0500 (EST)" , "info" :
"query test.$cmd ntoreturn:1 reslen:66 nscanned:0 query: { profile: 2 } nreturned:1 bytes:50" ,"millis" : 0}



```

