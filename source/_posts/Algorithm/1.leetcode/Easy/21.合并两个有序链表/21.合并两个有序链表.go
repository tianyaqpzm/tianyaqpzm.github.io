package main

/*
 * @lc app=leetcode.cn id=21 lang=golang
 *
 * [21] 合并两个有序链表
 */

// @lc code=start

//ListNode ... Definition for singly-linked list.
type ListNode struct {
	Val  int
	Next *ListNode
}

func mergeTwoLists(l1 *ListNode, l2 *ListNode) *ListNode {
	res := &ListNode{0, nil}
	dummy := res
	for l1 != nil && l2 != nil {
		if l1.Val <= l2.Val {
			dummy.Next = &ListNode{l1.Val, nil}
			l1 = l1.Next
		} else {
			dummy.Next = &ListNode{l2.Val, nil}
			l2 = l2.Next
		}
		dummy = dummy.Next
	}
	if l1 == nil {
		dummy.Next = l2
	}
	if l2 == nil {
		dummy.Next = l1
	}
	return res.Next
}

// @lc code=end
