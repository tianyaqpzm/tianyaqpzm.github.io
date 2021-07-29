/*
 * @lc app=leetcode.cn id=26 lang=golang
 *
 * [26] 删除排序数组中的重复项
 */
package main

// @lc code=start
func removeDuplicates(nums []int) int {
	n := len(nums)
	if n < 2 {
		return n
	}
	l, r := 0, 1
	for r < n {
		if nums[l] < nums[r] {
			l++
			nums[l] = nums[r]
		}
		r++
	}
	return l + 1
}

// @lc code=end
