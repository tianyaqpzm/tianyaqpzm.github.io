#
# @lc app=leetcode.cn id=1381 lang=python3
#
# [1381] 设计一个支持增量操作的栈
#


# @lc code=start
class CustomStack:
    def __init__(self, maxSize: int):
        # 固定列表大小
        self.st = []
        self.cnt = 0
        self.maxSize = maxSize

    def push(self, x: int) -> None:
        if self.cnt < self.maxSize:
            self.st.append(x)
            self.cnt += 1

    def pop(self) -> int:
        if self.cnt == 0:
            return -1
        self.cnt -= 1
        return self.st.pop()

    def increment(self, k: int, val: int) -> None:
        for i in range(0, min(self.cnt, k)):
            self.cnt[i] += val


# Your CustomStack object will be instantiated and called as such:
# obj = CustomStack(maxSize)
# obj.push(x)
# param_2 = obj.pop()
# obj.increment(k,val)
# @lc code=end
