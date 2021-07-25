class TreeNode:
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None


class Solution:
    def fibonacci(self, n):
        if n == 0 or n == 1: return n
        return self.fibonacci(n - 1) + self.fibonacci(n - 2)
