#
# @lc app=leetcode.cn id=95 lang=python3
#
# [95] 不同的二叉搜索树 II
#  BST

# @lc code=start
# Definition for a binary tree node.
from typing import List


class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


# 我们将问题进一步划分为子问题，假如左侧和右侧的树分别求好了，
# 我们是不是只要运用组合的原理，将左右两者进行合并就好了，我们需要两层循环来完成这个过程。


class Solution:
    def generateTrees(self, n: int) -> List[TreeNode]:
        if not n:
            return []

        def generateTree(start, end):
            if start > end:
                return [None]
            res = []
            for i in range(start, end + 1):
                ls = generateTree(start, i - 1)
                rs = generateTree(i + 1, end)
                for l in ls:
                    for r in rs:
                        node = TreeNode(i)
                        node.left = l
                        node.right = r
                        res.append(node)
            return res

        return generateTree(1, n)


# @lc code=end
test = Solution()
res = test.generateTrees(3)
print("e")