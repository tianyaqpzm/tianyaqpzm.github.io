#
# @lc app=leetcode.cn id=55 lang=python3
#
# [55] 跳跃游戏
#

# @lc code=start
from typing import List


class Solution:
    def canJump(self, nums: List[int]) -> bool:
        max_reach = 0
        for i, item in enumerate(nums):
            if i > max_reach:
                return False
            max_reach = max(max_reach, i+ item)
        return True
# @lc code=end

