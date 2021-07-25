#
# @lc app=leetcode.cn id=169 lang=python3
#
# [169] 多数元素
# 投票算法 的原理是通过不断消除不同元素直到没有不同元素，剩余的元素就是我们要找的元素
#

from typing import List


# @lc code=start
class Solution:
    def majorityElement(self, nums: List[int]) -> int:
        count, majority = 1, nums[0]
        for num in nums[1:]:
            if count == 0:
                majority = num
            if num == majority:
                count += 1
            else:
                count -= 1
        return majority


# @lc code=end

test = Solution()
# test.majorityElement([3, 2, 3])

test.majorityElement([2, 2, 1, 1, 1, 2, 2])
