#
# @lc app=leetcode.cn id=28 lang=python3
#
# [28] 实现双色法
#


# @lc code=start
class TreeNode:
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None


class Solution:
    def inorderTraversal(self, root: TreeNode) -> List[int]:
        len_h = len(haystack)
        len_n = len(needle)
        if len_n == 0: return 0
        if len_n > len_h or len_h == 0: return -1
        i, j = 0, 0
        while i < len_h and j < len_n:
            if haystack[i] == needle[j]:
                i += 1
                j += 1
            else:
                i = i - j + 1  # 相当于返回i原始值后 加1
                j = 0
        if j == len_n:
            return i - j
        return -1


# @lc code=end
