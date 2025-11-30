#
# @lc app=leetcode.cn id=146 lang=python3
#
# [146] LRU 缓存
#

# @lc code=start
class LRUCache:

    def __init__(self, capacity: int):
        self.capacity = capacity
        # 记录缓存数据
        self.cache = {}
        # 记录使用顺序，最近使用的在末尾
        self.order = []

    def get(self, key: int) -> int:
        if key in self.cache:
            # 更新使用顺序
            self.order.remove(key)
            self.order.append(key)
            return self.cache[key]
        return -1

    def put(self, key: int, value: int) -> None:
        if key in self.cache:
            # 更新值和使用顺序
            self.cache[key] = value
            self.order.remove(key)
            self.order.append(key)
        else:
            if len(self.cache) >= self.capacity:
                # 移除最久未使用的项
                lru_key = self.order.pop(0)
                del self.cache[lru_key]
            # 添加新项
            self.cache[key] = value
            self.order.append(key)


# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)
# @lc code=end

