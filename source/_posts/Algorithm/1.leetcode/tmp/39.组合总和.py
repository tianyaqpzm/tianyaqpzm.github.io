#
# @lc app=leetcode.cn id=39 lang=python3
#
# [39] 组合总和
#

from typing import List


# @lc code=start
class Solution:
    def combinationSum(self, candidates: List[int],
                       target: int) -> List[List[int]]:
        size = len(candidates)
        if size < 0:
            return []
        print(candidates.sort())
        path = []
        res = []
        self._find_path(target, path, res, candidates, 0, size)
        return res

    def _find_path(self, target, path, res, candidates, begin, size):
        if target == 0:
            res.append(path.copy())
        else:
            for i in range(begin, size):
                left_num = target - candidates[i]
                if left_num < 0:
                    # 剩余值为负数， 剪枝
                    break
                # 否则把当前值加入路径
                path.append(candidates[i])
                self._find_path(left_num, path, res, candidates, i, size)
                path.pop()


# @lc code=end
tst = Solution()
print(tst.combinationSum([2, 3, 6, 7], 7))
