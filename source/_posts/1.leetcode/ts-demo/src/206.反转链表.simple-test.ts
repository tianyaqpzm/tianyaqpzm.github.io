// eslint-disable no-console

import { ListNode, reverseList } from "./206.反转链表";

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

// 辅助函数：比较两个数组是否相等
function arraysEqual(arr1: number[], arr2: number[]): boolean {
    if (arr1.length !== arr2.length) return false;
    for (let i = 0; i < arr1.length; i++) {
        if (arr1[i] !== arr2[i]) return false;
    }
    return true;
}

// 测试函数
function testReverseList() {
    // eslint-disable-next-line no-console
    console.log("=== 反转链表测试用例 ===");

    const tests = [
        {
            name: "正常链表 [1,2,3,4,5]",
            input: [1, 2, 3, 4, 5],
            expected: [5, 4, 3, 2, 1]
        },
        {
            name: "空链表",
            input: [],
            expected: []
        },
        {
            name: "单节点链表 [1]",
            input: [1],
            expected: [1]
        },
        {
            name: "两个节点 [1,2]",
            input: [1, 2],
            expected: [2, 1]
        },
        {
            name: "三个节点 [1,2,3]",
            input: [1, 2, 3],
            expected: [3, 2, 1]
        },
        {
            name: "包含重复元素 [1,1,2,2,3]",
            input: [1, 1, 2, 2, 3],
            expected: [3, 2, 2, 1, 1]
        },
        {
            name: "负数链表 [-1,-2,-3]",
            input: [-1, -2, -3],
            expected: [-3, -2, -1]
        },
        {
            name: "混合正负数 [1,-2,3,-4,5]",
            input: [1, -2, 3, -4, 5],
            expected: [5, -4, 3, -2, 1]
        },
        {
            name: "零值链表 [0,0,0]",
            input: [0, 0, 0],
            expected: [0, 0, 0]
        },
        {
            name: "大链表 [1,2,3,4,5,6,7,8,9,10]",
            input: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10],
            expected: [10, 9, 8, 7, 6, 5, 4, 3, 2, 1]
        }
    ];

    let passedTests = 0;
    let totalTests = tests.length;

    for (let i = 0; i < tests.length; i++) {
        const test = tests[i];

        const input = createLinkedList(test.input);
        const result = reverseList(input);
        const actual = linkedListToArray(result);

        console.log(`原始链表: [${test.input.join(',')}]`);
        console.log(`反转后: [${actual.join(',')}]`);
        console.log(`期望结果: [${test.expected.join(',')}]`);

        const passed = arraysEqual(actual, test.expected);
        console.log(`测试结果: ${passed ? "✅ 通过" : "❌ 失败"}`);

        if (passed) {
            passedTests++;
        }
    }

    console.log(`\n=== 测试总结 ===`);
    console.log(`总测试数: ${totalTests}`);
    console.log(`通过测试: ${passedTests}`);
    console.log(`失败测试: ${totalTests - passedTests}`);
    console.log(`通过率: ${((passedTests / totalTests) * 100).toFixed(1)}%`);

    if (passedTests === totalTests) {
        console.log("🎉 所有测试都通过了！");
    } else {
        console.log("⚠️  有测试失败，请检查实现。");
    }
}

// 运行所有测试
testReverseList();