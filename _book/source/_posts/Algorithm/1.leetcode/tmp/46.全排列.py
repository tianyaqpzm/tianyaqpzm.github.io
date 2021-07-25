#
# @lc app=leetcode.cn id=46 lang=python3
#
# [46] 全排列
#

# @lc code=start
from typing import List


class Solution:
    def permute2(self, nums: List[int]) -> List[List[int]]:
        import itertools
        return itertools.permutations(nums)

    def permute(self, nums: List[int]) -> List[List[int]]:
        res = []

        def backtrack(nums, pre_list):
            if len(nums) <= 0:
                res.append(pre_list)
            else:
                for i in nums:
                    p_list = pre_list.copy()
                    p_list.append(i)
                    left_nums = nums.copy()
                    left_nums.remove(i)
                    backtrack(left_nums, p_list)

        backtrack(nums, [])
        return res

    def permute3(self, nums: List[int]) -> List[List[int]]:
        res = []
        length = len(nums)

        def backtrack(start=0):
            if start == length:
                res.append(nums[:])
            for i in range(start, length):
                #  1 3 2,  当第二次循环选择3，那么之后再改回2
                nums[start], nums[i] = nums[i], nums[start]
                backtrack(start + 1)
                nums[start], nums[i] = nums[i], nums[start]

        backtrack()
        return res


# @lc code=end

test = Solution()
print(test.permute3([1, 2, 3]))
