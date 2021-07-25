#
# @lc app=leetcode.cn id=20 lang=python3
#
# [20] 有效的括号
#
import os


# @lc code=start
class Solution:
    def isValid(self, s: str) -> bool:
        # 做个map  映射
        mp = {')': '(', '}': '{', ']': '['}
        stk = []
        for ch in s:
            if ch in '({[':
                stk.append(ch)
            else:
                if not stk or mp[ch] != stk.pop():
                    # 如果 栈最后一个元素 与字典中匹配的value 不同，说明不对应
                    # 如果 栈为空，ch为闭合括号，则说明失败
                    return False
        return not stk


# @lc code=end

test = Solution()
print(test.isValid(""))
