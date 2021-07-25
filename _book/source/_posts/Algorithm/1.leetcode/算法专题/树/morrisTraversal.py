class Solution:
    # 既不使用递归，也不借助栈，  在O(1) 空间完成这个过程
    def morrisTrav(self, root):
        curr = root
        while curr:
            if curr.left is None:
                print(curr.data, end="")
                curr = curr.right
            else:
                prev = curr.left
                while prev.right is not None and prev.right is not curr:
                    prev = prev.right
                if prev.right is curr:
                    prev.right = None
                    curr = curr.right
                else:
                    print(curr.data, end="")
                    prev.right = curr
                    curr = curr.left
