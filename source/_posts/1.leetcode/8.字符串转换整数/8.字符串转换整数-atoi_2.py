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
        str = str.strip()  
        # print str
        MAX_INT = 2147483647  
        MIN_INT = -2147483648
        try:  
            str = re.match(r'^[+-]?\d+', str).group()
            ret = int(str)  
            if ret > MAX_INT:  
                return MAX_INT  
            elif ret < MIN_INT:  
                return MIN_INT  
            else:  
                return ret  
        except:  
            return 0  
# @lc code=end

