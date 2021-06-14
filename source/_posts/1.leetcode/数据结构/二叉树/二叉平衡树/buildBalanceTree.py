#
# @lc app=leetcode.cn id=27 lang=python3
#
# [27] 移除元素
#

from typing import List


class TreeNode:
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None


class Solution:
    def sortedArrayToBST(self, nums: List[int]) -> bool:
        if not nums:
            return None
        mid = (len(nums) - 1) // 2
        root = TreeNode(nums[mid])
        root.left = self.sortedArrayToBST(nums[:mid])
        root.right = self.sortedArrayToBST(nums[mid + 1:])
        return root

    def printInOrder(root):
        if not root:
            return
        print()


test = Solution()
li = [-10, -3, 0, 5, 9]
print(test.sortedArrayToBST(li))
res = test.sortedArrayToBST(li)
# for i range(res):

# [0,-3,9,-10,null,5]

# @lc code=end