#
# @lc app=leetcode.cn id=1658 lang=python3
#
# [1658] 将 x 减到 0 的最小操作数
#

# @lc code=start
from typing import List
from functools import lru_cache
from functools import wraps


def memo(func):
    cache = {}

    @wraps
    def wrap(*args):
        if args not in cache:
            cache[args] = func(*args)
        return cache[args]

    return wrap


class Solution:
    # 动态规划法： 时间超限
    def minOperations2(self, nums: List[int], x: int) -> int:
        n = len(nums)

        @lru_cache(None)
        def dp(l, r, x):
            if x == 0:
                return 0
            if x < 0 or l > len(nums) - 1 or r < 0:
                return n + 1
            return 1 + min(dp(l + 1, r, x - nums[l]), dp(
                l, r - 1, x - nums[r]))

        ans = dp(0, n - 1, x)
        return -1 if ans > n else ans

    # 逆向思考： 求 剩下原数组的中间部分， 指定和 的最长子序列
    # 和为定值的最长子序列
    def minOperations(self, nums: List[int], x: int):
        i = 0
        target = sum(nums) - x
        win = 0
        ans = len(nums)
        if target == 0: return ans
        for j in range(len(nums)):
            win += nums[j]
            while i < j and win > target:
                # 一旦达到目标值，尝试去除开头
                win -= nums[i]
                i += 1
            # 穷举所有的可能（不论取去除前或后），最终找到去除个数最少的
            if win == target:
                ans = min(ans, len(nums) - (j - i + 1))
        return -1 if ans == len(nums) else ans


# @lc code=end
test = Solution()
print(test.minOperations([1, 1, 4, 2, 3], 5))
