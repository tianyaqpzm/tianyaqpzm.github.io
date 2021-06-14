#
# @lc app=leetcode.cn id=28 lang=python3
#
# [28] 实现 jump()
#

from typing import List


# @lc code=start
class Solution:
    def videoStitching(self, clips: List[List[int]], T: int) -> int:
        furthest = [0] * (T)
        for s, e in clips:
            for i in range(s, e + 1):
                if i >= T: break
                furthest[i] = max(furthest[i], e)
        end = last = ans = 0
        for i in range(T):
            last = max(last, furthest[i])
            if last == i: return -1
            if end == i:
                ans += 1
                end = last
        return ans


# @lc code=end

test = Solution()
# l = [[0, 2], [4, 6], [8, 10], [1, 9], [1, 5], [5, 9]]
l = [[0, 1], [1, 2]]
print(test.videoStitching(l, 10))
