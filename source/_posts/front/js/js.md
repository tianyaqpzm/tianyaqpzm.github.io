

# JS



> 函数就是一个对象！！有属性和方法



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




