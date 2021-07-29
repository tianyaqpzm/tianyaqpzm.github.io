/*
 * @lc app=leetcode.cn id=27 lang=golang
 *
 * [27] 移除元素
 */
package main

import (
	"fmt"
)

// @lc code=start
func removeElement(nums []int, val int) int {
	if len(nums) == 0 {
		return 0
	}
	s := 0
	for i := 0; i < len(nums)-s; i++ {
		if nums[i] == val {
			s++
			// 相当于 delete num[j]
			for j := i; j < len(nums)-1; j++ {
				nums[j] = nums[j+1]
			}
			i--
		}
	}
	return len(nums) - s
}

// @lc code=end

func main() {
	list := []int{3, 2, 2, 3}
	fmt.Println(removeElement(list, 3))
}
