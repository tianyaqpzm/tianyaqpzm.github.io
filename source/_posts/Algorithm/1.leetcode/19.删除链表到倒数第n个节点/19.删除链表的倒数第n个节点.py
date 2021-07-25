#
# @lc app=leetcode.cn id=19 lang=python3
#
# [19] 删除链表的倒数第N个节点
#

# @lc code=start
# Definition for singly-linked list.
class ListNode:
    def __init__(self, x):
        self.val = x
        self.next = None

class Solution:
    def removeNthFromEnd(self, head: ListNode, n: int) -> ListNode:
        dummy = ListNode(0)  
        dummy.next = head  
        p, q = dummy, dummy  
          
        # first 'q' go n step  
        for i in range(n):  
            q = q.next  
  
        # q & p  
        while q.next:  
            p = p.next  
            q = q.next  
    #    待删除的是  p.next
        rec = p.next  
        p.next = rec.next  
        del rec  
        return dummy.next  
# @lc code=end

