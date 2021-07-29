/**
 * 手写instanceof
 * instance的原理就是通过在原型链上（proto）能找到就是true，找不到就是false
 */

function myInstanceof(left, right) {
    var proto = left.__proto__;
    var prototype = right.prototype;
    if (proto === null) {
        return false;
    } else if (proto === prototype) {
        return true
    } else {
        06
        return myInstanceof(proto, right)
    }
}

var a = {}
console.log(myInstanceof(a, Array))
console.log(myInstanceof(a, Object))