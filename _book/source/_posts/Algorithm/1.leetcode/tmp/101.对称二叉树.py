#
# @lc app=leetcode.cn id=101 lang=python3
#
# [101] 对称二叉树
#


# @lc code=start
# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


class Solution:
    def isSymmetric(self, root: TreeNode) -> bool:
        def dfs(root1: TreeNode, root2: TreeNode) -> bool:
            if root1 == root2 == None:
                return True
            if not root1 or not root2: return False
            if root1.val != root2.val: return False
            return dfs(root1.left, root2.right) and dfs(
                root1.right, root2.left)

        if not root: return True
        return dfs(root.left, root.right)


# @lc code=end
