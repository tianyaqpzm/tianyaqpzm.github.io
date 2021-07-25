#
# @lc app=leetcode.cn id=665 lang=python3
#
# [665] 非递减数列
#
from typing import List


# @lc code=start
class Solution:
    def checkPossibility(self, nums: List[int]) -> bool:
        count = 0
        for i in range(1, len(nums)):
            if nums[i] < nums[i - 1]:
                # 前面大于后面
                count += 1
                if count > 1:
                    return False
                # [4,2,3] [4,2,1] [1,2,1,2]
                if i >= 2 and nums[i] < nums[i - 2]:
                    nums[i] = nums[i - 1]
        return True


# @lc code=end
test = Solution()
print(test.checkPossibility([3, 4, 2, 3]))
