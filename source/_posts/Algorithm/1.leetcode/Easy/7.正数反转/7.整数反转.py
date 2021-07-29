#
# @lc app=leetcode.cn id=7 lang=python3
#
# [7] 整数反转
#

# @lc code=start
class Solution:
    def reverse(self, x: int) -> int:
        """
        :type x: int
        :rtype: int
        """
        # 取绝对值， 转为字符串， 逆转后 转为整数
        revx = int(str(abs(x))[::-1])  
        print str(abs(x))[::-1]
        if revx > math.pow(2, 31):  
            return 0  
        else:  
            return revx * cmp(x, 0)  
# @lc code=end

