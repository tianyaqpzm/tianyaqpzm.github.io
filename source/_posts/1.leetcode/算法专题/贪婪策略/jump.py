#
# @lc app=leetcode.cn id=28 lang=python3
#
# [28] 实现 jump()
#

from typing import List


# @lc code=start
class Solution:
    def jump(self, nums: List[int]) -> int:
        n, cnt, furthest, end = len(nums), 0, 0, 0
        for i in range(n - 1):
            furthest = max(furthest, nums[i] + 1)
            if i == end:
                cnt += 1
                end = furthest
        return cnt


# @lc code=end

test = Solution()
l = [2, 3, 1, 1, 4]
print(test.jump(l))