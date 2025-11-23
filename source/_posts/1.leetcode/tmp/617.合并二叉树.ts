/*
 * @lc app=leetcode.cn id=617 lang=typescript
 *
 * [617] 合并二叉树
 */

// @lc code=start
/**
 * Definition for a binary tree node.
 * class TreeNode {
 *     val: number
 *     left: TreeNode | null
 *     right: TreeNode | null
 *     constructor(val?: number, left?: TreeNode | null, right?: TreeNode | null) {
 *         this.val = (val===undefined ? 0 : val)
 *         this.left = (left===undefined ? null : left)
 *         this.right = (right===undefined ? null : right)
 *     }
 * }
 */
// class TreeNode {
//     val: number
//     left: TreeNode | null
//     right: TreeNode | null
//     constructor(val?: number, left?: TreeNode | null, right?: TreeNode | null) {
//         this.val = (val === undefined ? 0 : val)
//         this.left = (left === undefined ? null : left)
//         this.right = (right === undefined ? null : right)
//     }
// }
function mergeTrees(root1: TreeNode | null, root2: TreeNode | null): TreeNode | null {
    // 边界条件：如果两个节点都为null，返回null
    if (root1 === null && root2 === null) {
        return null;
    }
    //  计算新节点的值
    const val1 = root1 ? root1.val : 0;
    const val2 = root2 ? root2.val : 0;
    const mergeNode = new TreeNode(val1 + val2);
    mergeNode.left = mergeTrees(root1?.left || null, root2?.left || null);
    mergeNode.right = mergeTrees(root1?.right || null, root2?.right || null);
    return mergeNode;
}

/**
 * 一塌糊涂
 * 过度复杂化：使用了不必要的辅助函数 merge 和复杂的参数传递
* 逻辑错误：在 merge 函数中重新创建 res 节点，忽略了传入的参数
* 递归调用混乱：使用了三元运算符和赋值混合，难以理解
* 内存浪费：创建了不必要的临时节点
* 边界条件处理不当：没有正确处理单个节点为 null 的情况
 * @param root1 
 * @param root2 
 * @returns 
 */
function mergeTrees1(root1: TreeNode | null, root2: TreeNode | null): TreeNode | null {
    if (root1 === null && root2 === null) {
        return null;
    }
    return merge(root1, root2, new TreeNode(0, null, null));
}
function merge(root1: TreeNode | null, root2: TreeNode | null, res: TreeNode): TreeNode | null {
    if (root1 === null && root2 === null) {
        return null;
    }
    res = new TreeNode(0, null, null);
    if (root1 && root2) {
        res.val = root1.val + root2.val;
        root1.left || root2.left ? merge(root1.left, root2.left, res.left = new TreeNode(0, null, null)) : null;
        root1.right || root2.right ? merge(root1.right, root2.right, res.right = new TreeNode(0, null, null)) : null;
        return res;
    } else {
        if (root1 == null && root2 != null) {
            res.val = root2.val;
            root2.left ? merge(null, root2.left, res.left = new TreeNode(0, null, null)) : 0;
            root2.right ? merge(null, root2.right, res.right = new TreeNode(0, null, null)) : 0;
        } else if (root2 == null && root1 != null) {
            res.val = root1.val;
            root1.left ? merge(root1.left, null, res.left = new TreeNode(0, null, null)) : 0;
            root1.right ? merge(root1.right, null, res.right = new TreeNode(0, null, null)) : 0;
        }
    }
    return res;
}