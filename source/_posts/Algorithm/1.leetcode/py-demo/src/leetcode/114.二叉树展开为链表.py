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
            
            # 先整理左子树
            leftTail = flattenTree(node.left)
            # 整理右子树
            rightTail = flattenTree(node.right)
            
            if leftTail:
                # 保存当前处理节点的右子树，接到左子树的尾部
                leftTail.right = node.right
                node.right = node.left
                node.left = None
            # 为什么要返回右子树的尾部呢？ 因为右子树是最后处理的节点
            # 能否先返回左子树的尾部呢？ 不能， 因为右子树可能为空

            return rightTail if rightTail else leftTail
        flattenTree(root)
        
    def flatten2(self, root: Optional[TreeNode]) -> None:
        if not root: 
            return
        
        stack = [root]
        prev = None
        
        while stack:
            node = stack.pop()
            
            if prev:
                prev.left = None
                prev.right = node
            
            if node.right:
                stack.append(node.right)
            if node.left:
                stack.append(node.left)
            
            prev = node
        
# @lc code=end

