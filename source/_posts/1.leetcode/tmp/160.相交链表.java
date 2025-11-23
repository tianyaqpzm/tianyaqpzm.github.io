/*
 * @lc app=leetcode.cn id=160 lang=java
 *
 * [160] 相交链表
 */

// @lc code=start
/* *
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) {
 * val = x;
 * next = null;
 * }
 * }
 */
class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }
}

class Solution160 {
    /**
     * 思路： 先都转化为数组，遍历数组，从后往前看(一旦为了比对位置，就需要翻转了。) 如果有想同的一段，则相交
     * 
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode1(ListNode headA, ListNode headB) {
        StringBuilder headAStr = new StringBuilder();
        StringBuilder headBStr = new StringBuilder();
        ListNode fakeHeadA = headA;
        for (int i = 0; headA.next != null; i++) {
            headAStr.append(headA.val);
            headA = headA.next;
        }
        for (int i = 0; headB.next != null; i++) {
            headAStr.append(headB.val);
            headA = headB.next;
        }
        return headA;
    }

    /**
     * 通过置换节点 第二遍的时候走向共同点。。。
     * 假设： [2 6 4] [1 5]
     * 执行结果：
     * 2 6 4 null 1 5 null
     * 1 5 null 2 4 6 null
     * 在没有相交的场景下， 循环两次都会碰到a ,b 都会是 null， 循环结束
     * 
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode a = headA;
        ListNode b = headB;
        while (a != b) {
            a = a == null ? headB : a.next;
            b = b == null ? headA : b.next;
        }
        return a;
    }
}
// @lc code=end
