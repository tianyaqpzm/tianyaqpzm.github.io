/**
 * 手写 instanceof
 * instance的原理就是通过在原型链上（proto）能找到就是true，找不到就是false
 *  一个是对象的原型，一个是函数的原型
 */

function myInstanceof(left, right) {
  var proto = left.__proto__
  var prototype = right.prototype
  if (proto === null) {
    return false
  } else if (proto === prototype) {
    return true
  } else {
    // 关键点： 查询对象的原型链 是不是该函数的原型
    return myInstanceof(proto, right)
  }
}

var a = {}
console.log(myInstanceof(a, Array))
console.log(myInstanceof(a, Object))
