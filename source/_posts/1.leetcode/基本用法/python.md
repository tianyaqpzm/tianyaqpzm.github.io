## 1、基本类型

### 1.1 基本类型

```python
maxV, minV = float('-inf'), float('inf')




```

### 1.2 列表

### 1.3 树

#### 1.3.1 二叉平衡树

如果一个树遍历的结果是有序数组，那么他也是一个二叉查找树(BST)

## 2、基本工具包

### 2.1 排序 sort

> sorted(iterable[, cmp[, key[, reverse]]])

> iterable.sort(cmp[, key[, reverse]])

```
# 多级排序，先按照第3个元素排序，然后按照第2个元素排序：
tasks.sort(key=lambda task: (task[2], task[1]), reverse=True)

from operator import itemgetter
print(sorted(alist, key=lambda x: itemgetter(2, 1)(x), reverse=False))

```

#### 2.1.2 [二分法排序](https://docs.python.org/zh-cn/3.7/library/bisect.html)

|                                      |                                                                            |     |
| ------------------------------------ | -------------------------------------------------------------------------- | --- |
| bisect.insort(a, x, lo=0, hi=len(a)) | 类似于 bisect.insort_left, 但是把 _x_ 插入到 _a_ 中已存在元素 _x_ 的右侧。 |     |
| bisect.bisect(a, x, lo=0, hi=len(a)) | 类似于 bisect_left(), 返回 的插入点是 _a_ 中已存在元素 _x_ 的右侧。        |     |
|                                      |                                                                            |     |

##### 应用：数字表查询

```python
import bisect
# 查出其对应的字母等级：90 分及以上是 'A'，80 到 89 是 'B'，以此类推
def grade(score, breakpoints=[60, 70, 80, 90], grades='FDCBA'):
    i = bisect.bisect(breakpoints, score)
    return grades[i]

print([grade(score) for score in [33, 99, 77, 70, 89, 90, 100]])
# ['F', 'A', 'C', 'C', 'B', 'A', 'A']
```

### 2.2 自定义缓存：

_# 被 lru_cache 装饰的函数会有 cache_clear 和 cache_info 两个方法，分别用于清除缓存和查看缓存信息。_

使用方法：

```python
# 被 lru_cache 装饰的函数会有 cache_clear 和 cache_info 两个方法，分别用于清除缓存和查看缓存信息。
# @functools.lru_cache(maxsize=None, typed=False)

from functools import lru_cache
# 最近最少使用
@lru_cache(None)
def add(x, y):
    print("calculating: %s + %s" % (x, y))
    return x + y

print(add(1, 2))
print(add(1, 2))
# vscode 编译报错，不影响使用
print(add.cache_info())
add.cache_clear()

```

```python
# 自定义装饰器函数  缓存
import functools

from functools import wraps


# 自定义装饰器函数  缓存
def memo(func):
    cache = {}

    @wraps(func)
    def wrap(*args):
        if args not in cache:
            cache[args] = func(*args)
        return cache[args]

    return wrap


import unittest


class TestMyCache(unittest.TestCase):
    def test_lru_with_keyword_args(self):
        @functools.lru_cache()
        def fib(n):
            if n < 2:
                return n
            return fib(n=n - 1) + fib(n=n - 2)

        self.assertEqual(
            [fib(n=number) for number in range(16)],
            [0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610])
        self.assertEqual(
            fib.cache_info(),
            functools._CacheInfo(hits=28, misses=16, maxsize=128, currsize=16))
        fib.cache_clear()
        self.assertEqual(
            fib.cache_info(),
            functools._CacheInfo(hits=0, misses=0, maxsize=128, currsize=0))

    def test_self_with_keyword_args(self):
        @memo
        def fib(i):
            if i < 2: return 1
            return fib(i - 1) + fib(i - 2)

        self.assertEqual(fib(100), 573147844013817084101)

```

### 2.3 集合

```
import collections
q1, q2 = collections.deque(), collections.deque()

```
