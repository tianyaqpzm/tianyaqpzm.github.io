#
# @lc app=leetcode.cn id=24 lang=python3
#
# [24] 两两交换链表中的节点
#


# @lc code=start
# Definition for singly-linked list.
class ListNode:
    def __init__(self, x):
        self.val = x
        self.next = None


class Solution:
    def swapPairs(self, head: ListNode) -> ListNode:
        pre = ListNode(0)
        pre.next = head

        curr = head
        head = pre
        while curr and curr.next:
            pre.next = curr.next
            curr.next = pre.next.next
            pre.next.next = curr
            pre = curr
            curr = curr.next
        return head.next


# @lc code=end
