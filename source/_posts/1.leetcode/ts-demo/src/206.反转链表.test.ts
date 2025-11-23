import { reverseList, ListNode } from './206.反转链表';

// 辅助函数：创建链表
function createLinkedList(arr: number[]): ListNode | null {
    if (arr.length === 0) return null;

    const head = new ListNode(arr[0]);
    let current = head;

    for (let i = 1; i < arr.length; i++) {
        current.next = new ListNode(arr[i]);
        current = current.next;
    }

    return head;
}

// 辅助函数：将链表转换为数组
function linkedListToArray(head: ListNode | null): number[] {
    const result: number[] = [];
    let current = head;

    while (current !== null) {
        result.push(current.val);
        current = current.next;
    }

    return result;
}

// 辅助函数：比较两个链表是否相等
function compareLinkedLists(list1: ListNode | null, list2: ListNode | null): boolean {
    const arr1 = linkedListToArray(list1);
    const arr2 = linkedListToArray(list2);

    if (arr1.length !== arr2.length) return false;

    for (let i = 0; i < arr1.length; i++) {
        if (arr1[i] !== arr2[i]) return false;
    }

    return true;
}

describe('reverseList', () => {
    test('应该正确反转正常链表 [1,2,3,4,5]', () => {
        const input = createLinkedList([1, 2, 3, 4, 5]);
        const expected = createLinkedList([5, 4, 3, 2, 1]);
        const result = reverseList(input);

        expect(compareLinkedLists(result, expected)).toBe(true);
        expect(linkedListToArray(result)).toEqual([5, 4, 3, 2, 1]);
    });

    test('应该正确处理空链表', () => {
        const input = null;
        const result = reverseList(input);

        expect(result).toBeNull();
    });

    test('应该正确处理单节点链表 [1]', () => {
        const input = createLinkedList([1]);
        const expected = createLinkedList([1]);
        const result = reverseList(input);

        expect(compareLinkedLists(result, expected)).toBe(true);
        expect(linkedListToArray(result)).toEqual([1]);
    });

    test('应该正确反转两个节点的链表 [1,2]', () => {
        const input = createLinkedList([1, 2]);
        const expected = createLinkedList([2, 1]);
        const result = reverseList(input);

        expect(compareLinkedLists(result, expected)).toBe(true);
        expect(linkedListToArray(result)).toEqual([2, 1]);
    });

    test('应该正确反转三个节点的链表 [1,2,3]', () => {
        const input = createLinkedList([1, 2, 3]);
        const expected = createLinkedList([3, 2, 1]);
        const result = reverseList(input);

        expect(compareLinkedLists(result, expected)).toBe(true);
        expect(linkedListToArray(result)).toEqual([3, 2, 1]);
    });

    test('应该正确处理包含重复元素的链表 [1,1,2,2,3]', () => {
        const input = createLinkedList([1, 1, 2, 2, 3]);
        const expected = createLinkedList([3, 2, 2, 1, 1]);
        const result = reverseList(input);

        expect(compareLinkedLists(result, expected)).toBe(true);
        expect(linkedListToArray(result)).toEqual([3, 2, 2, 1, 1]);
    });

    test('应该正确处理大链表 [1,2,3,4,5,6,7,8,9,10]', () => {
        const input = createLinkedList([1, 2, 3, 4, 5, 6, 7, 8, 9, 10]);
        const expected = createLinkedList([10, 9, 8, 7, 6, 5, 4, 3, 2, 1]);
        const result = reverseList(input);

        expect(compareLinkedLists(result, expected)).toBe(true);
        expect(linkedListToArray(result)).toEqual([10, 9, 8, 7, 6, 5, 4, 3, 2, 1]);
    });

    test('应该正确处理负数链表 [-1,-2,-3]', () => {
        const input = createLinkedList([-1, -2, -3]);
        const expected = createLinkedList([-3, -2, -1]);
        const result = reverseList(input);

        expect(compareLinkedLists(result, expected)).toBe(true);
        expect(linkedListToArray(result)).toEqual([-3, -2, -1]);
    });

    test('应该正确处理混合正负数的链表 [1,-2,3,-4,5]', () => {
        const input = createLinkedList([1, -2, 3, -4, 5]);
        const expected = createLinkedList([5, -4, 3, -2, 1]);
        const result = reverseList(input);

        expect(compareLinkedLists(result, expected)).toBe(true);
        expect(linkedListToArray(result)).toEqual([5, -4, 3, -2, 1]);
    });

    test('应该正确处理只有一个节点的链表 [42]', () => {
        const input = createLinkedList([42]);
        const expected = createLinkedList([42]);
        const result = reverseList(input);

        expect(compareLinkedLists(result, expected)).toBe(true);
        expect(linkedListToArray(result)).toEqual([42]);
    });

    test('应该正确处理零值链表 [0,0,0]', () => {
        const input = createLinkedList([0, 0, 0]);
        const expected = createLinkedList([0, 0, 0]);
        const result = reverseList(input);

        expect(compareLinkedLists(result, expected)).toBe(true);
        expect(linkedListToArray(result)).toEqual([0, 0, 0]);
    });
});

// 边界条件测试
describe('reverseList 边界条件测试', () => {
    test('应该正确处理最大整数值', () => {
        const input = createLinkedList([Number.MAX_SAFE_INTEGER, 1, Number.MIN_SAFE_INTEGER]);
        const expected = createLinkedList([Number.MIN_SAFE_INTEGER, 1, Number.MAX_SAFE_INTEGER]);
        const result = reverseList(input);

        expect(compareLinkedLists(result, expected)).toBe(true);
    });

    test('应该正确处理浮点数（虽然题目要求是整数）', () => {
        const input = createLinkedList([1.5, 2.7, 3.2]);
        const expected = createLinkedList([3.2, 2.7, 1.5]);
        const result = reverseList(input);

        expect(compareLinkedLists(result, expected)).toBe(true);
    });
}); 