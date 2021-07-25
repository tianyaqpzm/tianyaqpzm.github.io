from typing import List

# python中的正无穷或负无穷，使用float(“inf”)或float(“-inf”)来表示。


class Solution:
    def subSort(self, A: List[int]) -> List[int]:
        maxV, minV = float('-inf'), float('inf')
        right = left = -1
        for i in range(len(A)):
            if A[i] < maxV:
                right = i
            maxV = max(maxV, A[i])
        for i in range(len(A) - 1, -1, -1):
            if A[i] > minV:
                left = i
            minV = min(minV, A[i])
        return [-1, 1]


print(float(-1.2))
print(float(-1))
print(float(1.2))
print(float(-1.6))
test = Solution()
test.subSort([1, 2, 4, 7, 10, 11, 7, 12, 6, 7, 16, 18, 19])
