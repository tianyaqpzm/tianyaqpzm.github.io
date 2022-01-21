import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
 * @lc app=leetcode.cn id=103 lang=java
 *
 * [103] 二叉树的锯齿形层序遍历
 */

// @lc code=start
/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode
 * left; TreeNode right; TreeNode() {} TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) { this.val = val; this.left
 * = left; this.right = right; } }
 */
class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        Queue<TreeNode> deque = new LinkedList<TreeNode>();
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (root == null) {
            return res;
        }
        deque.offer(root);
        boolean flag = true;
        while (!deque.isEmpty()) {
            // 关键点在打印的顺序，而不是天津的顺序，
            // 加入子节点要保持从右到左
            Deque<Integer> tempList = new LinkedList<Integer>();
            int size = deque.size();
            for (int i = size - 1; i >= 0; i--) {
                TreeNode poll = deque.poll();
                if (flag) {
                    tempList.addFirst(poll.val);
                } else {
                    tempList.addLast(poll.val);
                }
                if (poll.right != null) {
                    deque.offer(poll.right);
                }
                if (poll.left != null) {
                    deque.offer(poll.left);
                }
            }
            flag = !flag;
            res.add(new LinkedList<Integer>(tempList));
        }
        return res;
    }

    // public class TreeNode {
    // int val;
    // TreeNode left;
    // TreeNode right;

    // TreeNode() {
    // }

    // TreeNode(int val) {
    // this.val = val;
    // }

    // TreeNode(int val, TreeNode left, TreeNode right) {
    // this.val = val;
    // this.left = left;
    // this.right = right;
    // }
    // }
}
// @lc code=end
