
#
# @lc app=leetcode.cn id=27 lang=python3
#
# [27] 移除元素
#

class TreeNode:
    def __init__(self, x):
        self.val = x
        self.left = None 
        self.right = None

class Solution:
    def isBalanced(self, root:TreeNode)-> bool:
        # 递归计算树高度
        def dfs(node, depth):
            # 退出条件
            if not node:return 0
            l = dfs(node.left,depth +1)
            r = dfs(node.right, depth + 1)
            # 
            return max(l,r)+ 1 
        if not root: return True
        if abs(dfs(root.left, 0) - dfs(root.right, 0)) >1 return False
        return self.isBalanced(root.left) and self.isBalanced(root.right)
test = Solution()
li = [3,9,20,null,null,15,7]
test.isBalanced(li)

# @lc code=end