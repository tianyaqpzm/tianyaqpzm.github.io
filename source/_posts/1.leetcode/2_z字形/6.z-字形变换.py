#
# @lc app=leetcode.cn id=6 lang=python3
#
# [6] Z 字形变换
#

# @lc code=start
class Solution:
    def convert(self, s: str, numRows: int) -> str:
        if numRows<=1:
            return s
        # 创建列表，默认元素,t替代下面三行
        sb = ['' for i in range(numRows)]
                # result = list()
        # sb:list=[]
        # for i in range(numRows):
        #     sb[i]=""
        index: int = 0
        dir: int =1
        # for c in s:
        for c in s:
            sb[index]+=c
            index += dir
            if index == 0 | index == numRows-1:
                dir  = -dir
           # 返回一个以分隔符sep连接各个元素后生成的字符串
        return ''.join(sb)   
# @lc code=end

