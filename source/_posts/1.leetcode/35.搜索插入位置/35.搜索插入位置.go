/*
 * @lc app=leetcode.cn id=35 lang=golang
 *
 * [35] 搜索插入位置
 */
package main

// @lc code=start
func searchInsert(nums []int, target int) int {
	l := len(nums)
	if l == 0 {
		return 0
	}
	for i := 0; i < l; i++ {
		if nums[i] == target || nums[i] > target {
			return i
		}
	}
	return l
}

// @lc code=end
