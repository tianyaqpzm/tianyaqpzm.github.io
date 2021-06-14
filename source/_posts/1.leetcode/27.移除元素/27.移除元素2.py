#
# @lc app=leetcode.cn id=27 lang=python3
#
# [27] 移除元素
#
from typing import List
# @lc code=start
class Solution:
    def removeElement(self, nums: List[int], val: int) -> int:
        len_nums = len(nums)
        for i in range(len_nums-1,-1,-1):
        # for i in range(len_nums-1):
            if nums[i] == val:
        # 必须从后往前，否则，会越界的，  否则一旦删除第一额2， 就会跳过第二个2
                del nums[i]
        return len(nums)
# @lc code=end

test = Solution()
print(test.removeElement([0,1,2,2,3,0,4,2], 2))



