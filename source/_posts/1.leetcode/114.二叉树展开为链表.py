#
# @lc app=leetcode.cn id=114 lang=python3
#
# [114] 二叉树展开为链表
#

# @lc code=start
# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right
from typing import Optional


class Solution:
    def flatten(self, root: Optional[TreeNode]) -> None:
        """
        Do not return anything, modify root in-place instead.
        """
        # 使用递归的方法   
        def flattenTree(node):
            if not node:
                return None
            
            if not node.left and not node.right:
                return node
            
            leftTail = flattenTree(node.left)
            rightTail = flattenTree(node.right)
            
            if leftTail:
                leftTail.right = node.right
                node.right = node.left
                node.left = None
            
            return rightTail if rightTail else leftTail 
    def flatten2(self, root: Optional[TreeNode]) -> None:

        # if not root:
        #     return
        
        # stack = [root]
        # prev = None
        
        # while stack:
        #     node = stack.pop()
            
        #     if prev:
        #         prev.left = None
        #         prev.right = node
            
        #     if node.right:
        #         stack.append(node.right)
        #     if node.left:
        #         stack.append(node.left)
            
        #     prev = node
        
# @lc code=end

