/*
 * @lc app=leetcode.cn id=206 lang=typescript
 *
 * [206] 反转链表
 */

// @lc code=start
/**
 * Definition for singly-linked list.
 * class ListNode {
 *     val: number
 *     next: ListNode | null
 *     constructor(val?: number, next?: ListNode | null) {
 *         this.val = (val===undefined ? 0 : val)
 *         this.next = (next===undefined ? null : next)
 *     }
 * }
 */

export class ListNode {
    val: number
    next: ListNode | null
    constructor(val?: number, next?: ListNode | null) {
        this.val = (val === undefined ? 0 : val)
        this.next = (next === undefined ? null : next)
    }
}
function reverseList4(head: ListNode | null): ListNode | null {
    if (head === null || head.next === null) {
        return head;
    }
    // 双指针的写法  平移 prev 和curr  
    // 哔哩哔哩  https://b23.tv/EEHqWQj
    let prev: ListNode | null = null;
    let current: ListNode | null = head;
    while (current !== null) {
        const nextTemp: ListNode | null = current.next;
        current.next = prev;
        prev = current;
        current = nextTemp;
    }
    return prev;
}

/**
 * 由双指针演进而来
 * @param head 
 * @returns 
 */
export function reverseList(head: ListNode | null): ListNode | null {
    // 相当于初始化
    return reverse(head, null);
}
function reverse(curr: ListNode | null, prev: ListNode | null): ListNode | null {
    if (curr == null) {
        return prev;
    }
    let temp = curr.next;
    curr.next = prev;
    // 相当于平移 赋值， 与双指针上的赋值效果一致
    return reverse(temp, curr);
}

function reverseList2(head: ListNode | null): ListNode | null {
    if (head === null || head.next === null) {
        return head;
    }
    let cur: ListNode | null = reverseList2(head.next);
    // 直到倒数第二的节点 才开始更改箭头
    head.next.next = head;
    head.next = null;
    return cur;
}

// @lc code=end

