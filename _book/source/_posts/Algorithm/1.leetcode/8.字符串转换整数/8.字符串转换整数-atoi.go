/*
 * @lc app=leetcode.cn id=8 lang=golang
 *
 * [8] 字符串转换整数 (atoi)
 */
package main

import (
	"fmt"
	"math"
	"strings"
)

// @lc code=start
func myAtoi(str string) int {
	strTrim := strings.TrimLeft(str, " ")
	if len(strTrim) == 0 {
		return 0
	}
	sign := 1
	result := 0
	i := 0
	if strTrim[0] == '-' {
		sign = -1
		i = 1
	} else if strTrim[0] == '+' {
		sign = 1
		i = 1
	}
	for ; i < len(strTrim); i++ {
		ch := int(strTrim[i] - '0')
		tem := result*10 + ch
		//判断char 是否是数字
		if ch < 0 || ch > 9 {
			break
		} else if sign == 1 && tem > math.MaxInt32 {
			return math.MaxInt32
		} else if sign == -1 && tem*sign < math.MinInt32 {
			return math.MinInt32
		}
		result = tem
	}
	return result * sign
}

// @lc code=end

func main() {
	fmt.Println("start")
	// fmt.Println(myAtoi("  -42"))
	fmt.Println(myAtoi("4193 with words"))
}
