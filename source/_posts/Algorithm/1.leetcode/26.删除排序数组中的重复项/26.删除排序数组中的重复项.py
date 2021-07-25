#
# @lc app=leetcode.cn id=26 lang=python3
#
# [26] 删除排序数组中的重复项
#


# @lc code=start
class Solution:
    def removeDuplicates(self, nums: List[int]) -> int:
        if len(nums) <= 1:
            return 0
        index = 0
        for item in nums[1:]:
            if item != nums[index]:
                index += 1
                nums[index] = item
        return index + 1


# @lc code=end
