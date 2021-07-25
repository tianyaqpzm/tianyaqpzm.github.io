#
# @lc app=leetcode.cn id=53 lang=python3
#
# [53] 最大子序和
#
# dp[i]  表示当前位置i 的最大子序列和
# dp[i] = max(dp[i-1] + nums[i],nums[i])
# 初始化：dp[0] = nums[0]
# 只需要关注前一个状态
# @lc code=start
from typing import List


class Solution:
    def maxSubArray(self, nums: List[int]) -> int:
        if not nums:
            return 0
        curSum = maxSum = nums[0]
        for num in nums[1:]:
            curSum = max(num, curSum + num)
            maxSum = max(maxSum, curSum)
        return maxSum


# @lc code=end
