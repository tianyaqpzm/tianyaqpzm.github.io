from heapq import heappop, heappush


class Solution:
    def getKthMagicNumber(self, k: int) -> int:
        # 小顶堆
        heap = [1]
        numbers = set()
        while k:
            cur = heappop(heap)
            if cur not in numbers:
                k -= 1
                heappush(heap, cur * 3)
                heappush(heap, cur * 5)
                heappush(heap, cur * 7)
            numbers.add(cur)
        return cur

    def getKthMagicNumber2(self, k: int):
        # 三个指针记录因子是 3，5，7 的最小值的索引
        p3 = p5 = p7 = 0
        state = [1] + [0] * (k - 1)
        for i in range(1, k):
            state[i] = min(state[p3] * 3, state[p5] * 5, state[p7] * 7)
            if 3 * state[p3] == state[i]: p3 += 1
            if 5 * state[p5] == state[i]: p5 += 1
            if 7 * state[p7] == state[i]: p7 += 1
        return state[-1]


test = Solution()

print(test.getKthMagicNumber(5))
print(test.getKthMagicNumber2(5))