#
# @lc app=leetcode.cn id=3 lang=python3
#
# [3] 无重复字符的最长子串
#
from collections import defaultdict


# @lc code=start
class Solution:
    def lengthOfLongestSubstring(self, s: str) -> int:
        l = 0
        ans = 0
        counter = defaultdict(lambda: 0)
        for r in range(len(s)):
            # 获取当前元素对应的位置
            while counter.get(s[r], 0) != 0:
                # 如果当前遍历字符出现过，
                # 缩小窗口，获取上一次出现的索引位置，
                counter[s[l]] = counter.get(s[l], 0) - 1
                l += 1
            counter[s[r]] += 1
            ans = max(ans, r - l + 1)
        return ans


test = Solution()
test.lengthOfLongestSubstring("abccbcbb")

# @lc code=end
