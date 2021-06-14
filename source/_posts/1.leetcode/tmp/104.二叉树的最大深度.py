#
# @lc app=leetcode.cn id=104 lang=python3
#
# [104] 二叉树的最大深度
#

# @lc code=start
# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right
class Solution:
    def maxDepth(self, root: TreeNode) -> int:
        if not root: return 0
        if not root.left && root.right: return 1
        return 1+ Math.max(maxDepth(root.left), maxDepth(root.right))

# 队列中用 Null(一个特殊元素)来划分每层，
# 或者在对每层进行迭代之前保存当前队列元素的个数（即当前层所含元素个数）
    def maxDepth2(self, root:TreeNode)->int:
        if not root: return 0
        q, depth = [root,None],1
        while q:
            node  = q.pop(0)
            if node:
                if node.left:q.append(node.left)
                if node.right:q.append(node.right)
            elif q:
                q.append(None)
        return depth
# @lc code=end

