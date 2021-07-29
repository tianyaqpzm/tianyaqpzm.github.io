class Solution:
    def binarySearch(self, nums, target):
        l, r = 0, len(nums) - 1
        while l <= r:
            mid = (l + r) >> 1
            if nums[mid] == target: return mid
            if nums[mid] < target: l = mid + 1
            if nums[mid] > target: r = mid - 1
        return -1


test = Solution()
print(test.binarySearch([1, 2, 3, 4, 5], 4))
