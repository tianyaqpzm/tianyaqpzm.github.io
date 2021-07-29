#
# @lc app=leetcode.cn id=98 lang=python3
#
# [98] 验证二叉搜索树
#


# @lc code=start
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def isValidBST(self, root: TreeNode) -> bool:
        WHITE, GRAY = 0, 1
        stack = [(WHITE, root)]
        previous = None
        while stack:
            color, node = stack.pop()
            if node is None:
                continue
            if color == WHITE:
                stack.append((WHITE, node.right))
                # 中序遍历
                stack.append((GRAY, node))
                stack.append((WHITE, node.left))
            else:
                if previous != None and node.val <= previous.val:
                    return False
                previous = node
                # 加入返回列表
                # res.append(node.val)
                # print(node.val + ",")
        return True


# @lc code=end
