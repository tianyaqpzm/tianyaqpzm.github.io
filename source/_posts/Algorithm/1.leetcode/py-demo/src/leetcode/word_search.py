from typing import List


class Solution:
    def exist(self, board: List[List[str]], word: str) -> bool:
        if not word:
            return True
        if not board or not board[0]:
            return False
        m, n = len(board), len(board[0])
        visited = [[False] * n for _ in range(m)]

        def backtrack(i: int, j: int, index: int) -> bool:
            # exit condition: matched whole word
            if index == len(word):
                return True
            # bounds
            if i < 0 or i >= m or j < 0 or j >= n:
                return False
            if visited[i][j] or board[i][j] != word[index]:
                return False
            visited[i][j] = True
            found = (
                backtrack(i + 1, j, index + 1)
                or backtrack(i - 1, j, index + 1)
                or backtrack(i, j + 1, index + 1)
                or backtrack(i, j - 1, index + 1)
            )
            visited[i][j] = False
            return found

        for i in range(m):
            for j in range(n):
                if backtrack(i, j, 0):
                    return True
        return False
