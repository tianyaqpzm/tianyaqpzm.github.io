#
# @lc app=leetcode.cn id=56 lang=python3
#
# [56] 合并区间
#

# @lc code=start
from typing import List


class Solution:
    def merge(self, intervals: List[List[int]]) -> List[List[int]]:
        if not intervals:
            return [[]]
        # 先按照区间起点排序
        intervals.sort(key=lambda x: x[0])
        merged= [intervals[0]]
        for item in intervals[1:]:
            # 判断当前区间起点是否在上一个合并区间内
            if item[0] <= merged[-1][1]: 
                merged[-1][1]= max(merged[-1][1], item[1])
            else:
                merged.append(item)
        return merged
# @lc code=end

