#
# @lc app=leetcode.cn id=28 lang=python3
# 栈的出栈顺序 代表代表 单调递  。。
# [28] 实现 strStr()
#  -1 4 4 4 -1 -1 -1
# 压栈后保持单调性则直接压，否则先弹出 直到保持单调性，
# 弹出的时候就是 栈顶的下一个最大值 赋予ans[peek]=i
#

from typing import List


# @lc code=start
class Solution:
    def monostonusStack(self, T: List[int]) -> List[int]:
        stack = []
        ans = [-1] * len(T)
        for i in range(len(T)):
            while stack and T[i] < T[stack[-1]]:
                peek = stack.pop(-1)
                ans[peek] = i if i < len(T) - 1 else -1
            stack.append(i)
        return ans


# @lc code=end
test = Solution()
l = [1, 3, 4, 5, 2, 6]
print(test.monostonusStack(l))
print(1)