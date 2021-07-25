#
# @lc app=leetcode.cn id=172 lang=python3
#
# [172] 阶乘后的零
#


# @lc code=start
class Solution:
    def trailingZeroes(self, n: int) -> int:
        result = 0
        for i in range(32):
            result = (result << 1) + (n & 1)
            n >>= i
        return result


# @lc code=end
