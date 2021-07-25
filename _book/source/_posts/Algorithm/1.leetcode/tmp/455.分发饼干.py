#
# @lc app=leetcode.cn id=455 lang=python3
#
# [455] 分发饼干
#
from typing import List

# @lc code=start
class Solution:
    def findContentChildren(self, g: List[int], s: List[int]) -> int:
        g.sort()
        s.sort()
        gi = 0
        sj = 0
        res =0
        while gi<len(g) && sj<len(s):
            if s[sj] > g[gi]:
                gi++
                sj++
                res ++
            else:
                # 饼干sj < 胃口gi，当前饼干满足不了胃口，需换个更大的饼干
                sj++
        return res

# @lc code=end
