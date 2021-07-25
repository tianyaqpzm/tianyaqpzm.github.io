"use strict";
/*
 * @lc app=leetcode.cn id=128 lang=typescript
 *
 * [128] 最长连续序列
 */
// @lc code=start
/**
 * @param {number[]} nums
 * @return {number}
 */
var longestConsecutive = function (nums) {
    var numSet = new Set();
    for (var _i = 0, nums_1 = nums; _i < nums_1.length; _i++) {
        var num = nums_1[_i];
        numSet.add(num);
    }
    var longestStreak = 0;
    for (var _a = 0, numSet_1 = numSet; _a < numSet_1.length; _a++) {
        var num = numSet_1[_a];
        if (!numSet.has(num - 1)) {
            var currentNum = num;
            var currentStreak = 1;
            while (numSet.has(currentNum + 1)) {
                currentNum += 1;
                currentStreak += 1;
            }
            longestStreak = Math.max(longestStreak, currentStreak);
        }
    }
    return longestStreak;
};
// @lc code=end
