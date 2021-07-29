/*
 * @lc app=leetcode.cn id=155 lang=javascript
 *
 * [155] 最小栈
 */

// @lc code=start
/**
 * initialize your data structure here.
 */
var MinStack = function () {
    this.stack = []
    this.minV = Number.MAX_VALUE

};

/** 
 * @param {number} val
 * @return {void}
 */
MinStack.prototype.push = function (val) {
    const minV = this.minV
    if (x < this.minV) {
        this.minV = x
    }
};

/**
 * @return {void}
 */
MinStack.prototype.pop = function () {

};

/**
 * @return {number}
 */
MinStack.prototype.top = function () {

};

/**
 * @return {number}
 */
MinStack.prototype.getMin = function () {

};

/**
 * Your MinStack object will be instantiated and called as such:
 * var obj = new MinStack()
 * obj.push(val)
 * obj.pop()
 * var param_3 = obj.top()
 * var param_4 = obj.getMin()
 */
// @lc code=end