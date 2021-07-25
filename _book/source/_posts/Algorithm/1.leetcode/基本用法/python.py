# #

# sorted(iterable[, cmp[, key[, reverse]]])

# iterable.sort(cmp[, key[, reverse]])

from operator import itemgetter

alist = [('2', '3', '10'), ('1', '2', '3'), ('5', '6', '7'), ('2', '5', '10'),
         ('2', '4', '10')]

a1 = alist[:]
a1.sort(key=lambda task: (task[2], task[1]), reverse=True)

# print(sorted(alist, key=lambda x: map(int,
#   itemgetter(2, 1)(x)), reverse=False))
print(sorted(alist, key=lambda x: itemgetter(2, 1)(x), reverse=False))

a = [1, 2, 3]
b = itemgetter(1)  #定义函数b，获取对象的第1个域的值
b(a)
# 2
b = itemgetter(1, 0)  #定义函数b，获取对象的第1个域和第0个的值
b(a)
# (2, 1)

import collections
q1, q2 = collections.deque(), collections.deque()

stack = [1, 2]
print(stack.remove(1))
