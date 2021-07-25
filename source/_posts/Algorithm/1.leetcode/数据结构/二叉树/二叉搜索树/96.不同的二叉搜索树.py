#
# @lc app=leetcode.cn id=96 lang=python3
#
# [96] 不同的二叉搜索树
#
# f(n) = f(n-1)*f(0) + f(n-2)*f(1) +……f(0)*f(n-1)


# @lc code=start
class Solution:
    def numTrees2(self, n: int) -> int:
        if n <= 1:
            return 1
        res = 0
        # 并没有栈溢出，因此我们考虑使用 hashmap 来优化
        for i in range(1, n + 1):
            res += self.numTrees2(i - 1) * self.numTrees2(n - i)
        return res

    visited = dict()

    def numTrees(self, n: int) -> int:
        if n in self.visited:
            return self.visited.get(n)
        if n <= 1:
            return 1
        res = 0
        for i in range(1, n + 1):
            res += self.numTrees(i - 1) * self.numTrees(n - i)
        self.visited[n] = res
        return res


# @lc code=end
