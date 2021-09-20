#
# @lc app=leetcode.cn id=155 lang=python3
#
# [155] 最小栈
#

# @lc code=start
class MinStack:

    def __init__(self):
        """
        initialize your data structure here.
        """
        self.minV = float('inf')
        self.stack = []


    def push(self, val: int) -> None:
        self.stack.append(x-self.minV)
        if x< self.minV:
            self.minV = x

    def pop(self) -> None:
        if not self.stack:
            return
        if tmp<0:
            self.minV -= tmp


    def top(self) -> int:


    def getMin(self) -> int:



# Your MinStack object will be instantiated and called as such:
# obj = MinStack()
# obj.push(val)
# obj.pop()
# param_3 = obj.top()
# param_4 = obj.getMin()
# @lc code=end

