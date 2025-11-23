

function myNew(func, ...args) {
    // 创建一个空对象
    const obj = {};
    // 将空对象函数的原型 指向函数的原型
    obj.__proto__ = func.prototype
    // 3 将构造函数的this指向新对象
    let result = func.apply(obj, args)
    // 4 根据返回值判断: 如果func 返回一个对象的话，那要保持this指向这个对象
    return result instanceof Object ? result : obj
}

function Person (name, age){
    this.name = name;
    this.age = age;
}

Person.prototype.say = function() {
    console.log(this.name)
}

let p = myNew(Person, "tianya", 123)
console.log(p)
p.say()


// 测试构造函数返回对象的情况
function Car(brand) {
    this.brand = brand;
    // 返回一个对象
    return {
      brand: brand,
      type: 'sedan'
    };
  }
  
const car1 = myNew(Car, 'Toyota');
console.log(car1); // { brand: 'Toyota', type: 'sedan' }
// 注意：此时返回的对象不会继承Car的原型
Car.prototype.drive = function() { console.log('Driving...'); };
// car1.drive(); // 报错：car1.drive is not a function