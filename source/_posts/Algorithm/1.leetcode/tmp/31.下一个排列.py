#
# @lc app=leetcode.cn id=31 lang=python3
#
# [31] 下一个排列
#

from typing import List


# @lc code=start
class Solution:
    def nextPermutation(self, nums: List[int]) -> None:
        """
        Do not return anything, modify nums in-place instead.
        """
        down_index = None
        for i in range(len(nums) - 2, -1, -1):
            if nums[i] < nums[i + 1]:
                down_index = i
                break
        # 如果没有下降点， 重新排列
        if down_index is None:
            nums.reverse()
        else:
            # 第二步， 从后往前，找到比下降点大大数， 对换位置
            for i in range(len(nums) - 1, down_index, -1):
                if nums[down_index] < nums[i]:
                    nums[down_index], nums[i] = nums[i], nums[down_index]
                    break
            # 第三步： 重新排列下降点后点数
            i, j = down_index + 1, len(nums) - 1
            while i < j:
                nums[i], nums[j] = nums[j], nums[i]
                i += 1
                j -= 1


# @lc code=end

test = Solution()
test.nextPermutation([2, 5, 1, 6, 5, 4, 3, 2])
