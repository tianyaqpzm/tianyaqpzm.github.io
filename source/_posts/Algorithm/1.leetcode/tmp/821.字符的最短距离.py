#
# @lc app=leetcode.cn id=821 lang=python3
#
# [821] 字符的最短距离
#

from typing import List


# @lc code=starts
class Solution:
    def shortestToChar(self, s: str, c: str) -> List[int]:
        ans = []
        for i in range(len(s)):
            l = r = i
            # 向左找到第一个C
            while l > -1:
                if s[l] == c: break
                l -= 1
            # 向又找到第一个C
            while r < len(s):
                if s[r] == c: break
                r += 1
            if l == -1: l = -10000
            if r == len(s): r = 10000
            ans.append(min(r - i, i - l))
        return ans
    def shortestToChar(self, s: str, c: str) -> List[int]:


# @lc code=end
