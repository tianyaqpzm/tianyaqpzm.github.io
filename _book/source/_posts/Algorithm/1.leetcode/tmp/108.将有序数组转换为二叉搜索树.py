#
# @lc app=leetcode.cn id=108 lang=python3
#
# [108] 将有序数组转换为二叉搜索树
#

from typing import List


# @lc code=start
# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


class Solution:
    def sortedArrayToBST(self, nums: List[int]) -> TreeNode:
        def reBuild(nums, left, right):
            if left > right:
                return
            mid = (left + right) // 2
            root: TreeNode = TreeNode(nums[mid])
            root.left = reBuild(nums, left, mid - 1)
            root.right = reBuild(nums, mid + 1, right)
            return root

        return reBuild(nums, 0, len(nums) - 1)


# @lc code=end
