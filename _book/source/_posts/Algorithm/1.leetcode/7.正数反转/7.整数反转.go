/*
 * @lc app=leetcode.cn id=7 lang=golang
 *
 * [7] 整数反转
 */

// @lc code=start
func reverse(x int) int {
	// 取最大 最小int32   2^32-1
	max,min:=math.MaxInt32,math.MinInt32
	fmt.Print(max)
	fmt.Print(math.Pow(2,31))
	res:=0
	for ;x!=0;x/=10{
		newres := res*10+x%10
		if newres>max||newres<min{ // 这个也行吧
			return 0
		}
		res=newres
	}
	return res
}
// @lc code=end

