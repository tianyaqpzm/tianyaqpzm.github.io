import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.xml.stream.events.EntityDeclaration;

import apple.laf.JRSUIUtils.Tree;

/*
 * @lc app=leetcode.cn id=95 lang=java
 *
 * [95] 不同的二叉搜索树 II
 */

// @lc code=start
/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode
 * left; TreeNode right; TreeNode() {} TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) { this.val = val; this.left
 * = left; this.right = right; } }
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Solution {
    public List<TreeNode> generateTrees(int n) {
        return gen(1, n);
    }

    private List<TreeNode> gen(int start, int end) {
        LinkedList<TreeNode> res = new LinkedList<TreeNode>();
        if (start > end) {
            res.add(null);
            return res;
        }
        for (int i = start; i <= end; i++) {
            List<TreeNode> leftTrees = gen(start, i - 1);
            List<TreeNode> rightTrees = gen(i + 1, end);
            for (TreeNode left : leftTrees) {
                for (TreeNode right : rightTrees) {
                    TreeNode currTree = new TreeNode(i);
                    currTree.left = left;
                    currTree.right = right;
                    res.add(currTree);
                }
            }
        }
        return res;
    }

    // public static void main(String[] args) {
    // Solution95 solution95 = new Solution95();
    // solution95.generateTrees(3);
    // }
}
// @lc code=end
