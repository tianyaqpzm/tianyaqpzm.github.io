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
        self.incrementals = []

    def push(self, x: int) -> None:
        if self.cnt < self.maxSize:
            self.st.append(x)
            self.incrementals.append(0)
            self.cnt += 1

    def pop(self) -> int:
        if self.cnt == 0:
            return -1
            # 推出栈顶元素，cnt减一
        if self.cnt >= 2:
            self.incrementals[self.cnt - 2] += self.incrementals[self.cnt - 1]
        self.cnt -= 1
        return self.st.pop() + self.incrementals.pop()

# 我们维护了一个大小为 maxSize 的数组，
# 因此平均到每次的空间复杂度为 $O(maxSize / N)$，其中 N 为操作数。

    def increment(self, k: int, val: int) -> None:
        if self.incrementals:
            self.incrementals[min(self.cnt, k) - 1] += val


# Your CustomStack object will be instantiated and called as such:
# obj = CustomStack(maxSize)
# obj.push(x)
# param_2 = obj.pop()
# obj.increment(k,val)
# @lc code=end
