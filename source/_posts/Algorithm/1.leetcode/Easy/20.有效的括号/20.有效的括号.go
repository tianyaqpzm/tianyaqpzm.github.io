/*
 * @lc app=leetcode.cn id=20 lang=golang
 *
 * [20] 有效的括号
 */
package main

import "fmt"

// @lc code=start
func isValid(s string) bool {
	if s == "" {
		return true
	}
	var stack []uint8 // 定义8 位二进制的 切片，代表栈
	// 单引号，表示byte类型或rune类型，对应 uint8和int32类型，默认是 rune 类型
	m := map[uint8]uint8{
		'}': '{',
		')': '(',
		']': '[',
	}
	for i := 0; i < len(s); i++ {
		if s[i] == '{' || s[i] == '[' || s[i] == '(' {
			stack = append(stack, s[i])

		} else {
			if len(stack) == 0 {
				return false
			}
			if m[s[i]] != stack[len(stack)-1] {
				return false
			}
			stack = stack[:len(stack)-1]
		}
	}
	return len(stack) == 0
}

// @lc code=end
func main() {
	fmt.Println("start")
	fmt.Println(isValid("[]{[]}"))
}
