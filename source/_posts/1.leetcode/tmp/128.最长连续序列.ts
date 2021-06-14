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
var longestConsecutive = function(nums: number[]): number {
  let numSet: Set<number> = new Set()
  for (const num of nums) {
    numSet.add(num)
  }
  let longestStreak = 0
  for (const num of numSet) {
    if (!numSet.has(num - 1)) {
      let currentNum = num
      let currentStreak = 1
      while (numSet.has(currentNum + 1)) {
        currentNum += 1
        currentStreak += 1
      }
      longestStreak = Math.max(longestStreak, currentStreak)
    }
  }
  return longestStreak
}
// @lc code=end
