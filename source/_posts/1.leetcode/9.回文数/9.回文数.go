/*
 * @lc app=leetcode.cn id=9 lang=golang
 *
 * [9] 回文数
 */
package main

// @lc code=start
func isPalindrome(x int) bool {
	res := 0
	reminder := 0
	temp := x
	for temp > 0 {
		res *= 10
		reminder = temp % 10
		res += reminder
		temp /= 10
	}
	if res == x {
		return true
	}
	return false
}

// @lc code=end

func main() {

}
