---
title: 代理
date: 2021-05-24 22:07:58
tags: [算法, leetcode]
categories:
  - Algorithm
description: 代理
top: true
---



前言：先看[反射](./reflect.md)

## 代理模式定义

代理模式(Proxy Design Pattern)：在不改变原始类 (或叫被代理类)代码的情况下，通过引入代理类来给原始类附加功能。
如果根据代理类字节码的创建时机来分类，可以分为静态代理和动态代理：

静态：在程序运行前就已经存在代理类的字节码文件，代理类和原始类的关系在运行前就确定了。
动态：代理类源码是在程序运行期间由JVM根据反射等机制动态的生成，所以在运行前并不存在代理类的字节码文件



动态代理的应用十分广泛，很多有名的框架都用到了动态代理，比如spring aop，mybatis，Hibernate，rpc等等，甚至我们日常开发中一些 非功能性需求–监控、 统计、鉴权、限流、事务、幂等、日志–也是基于动态代理实现的。由此可见，掌握动态代理对我们的开发工作或阅读框架源码是非常有帮助的。



新需求：收集接口请求的原始数据，比如记录方法的访问时间，及处理时长。

首先分析一下需求，很明显这样的需求与业务代码本身并没有什么关系，任何业务代码都可能面临这种需求，所以直接在login方法中添加代码的方法，破坏了业务类的单一原则，也增加了代码的冗余程度。

为了将框架代码和业务代码解耦，代理模式就派上用场了。



## 静态代理





### 优缺点

- 优点：代码结构简单，较容易实现
- 缺点：无法适配所有代理场景，如果有新的需求，需要修改代理类，「不符合软件工程的开闭原则」





## 动态代理

### JDK

JDK动态代理，有两个关键类：**java.lang.reflect.InvocationHandler** 和 **java.lang.reflect.Proxy**。



从 $Proxy0 的代码中我们可以发现：

* $Proxy0 继承了 Proxy 类（这也是JDK动态代理为什么不能代理类的直接原因），并且实现了被代理的所有接口，以及equals、hashCode、toString等方法

* $Proxy0 继承了 Proxy 类， 每个代理类都有一个参数为 InvocationHandler的构造方法

* 类和所有方法都被 public final 修饰，所以代理类只可被使用，不可以再被继承

* 每个方法都有一个 Method 对象来描述，Method 对象在static静态代码块中创建，以 m + 数字 的格式命名

* 调用方法的时候通过 super.h.invoke(this, m1, (Object[])null); 调用，其中的 super.h.invoke 实际上是在创建代理的时候传递给 Proxy.newProxyInstance 的 DynamicProxyHandler 对象，它继承 InvocationHandler 类，负责实际的调用处理逻辑

  而 DynamicProxyHandler 的 invoke 方法接收到 method、args 等参数后，进行一些处理，然后通过反射让被代理的对象 proxied 执行方法。



### CGLIB

CGLIB（Code generation Library） 不是 JDK 自带的动态代理，它需要导入第三方依赖，它是一个字节码生成类库，能够在运行时动态生成代理类。

与 JDK 动态代理不同的是，CGLIB不仅能够为 Java接口 做代理，而且能够为普通的 Java类 做代理。

添加maven依赖：

<dependency>
  <groupId>cglib</groupId>
  <artifactId>cglib-nodep</artifactId>
  <version>3.3.0</version>
</dependency>
1
2
3
4
5
CGLIB 代理，有两个核心的类：MethodInterceptor接口和Enhancer类，MethodInterceptor类似于上述的InvocationHandler，Enhancer类似于Proxy。



CGLIB 创建动态代理类的模式是：

- 查找目标类上的所有非final 的public类型的方法定义；

- 将这些方法的定义转换成字节码；

- 将组成的字节码转换成相应的代理的class对象；

- 实现 MethodInterceptor接口，用来处理对代理类上所有方法的请求

  





### 比较

JDK动态代理：基于Java反射机制实现，必须要实现了接口的业务类才能用这种办法生成代理对象。
cglib动态代理：基于ASM机制实现，通过生成业务类的子类作为代理类。

JDK Proxy 的优势：

* 最小化依赖关系，减少依赖意味着简化开发和维护，JDK 本身的支持，可能比 cglib 更加可靠。

* 平滑进行 JDK 版本升级，而字节码类库通常需要进行更新以保证在新版 Java 上能够使用。

* 代码实现简单。

基于 cglib 框架的优势：

* 无需实现接口，达到代理类无侵入
* 只操作我们关心的类，而不必为其他相关类增加工作量。
* 高性能





参考： https://blog.csdn.net/sky__fall/article/details/109829936

