from typing import List


class Solution:
    """Max Subarray implementation copied from the legacy file.

    Placed under a valid module name so it can be imported in tests.
    """

    def maxSubArray(self, nums: List[int]) -> int:
        if not nums:
            # definition: for empty input raise ValueError
            raise ValueError("nums must be non-empty")
        max_sum = current_sum = nums[0]
        for num in nums[1:]:
            current_sum = max(num, current_sum + num)
            max_sum = max(max_sum, current_sum)
        return max_sum


__all__ = ["Solution"]
