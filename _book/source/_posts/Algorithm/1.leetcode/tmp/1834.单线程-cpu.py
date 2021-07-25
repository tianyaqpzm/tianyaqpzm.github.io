#
# @lc app=leetcode.cn id=1834 lang=python3
#
# [1834] 单线程 CPU
#

from typing import List
import heapq


# @lc code=start
class Solution:
    def getOrder(self, tasks: List[List[int]]) -> List[int]:
        # 按照时间排序，题目返回排序前堆的索引
        tasks = [(task[0], i, task[1]) for i, task in enumerate(tasks)]
        tasks.sort(key=lambda x: x[0], reverse=False)
        print(tasks)
        backlog = []
        time = 0
        ans = []
        pos = 0
        for _ in tasks:
            # pos 代表任务索引
            if not backlog:
                time = max(time, tasks[pos][0])
                # 把时间内的任务，全部加入到堆中
            while pos < len(tasks) and tasks[pos][0] <= time:
                heapq.heappush(backlog, (tasks[pos][2], tasks[pos][1]))
                pos += 1
            d, j = heapq.heappop(backlog)
            # 更新cpu的时间
            time += d
            ans.append(j)
        return ans


# @lc code=end
test = Solution()
print(test.getOrder([[1, 2], [2, 4], [3, 2], [4, 1]]))
