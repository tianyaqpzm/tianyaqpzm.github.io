package main
func twoSum(nums []int, target int) []int{
	hashmap := make(map[int]int) // 声明map
	//var index []int  // 定义切片
	//defaultIndex := []int{-1, -1}  // 定义切片  若是数组[...]   可省略，
	for i, num1 := range nums {   // 循环 索引和数值
		num2 := target - num1
		if _, ok := hashmap[num2]; ok { //判断map是否含有以该值为下标的元素
			//index = append(index, hashmap[num2],i)
			return []int{hashmap[num2],i}
		}
		hashmap[num1] = i
	}
	return []int{}
}
func main() {
	// twoSum(l1,l2)
}
