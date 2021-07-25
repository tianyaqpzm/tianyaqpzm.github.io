#
# @lc app=leetcode.cn id=5 lang=python3
#
# [5] 最长回文子串
#


# @lc code=start
class Solution:
    def longestPalindrome(self, s: str) -> str:
        n = len(s)
        if n == 0:
            return ""
        res = s[0]

        def extend(i, j, s):
            while (i >= 0 and j < len(s) and s[i] == s[j]):
                i -= 1
                j += 1
            return s[i + 1:j
        for i in range(n-1):
            e1 = extend(i, i, s)
            e2 = extend(i,i+1,s)
            
            


# @lc code=end
