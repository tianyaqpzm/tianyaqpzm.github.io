#
# @lc app=leetcode.cn id=575 lang=python3
#
# [575] 分糖果
# #
# 如果糖果种类大于 n / 2（糖果种类数为 n），妹妹最多可以获得的糖果种类应该是n / 2(因为妹妹只有 n / 2 个糖).
# 糖果种类数小于 n / 2, 妹妹能够得到的糖果种类可以是糖果的种类数（糖果种类本身就这么多）

from typing import List


# @lc code=start
class Solution:
    def distributeCandies(self, candyType: List[int]) -> int:
        return min(len(set(candyType)), len(candyType) >> 1)


# @lc code=end
