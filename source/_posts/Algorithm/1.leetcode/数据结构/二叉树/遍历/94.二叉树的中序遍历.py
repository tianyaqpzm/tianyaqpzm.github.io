#
# @lc app=leetcode.cn id=94 lang=python3
#
# [94] 二叉树的中序遍历
#

# @lc code=start
# Definition for a binary tree node.
from typing import List
# 如果一个树遍历的结果是有序数组，那么他也是一个二叉查找树(BST)


class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


class Solution:
    def inorderTraversal(self, root: TreeNode) -> List[int]:
        WHITE, GRAY = 0, 1
        res = []
        stack = [(WHITE, root)]
        while stack:
            color, node = stack.pop()
            if node is None:
                continue
            if color == WHITE:
                # 如果遇见白色，则继续深入
                stack.append((WHITE, node.right))
                # 中序遍历
                stack.append((GRAY, node))
                stack.append((WHITE, node.left))
            else:
                # 加入返回列表
                res.append(node.val)
                # print(node.val + ",")
        return res

    def inorderTraversal2(self, root: TreeNode) -> List[int]:
        if root is None:
            return []
        return self.inorderTraversal2(
            root.left) + [root.val] + self.inorderTraversal2(root.right)


# @lc code=end

test = Solution()

# test.inorderTraversal(root)
# print(res)