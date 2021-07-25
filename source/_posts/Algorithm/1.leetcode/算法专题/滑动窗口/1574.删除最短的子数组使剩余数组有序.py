#
# @lc app=leetcode.cn id=1574 lang=python3
#
# [1574] 删除最短的子数组使剩余数组有序
#

from typing import List


# @lc code=start
class Solution:
    def findLengthOfShortestSubarray(self, arr: List[int]) -> int:
        n = len(arr)
        l, r = 0, n - 1
        # 从左方缩小范围
        while l < n - 1 and arr[l] <= arr[l + 1]:
            l += 1
        if l == n - 1:
            return 0
        # 从右侧缩小范围
        while r > 0 and arr[r] >= arr[r - 1]:
            r -= 1
        ans = min(r, n - l - 1)
        i = 0
        # 若左侧为10 ，右侧为2 ，需要找到右侧 大于等于 左侧的距离
        while i <= l and r < n:
            print(str(arr[i]) + ' ' + str(arr[r]))
            if arr[i] <= arr[r]:
                ans = min(ans, r - i - 1)
                i += 1
            else:
                r += 1
        return ans


# @lc code=end

test = Solution()
print(test.findLengthOfShortestSubarray([1, 2, 3, 10, 4, 2, 3, 5]))
