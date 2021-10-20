#
# @lc app=leetcode.cn id=33 lang=python3
#
# [33] 搜索旋转排序数组
#

from typing import List


# @lc code=start
class Solution:
    def search(self, nums: List[int], target: int) -> int:
        '''
        我们可以先找出 mid，然后根据 mid 来判断，mid 是在有序的部分还是无序的部分
        然后我们继续判断 target 在哪一部分， 我们就可以舍弃另一部分了
        '''
        if len(nums) <= 0:
            return -1
        left = 0
        right = len(nums) - 1
        while left < right:
            mid = (right - left) // 2 + left
            if nums[mid] == target:
                return mid
            if nums[mid] > nums[left]:
                if nums[left] <= target <= nums[mid]:
                    right = mid
                else:
                    left = mid + 1
            else:
                if nums[mid + 1] <= target <= nums[right]:
                    left = mid + 1
                else:
                    right = mid
        return left if nums[left] == target else -1


# @lc code=end
test = Solution()
print(test.search([4, 5, 6, 7, 0, 1, 2], 0))
