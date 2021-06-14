#
# @lc app=leetcode.cn id=20 lang=python3
#
# [20] 有效的括号
#
import os


# @lc code=start
class Solution:
    def isValid(self, s: str) -> bool:
        # 字符串包含
        while '[]' in s or '()' in s or '{}' in s:
            s = s.replace('[]', '').replace('()', '').replace('()', '')
        return not len(s)


# @lc code=end

test = Solution()
print(test.isValid("[0]"))
flag = '[]' in "[]"
print(flag)