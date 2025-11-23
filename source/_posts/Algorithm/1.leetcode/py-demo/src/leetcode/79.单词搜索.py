#
# @lc app=leetcode.cn id=79 lang=python3
#
# [79] 单词搜索
#

# @lc code=start
from typing import List


class Solution:
    def exist(self, board: List[List[str]], word: str) -> bool:
        m, n = len(board), len(board[0])
        visited = [[False] * n for _ in range(m)]

        def backtrack(i: int, j: int, index: int) -> bool:
            # 递归退出条件
            if index == len(word):
                return True
            # 边界条件
            if i < 0 or i >= m or j < 0 or j >= n:
                return False
            if visited[i][j] or board[i][j] != word[index]:
                return False
            # 递归，不需要保持"过程状态" 所以只通过 记录 "访问状态" 即可
            visited[i][j] = True
            # 在java版本中 采用if else 方式 但python中 直接 or 更简洁
            found = (backtrack(i + 1, j, index + 1) or
                     backtrack(i - 1, j, index + 1) or
                     backtrack(i, j + 1, index + 1) or
                     backtrack(i, j - 1, index + 1))
            visited[i][j] = False
            return found

        for i in range(m):
            for j in range(n):
                if backtrack(i, j, 0):
                    return True
        return False     
# @lc code=end

