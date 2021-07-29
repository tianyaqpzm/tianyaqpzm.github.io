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
func strStr(haystack string, needle string) int {
	if len(needle) == 0 {
		return 0
	}
	if len(haystack) == 0 {
		return -1
	}

	next := getNextArr(needle)
	i, j := 0, 0
	for ; i < len(haystack); i++ {
		for haystack[i] != needle[j] && j > 0 {
			j = next[j-1]
		}
		if haystack[i] == needle[j] {
			j++
			if j == len(needle) {
				return i - len(needle) + 1
			}
		}
	}
	return -1
}

func getNextArr(s string) []int {
	res := make([]int, len(s))
	maxMatchLen := 0
	for i := 1; i < len(s); i++ {
		// 迭代  == 递归
		for maxMatchLen > 0 && s[maxMatchLen] != s[i] {
			maxMatchLen = res[maxMatchLen-1]
		}
		if s[maxMatchLen] == s[i] {
			maxMatchLen++
		}
		res[i] = maxMatchLen
	}
	return res
}

// @lc code=end

func main2() {
	// needle := "abab"
	// haystack := "abacababc"
	// needle2 := "aabaabaaa" // 0 1 0 1 2 3 4 5 2
	needle3 := "abcaby"
	haystack3 := "abxabcabcaby"
	// next := getNext(needle3)
	// for i := 0; i < len(next); i++ {
	// 	fmt.Print(next[i], " ")
	// }
	fmt.Println(strStr(haystack3, needle3))
}
