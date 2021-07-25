---
title: file
date: 2019-08-05 23:59:20
categories: dir
tags: [tag1, tag2]
---

亿级流量电商网站的商品详情页系统，架构讲解，项目实战：高并发+高可用的系统缓存架构

真正能支撑高并发以及高可用的复杂系统中的**缓存架构**有哪些东西？

（1）如何让 redis 集群支撑几十万 QPS 高并发 + 99.99%高可用 +TB 级海量数据 + 企业级数据备份与恢复？

redis 企业级集群架构

（2）如何支撑高性能以及高并发到极致？ 同时给缓存架构最后到安全保护层？

（nginx + lua）+ redis + ehcache 的三级缓存架构

（3）高并发场景下，如何解决数据库与缓存双写的时候数据不一致的情况？

企业级的完美数据库+ 缓存双写一致性解决方案

（4）

![image-20191216235706283](file/image-20191216235706283.png)

              return new Promise((resolve, reject) => {}); // 返回pending状态
              // return (new Promise((resolve, reject)=>{reject()}));//返回reject状态 会被最后catch捕获。
