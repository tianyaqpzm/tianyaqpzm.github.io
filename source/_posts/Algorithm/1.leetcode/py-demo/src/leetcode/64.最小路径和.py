#
# @lc app=leetcode.cn id=64 lang=python3
#
# [64] 最小路径和
#

# @lc code=start
from typing import List


class Solution:
    def minPathSum(self, grid: List[List[int]]) -> int:
        # 使用深度遍历+ 记忆化搜索
        m, n = len(grid), len(grid[0])
        # 记忆化数组
        memo = [[-1] * n for _ in range(m)]
        def dfs(i, j):
            # 递归推出条件： 到达右下角
            if i == m - 1 and j == n - 1:
                return grid[i][j]
            # 优先利用记忆化搜索
            if memo[i][j] != -1:
                return memo[i][j]
            down = right = float('inf')
            if i + 1 < m:
                down = dfs(i + 1, j)
            if j + 1 < n:
                right = dfs(i, j + 1)
            memo[i][j] = grid[i][j] + min(down, right)
            return memo[i][j]
        return dfs(0,0)
    # 使用动态规划
    def minPathSum_dp(self, grid: List[List[int]]) -> int:
        m, n = len(grid), len(grid[0])
        # 初始化 m*n 的 dp 数组
        dp = [[0] * n for _ in range(m)]
        dp[0][0] = grid[0][0]
        for i in range(1, m):
            dp[i][0] = dp[i - 1][0] + grid[i][0]
        for j in range(1, n):
            dp[0][j] = dp[0][j - 1] + grid[0][j]
        for i in range(1, m):
            for j in range(1, n):
                # 递推公式： dp[i][j] 的两种可能： 上方和左方
                dp[i][j] = min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j]
        return dp[m - 1][n - 1]
# @lc code=end

