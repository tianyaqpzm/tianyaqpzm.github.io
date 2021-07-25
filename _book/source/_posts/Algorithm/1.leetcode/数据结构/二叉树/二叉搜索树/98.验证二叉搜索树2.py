#
# @lc app=leetcode.cn id=98 lang=python3
#
# [98] 验证二叉搜索树
#


# @lc code=start
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def isValidBST(self,
                   root: TreeNode,
                   area: tuple = (-float('inf'), float('inf'))) -> bool:
        """思路如上面的分析，用Python表达会非常直白
            :param root TreeNode 节点
            :param area tuple 取值区间
            """
        if root is None:
            return True
        is_valid_left = root.left is None or\
                (root.left.val < root.val and area[0] < root.left.val < area[1])
        is_valid_right = root.right is None or\
                (root.right.val > root.val and area[0] < root.right.val < area[1])

        # 左右节点都符合，说明本节点符合要求
        is_valid = is_valid_left and is_valid_right

        # 递归下去
        return is_valid\
            and self.isValidBST(root.left, (area[0], root.val))\
            and self.isValidBST(root.right, (root.val, area[1]))


# @lc code=end
