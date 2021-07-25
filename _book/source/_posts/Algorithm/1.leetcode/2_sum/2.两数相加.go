package main
import "fmt"
/*
 * @lc app=leetcode.cn id=2 lang=golang
 *
 * [2] 两数相加
 */

// @lc code=start
/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */
 // 定义类
type ListNode struct {
	Val int
	Next *ListNode
}
func addTwoNumbers(l1 *ListNode, l2 *ListNode) *ListNode {
	if l1 == nil && l2 == nil {
		return nil
	}
	if l1 == nil {
		return l2
	}
	if l2 == nil {
		return l1
	}

	sum := l1.Val + l2.Val
	nextNode := addTwoNumbers(l1.Next, l2.Next)
	if sum < 10 {
		return &ListNode{ Val: sum, Next: nextNode }
	} else {
		tempNode := &ListNode{
			Val: 1,
			Next: nil,
		}
		return &ListNode{
			Val: sum - 10,
			Next: addTwoNumbers(nextNode, tempNode),
		}
	}
}
// @lc code=end



func main() {
	var l1  = &ListNode{2,&ListNode{4,&ListNode{3,nil}}}
	l2  := &ListNode{5,&ListNode{6,&ListNode{4,nil}}}
	addTwoNumbers(l1,l2)
	fmt.Print("Hello from goProject.")
}