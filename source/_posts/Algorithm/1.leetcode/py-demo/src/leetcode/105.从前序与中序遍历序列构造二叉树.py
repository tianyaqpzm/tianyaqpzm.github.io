#
# @lc app=leetcode.cn id=105 lang=python3
#
# [105] 从前序与中序遍历序列构造二叉树
#

# @lc code=start
# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right
from typing import List, Optional


class Solution:
    def buildTree(self, preorder: List[int], inorder: List[int]) -> Optional[TreeNode]:
        if not preorder or not inorder:
            return None

        # 必须有前序遍历，中序遍历才能构造树
        root_val = preorder[0]
        root = TreeNode(root_val)

        # 找到根节点在中序遍历中的位置 3
        root_index_in_inorder = inorder.index(root_val)

        # 以该节点划分左右子树  9 20
        left_inorder = inorder[:root_index_in_inorder]
        right_inorder = inorder[root_index_in_inorder + 1:]

        # 递归 左子树的入参，通过截取前序遍历数组获取 9   前包后不包
        left_preorder = preorder[1:1 + len(left_inorder)]

        # 递归 右子树的入参，通过截取前序遍历数组获取 15 7
        right_preorder = preorder[1 + len(left_inorder):]

        # 调用自身递归构造左右子树
        root.left = self.buildTree(left_preorder, left_inorder)
        root.right = self.buildTree(right_preorder, right_inorder)

        return root
# @lc code=end

