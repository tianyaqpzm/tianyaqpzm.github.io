#
# @lc app=leetcode.cn id=47 lang=python3
#
# [47] 全排列 II
#

from typing import List


# @lc code=start
class Solution:
    def permuteUnique2(self, nums: List[int]) -> List[List[int]]:
        """与46题一样，当然也可以直接调用itertools的函数，然后去重"""
        import itertools
        return list(set(itertools.permutations(nums)))

    def permuteUnique(self, nums: List[int]) -> List[List[int]]:
        nums.sort()
        res = []

        def backtrack(nums, pre_list):
            if len(nums) <= 0:
                res.append(pre_list)
            else:
                for i in range(len(nums)):
                    # 同样值的数字只能出现一次
                    if i > 0 and nums[i] == nums[i - 1]:
                        continue
                    p_list = pre_list.copy()
                    p_list.append(nums[i])
                    left_nums = nums.copy()
                    left_nums.pop(i)
                    backtrack(left_nums, p_list)

        backtrack(nums, [])
        return res


# @lc code=end

test = Solution()
print(test.permuteUnique([1, 1, 2]))
