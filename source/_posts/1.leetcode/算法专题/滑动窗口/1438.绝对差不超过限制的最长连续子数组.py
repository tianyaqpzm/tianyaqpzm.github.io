#
# @lc app=leetcode.cn id=1438 lang=python3
#
# [1438] 绝对差不超过限制的最长连续子数组
#

import bisect
from typing import List

from sortedcontainers import SortedList
import sortedcontainers


# @lc code=start
class Solution:
    def longestSubarray(self, nums: List[int], limit: int) -> int:
        d = []
        ans = 1
        for i, a in enumerate(nums):
            bisect.insort(d, a)
            if len(d) > 1:
                # 求连续子数组的最大值和最小值
                while d[-1] - d[0] > limit:
                    print(nums[i - len(d) + 1])
                    # 左端点可通过 右端点 - d 的长度 + 1
                    d.remove(nums[i - len(d) + 1])
                ans = max(ans, len(d))
        return ans

# 只不过将数据结构从数组变成了平衡树，这样插入和删除的复杂度可以降低到 $O(logn)$。
# Python 的 SortedList 可以达到此目的。Java 可用 TreeMap，C++ 可用 multiset 代替。

    def longestSubarray2(self, nums: List[int], limit: int) -> int:
        d = SortedList()
        ans = 1
        for i, a in enumerate(nums):
            d.add(a)
            if len(d) > 1:
                while d[-1] - d[0] > limit:
                    d.remove(nums[i - len(d) + 1])
                ans = max(ans, len(d))
        return ans


# @lc code=end
test = Solution()
print(test.longestSubarray([8, 2, 4, 7], 4))
