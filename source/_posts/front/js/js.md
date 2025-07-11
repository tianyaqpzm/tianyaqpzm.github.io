

# JS



> 函数就是一个对象！！有属性和方法



# 基础知识

函数声明：

1 . function fn(a,b,c){}

fn(2,3)


手写new
看一下正常使用new

```
function Dog(name){
    this.name = name
}
Dog.prototype.sayName = function(){
    console.log(this.name)
}
var dog = new Dog('小狗')
dog.sayName()
```
结果为小狗

自己手写的new

```
function Dog(name){
    this.name = name
}
Dog.prototype.sayName = function(){
    console.log(this.name)
}

// 上面是本身Dog
function _new(fn,...args){   // ...args为ES6展开符,也可以使用arguments
    //先用Object创建一个空的对象,
    const obj = Object.create(fn.prototype)  //fn.prototype代表 用当前对象的原型去创建
    //现在obj就代表Dog了,但是参数和this指向没有修改
    const rel = fn.apply(obj,args)
    //正常规定,如何fn返回的是null或undefined(也就是不返回内容),我们返回的是obj,否则返回rel
    return rel instanceof Object ? rel : obj
}
var _newDog = _new(Dog,'这是用_new出来的小狗')
_newDog.sayName()
```

结果为这是用_new出来的小狗

总结一下

new相当于上面的哪些代码呢?


new的具体步骤
创建一个空对象 var obj = {}
修改obj.__proto__=Dog.prototype
只改this指向并且把参数传递过去,call和apply都可以
根据规范，返回 null 和 undefined 不处理，依然返回obj



## instanceof
function myInstance(left, right) {
    var proto = left.__proto__;
    var prototype = right.prototype;

    if (proto === null) {
        return false;
    } else if (proto === prototype) {
        return true;
    } else {
        return myInstance(proto, right);
    }
}
var a = {};
console.log(myInstance(a,Array)); //false
console.log(myInstance({}, Object)) //true

instance的原理就是通过在原型链上（proto）能找到就是true，找不到就是false





## 什么是symbol

什么Symbol?

Symbol是ES6中新增的一种数据类型, 被划分到了基本数据类型中

基本数据类型: 字符串、数值、布尔、undefined、null、Symbol

引用数据类型: Object

Symbol的作用

用来表示一个独一无二的值

格式

let xxx=Symbol(‘标识字符串’);

为什么需要Symbol?

为了避免第三方框架的同名属性被覆盖

如何区分Symbol?

在通过Symbol生成独一无二的值时可以设置一个标记

这个标记仅仅用于区分, 没有其它任何含义

如果特殊情况需要读取这个标记的话

symbol类型可以转化为boolean或者字符串，转化为字符串时前面会加上symbol（wyx），不方便

由于以 Symbol 值作为键名，不会被常规方法遍历得到。我们可以利用这个特性，为对象定义一些非私有的、但又希望只用于内部的方法。

注意：symbol并不能实现真正的私有变量的效果，只是不能通过常规的遍历方法拿到symbol类型的属性而已

再来复习一下对象的遍历方法

for (let xx in obj) :i代表key

for (let xx of obj)：不是自带的哈

Object.keys(obj) :返回包含key的数组

Object.values(obj) :返回包含value的数组

Object.getOwnPropertyNames() ：返回包含key的数组

上述的所有方法都是遍历不到symbol类型的（注意，是遍历时取不到symbol，并不是说我们访问不到对象的symbol类型）

可以遍历到symbol的方法：

Object.getOwnPropertySymbols() ：返回对象中只包含symbol类型key的数组

Reflect.ownKeys() ：返回对象中所有类型key的数组（包含symbol）

### 4.symbol自带的方法

symbol.for()

因为symbol类型的值都是独一无二的，但有时，我们希望重新使用同一个 Symbol 值，Symbol.for()方法可以做到这一点。它接受一个字符串作为参数，然后搜索有没有以该参数作为名称的 Symbol 值。如果有，就返回这个 Symbol 值，否则就新建一个以该字符串为名称的 Symbol 值，并将其注册到全局。

let s1 = Symbol.for('foo');

let s2 = Symbol.for('foo');

s1 === s2 // true

symbole.keyFor()

由于Symbol()写法没有登记机制，所以每次调用都会返回一个不同的值。

Symbol.keyFor()方法返回一个已登记的 Symbol 类型值的key。

let s1 = Symbol.for("foo");

Symbol.keyFor(s1) // "foo"

let s2 = Symbol("foo");

Symbol.keyFor(s2) // undefined
