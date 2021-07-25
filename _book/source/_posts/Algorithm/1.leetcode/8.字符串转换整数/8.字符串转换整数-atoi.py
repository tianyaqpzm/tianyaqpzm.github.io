#
# @lc app=leetcode.cn id=8 lang=python3
#
# [8] 字符串转换整数 (atoi)
#

# @lc code=start
class Solution:
    def myAtoi(self, str: str) -> int:
        """
        :type str: str
        :rtype: int
        """
        # 1.去掉前后空格
        str = str.strip()
        if not str:
            return 0
        MAX_INT = 2147483647
        MIN_INT = -2147483648
        ret:int =0
        pos:int = 0;sign:int =1;overflow =False
        # 取正负符号，如果存在，位置加1
        if str[pos]=='-':
            pos+=1
            sign = -1
        elif str[pos] =='+':
            pos += 1
        # 从pos 算起， range()  ,如果是不是数据，返回， 如果计算后的结果，超过正常范围则 溢出返回 最大、或最小
        for i in range(pos,len(str)):
            if not str[i].isdigit():
                break
            ret = ret *10 + int(str[i])
            if not MIN_INT<= sign * ret <=MAX_INT:
                overflow = True
                break
        if overflow:
            return MAX_INT if sign==1 else MIN_INT
        else:
            return sign*ret
# @lc code=end

