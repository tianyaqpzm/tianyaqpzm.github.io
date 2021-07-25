#
# @lc app=leetcode.cn id=36 lang=python3
#
# [36] 有效的数独
#

# @lc code=start
class Solution:
    def isValidSudoku(self, board: List[List[str]]) -> bool:
        if len(board)!=9 or len(board[0])!=9:return False
        for i in range(9):
            map = [0 for k in range(10)]
            for j in range(9):
                if board[i][j] == '.':continue
                if board[i][j]<'0' or board[i][j]>'9':return False
                num = ord(board[i][j]) - ord('0')
                # 每一行 不相等
                if map[num] ==1:return False
                map[num] = 1
        for j in range(9):
            map=[0 for k in range(10)]
            for i in range(9):
                if board[i][j]=='.':continue  
                num = ord(board[i][j])-ord('0')  
                if map[num] == 1:return False  
                map[num] = 1
        for i in range(0,9,3):
            for j in range(0,9,3):
                map= [0 for k in range(10)]
                for k in range(i,i+3):
                    for l in range(j,j+3):
                        if board[k][l] =='.':continue
                        num = ord(board[k][l])-ord('0')  
                        if map[num]==1:return False  
                        map[num]=1  
        return True
# @lc code=end

