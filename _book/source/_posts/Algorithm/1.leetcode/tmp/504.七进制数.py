#
# @lc app=leetcode.cn id=504 lang=python3
#
# [504] 七进制数
#


# @lc code=start
class Solution:
    def convertToBase7(self, num: int) -> str:
        if num == 0:
            return 0
        ans = []
        isNegative = num < 0
        while num > 0:
            num, remain = num // 7, num % 7
            ans.append(str(remain))
        return "-" + "".join(ans[::-1]) if isNegative else "".join(ans[::-1])

    def convertToBase7Two(self, num: int) -> str:
        if num < 0:
            return "-" + self.convertToBase7Two(-num)
        if num < 7:
            return str(num)
        return self.convertToBase7Two(num // 7) + str(num % 7)


# @lc code=end
