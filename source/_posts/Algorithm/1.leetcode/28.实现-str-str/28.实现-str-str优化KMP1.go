/*
 * @lc app=leetcode.cn id=28 lang=golang
 *
 * [28] 实现 strStr()
 */
package main

import (
	"fmt"
)

// @lc code=start
func strStr2(haystack string, needle string) int {
	i, j := 0, 0
	var sLen int = len(haystack)
	var pLen int = len(needle)
	next := getNext(needle)
	for i < sLen && j < pLen {
		if j == 0 || haystack[i] == needle[j] {
			i++
			j++
		} else {
			j = next[j] // 变化处
		}
	}
	if j == pLen {
		return i - j
	} else {
		return -1
	}
}
func getNext(s string) []int {
	res := make([]int, len(s))
	j := -1
	res[0] = -1
	for i := 0; i < len(s)-1; {
		// 迭代  == 递归
		// next[j] = next[ next[j] ]
		if j == -1 || s[i] == s[j] {
			i++
			j++
			// 优化 改动下面4行
			if s[i] != s[j] {
				res[i] = j
			} else {
				// 如果出现了p[j] = p[ next[j] ]咋办呢？
				// 如果出现了，则需要再次递归一次，即令next[j] = next[ next[j] ]
				res[i] = res[j]
			}
		} else {
			j = res[j]

		}
	}
	return res
}

// @lc code=end

func main() {
	needle := "abab"
	haystack := "abacababc"
	// needle2 := "aabaabaaa" // 0 1 0 1 2 3 4 5 2
	// needle3 := "abcaby"
	// haystack3 := "abxabcabcaby"
	next := getNext(needle)
	for i := 0; i < len(next); i++ {
		fmt.Print(next[i], " ")
	}
	fmt.Println(strStr2(haystack, needle))
}
