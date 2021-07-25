from heapq import *

#  函 数      描 述
# heappush(heap, x)                                        将x压入堆中
# heappop(heap)                                      从堆中弹出最小的元素
# heapify(heap)                                           让列表具备堆特征
# nlargest(n, iter)                                       返回iter中n个最大的元素
# nsmallest(n, iter)                                   返回iter中n个最小的元素

data = list(range(10))
heap = []
for n in data:
    heappush(heap, n)
print(heap)
