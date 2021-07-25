import sys

# 题目描述
# 求取数组中最大连续子序列和，例如给定数组为 A = [1， 3， -2， 4， -5]， 则最大连续子序列和为 6，即 1 + 3 +（-2）+ 4 = 6。 去
# 首先我们来明确一下题意。
# 题目说的子数组是连续的
# 题目只需要求和，不需要返回子数组的具体位置。
# 数组中的元素是整数，但是可能是正数，负数和 0。
# 子序列的最小长度为 1。
# 比如：
# 对于数组 [1, -2, 3, 5, -3, 2], 应该返回 3 + 5 = 8
# 对于数组 [0, -2, 3, 5, -1, 2], 应该返回 3 + 5 + -1 + 2 = 9
# 对于数组 [-9, -2, -3, -5, -3], 应该返回 -2

from typing import List


class Solution:
    def maxSubArry(self, nums: List[int]) -> int:
        n = len(nums)
        maxSum = -sys.maxsize
        for i in range(n):
            sum = 0
            for j in range(i, n):
                sum += nums[j]
                maxSum = max(maxSum, sum)
        return maxSum

    def maxSubArryDynaic(self, nums: List[int]) -> int:
        n = len(nums)
        max_sum_curr_index = max_sum = nums[0]
        for i in range(1, n):
            max_sum_curr_index = max(max_sum_curr_index + nums[i], nums[i])
            max_sum = max(max_sum, max_sum_curr_index)
        return max_sum

    def maxSubArryMath(self, nums: List[int]) -> int:
        n = len(nums)
        maxSum = nums[0]
        minSum = sum = 0
        for i in range(n):
            sum += nums[i]
            maxSum = max(maxSum, sum - minSum)
            minSum = min(minSum, sum)


test = Solution()
print(test.maxSubArryMath([1, -2, 3, 5, -3, 2]))
print(-sys.maxsize)
print(float('inf'))
