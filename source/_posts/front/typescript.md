



https://www.processon.com/view/link/61b2313b0e3e74683770741d#map







TS核心知识点总结，带你半小时入门 TS

https://jishuin.proginn.com/p/763bfbd2a089





##### TS篇—type 和 interface 的区别

对于 type来说，更多的是对类型的一种复用，比如在项目中需要用到一些比较复杂的或者书写起来很长的类型。我们可以使用 type来直接引用该类型：

type FType = boolean | string | number;

而对于 interface来说，它是正儿八经的用来定义接口类型（约束数类型和属性）的，且接口类型是支持继承和声明合并的。

所以在对于对象结构的类型定义上，建议尽可能的使用 interface，而在合适的场景使用 type。
