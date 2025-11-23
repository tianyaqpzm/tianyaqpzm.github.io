import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Solution543 二叉树直径测试用例
 * 
 * 注意：当前 Solution543 类存在以下错误：
 * 1. 算法逻辑错误：当前实现计算的是树的最大深度，而不是直径
 * 2. 直径定义：二叉树的直径是任意两个节点间路径长度的最大值
 * 3. 正确算法应该：对于每个节点，计算左子树深度 + 右子树深度，取最大值
 */
public class Solution543Test {

    // 创建TreeNode的辅助方法
    private TreeNode createNode(int val) {
        return new TreeNode(val);
    }

    // 测试用例1：空树
    @Test
    public void testEmptyTree() {
        Solution543 solution = new Solution543();
        TreeNode root = null;
        int result = solution.diameterOfBinaryTree(root);
        // 期望：0，实际：0 ✓
        assertEquals("空树的直径应该为0", 0, result);
    }

    // 测试用例2：只有一个节点的树
    @Test
    public void testSingleNode() {
        Solution543 solution = new Solution543();
        TreeNode root = createNode(1);
        int result = solution.diameterOfBinaryTree(root);
        // 期望：0，实际：1 ✗ (错误：返回的是深度而不是直径)
        assertEquals("单节点树的直径应该为0", 0, result);
    }

    // 测试用例3：简单的两节点树
    @Test
    public void testTwoNodes() {
        Solution543 solution = new Solution543();
        TreeNode root = createNode(1);
        root.left = createNode(2);
        int result = solution.diameterOfBinaryTree(root);
        // 期望：1，实际：2 ✗ (错误：返回的是深度而不是直径)
        assertEquals("两节点树的直径应该为1", 1, result);
    }

    // 测试用例4：LeetCode示例1
    @Test
    public void testLeetCodeExample1() {
        Solution543 solution = new Solution543();
        /*
         * 1
         * / \
         * 2 3
         * / \
         * 4 5
         */
        TreeNode root = createNode(1);
        root.left = createNode(2);
        root.right = createNode(3);
        root.left.left = createNode(4);
        root.left.right = createNode(5);

        int result = solution.diameterOfBinaryTree(root);
        // 期望：3，实际：3 ✗ (巧合：这个例子中深度等于直径，但算法逻辑错误)
        assertEquals("LeetCode示例1的直径应该为3", 3, result);
    }

    // 测试用例5：左偏树
    @Test
    public void testLeftSkewedTree() {
        Solution543 solution = new Solution543();
        /*
         * 1
         * /
         * 2
         * /
         * 3
         * /
         * 4
         */
        TreeNode root = createNode(1);
        root.left = createNode(2);
        root.left.left = createNode(3);
        root.left.left.left = createNode(4);

        int result = solution.diameterOfBinaryTree(root);
        // 期望：3，实际：4 ✗ (错误：返回的是深度而不是直径)
        assertEquals("左偏树的直径应该为3", 3, result);
    }

    // 测试用例6：右偏树
    @Test
    public void testRightSkewedTree() {
        Solution543 solution = new Solution543();
        /*
         * 1
         * \
         * 2
         * \
         * 3
         * \
         * 4
         */
        TreeNode root = createNode(1);
        root.right = createNode(2);
        root.right.right = createNode(3);
        root.right.right.right = createNode(4);

        int result = solution.diameterOfBinaryTree(root);
        // 期望：3，实际：4 ✗ (错误：返回的是深度而不是直径)
        assertEquals("右偏树的直径应该为3", 3, result);
    }

    // 测试用例7：完全二叉树
    @Test
    public void testCompleteBinaryTree() {
        Solution543 solution = new Solution543();
        /*
         * 1
         * / \
         * 2 3
         * / \ / \
         * 4 5 6 7
         */
        TreeNode root = createNode(1);
        root.left = createNode(2);
        root.right = createNode(3);
        root.left.left = createNode(4);
        root.left.right = createNode(5);
        root.right.left = createNode(6);
        root.right.right = createNode(7);

        int result = solution.diameterOfBinaryTree(root);
        // 期望：4，实际：3 ✗ (错误：返回的是深度而不是直径)
        assertEquals("完全二叉树的直径应该为4", 4, result);
    }

    // 测试用例8：直径不经过根节点
    @Test
    public void testDiameterNotThroughRoot() {
        Solution543 solution = new Solution543();
        /*
         * 1
         * / \
         * 2 3
         * / \
         * 4 5
         * / \
         * 6 7
         * / \
         * 8 9
         */
        TreeNode root = createNode(1);
        root.left = createNode(2);
        root.right = createNode(3);
        root.left.left = createNode(4);
        root.right.right = createNode(5);
        root.left.left.left = createNode(6);
        root.right.right.right = createNode(7);
        root.left.left.left.left = createNode(8);
        root.right.right.right.right = createNode(9);

        int result = solution.diameterOfBinaryTree(root);
        assertEquals("直径不经过根节点的树的直径应该为8", 8, result);
    }

    // 运行所有测试的主方法
    public static void main(String[] args) {
        System.out.println("开始运行 Solution543 二叉树直径测试用例...");
        System.out.println("⚠️  注意：当前 Solution543 类存在算法逻辑错误！");
        System.out.println("❌ 错误：当前实现计算的是树的最大深度，而不是直径");
        System.out.println("✅ 正确：直径 = 左子树深度 + 右子树深度 (取所有节点的最大值)");
        System.out.println();

        Solution543Test test = new Solution543Test();

        try {
            test.testEmptyTree();
            System.out.println("✓ 空树测试通过");

            test.testSingleNode();
            System.out.println("❌ 单节点测试失败 - 期望0，实际返回深度");

            test.testTwoNodes();
            System.out.println("❌ 两节点测试失败 - 期望1，实际返回深度");

            test.testLeetCodeExample1();
            System.out.println("⚠️  LeetCode示例1测试通过(巧合)");

            test.testLeftSkewedTree();
            System.out.println("❌ 左偏树测试失败 - 期望3，实际返回深度");

            test.testRightSkewedTree();
            System.out.println("❌ 右偏树测试失败 - 期望3，实际返回深度");

            test.testCompleteBinaryTree();
            System.out.println("❌ 完全二叉树测试失败 - 期望4，实际返回深度");

            test.testDiameterNotThroughRoot();
            System.out.println("❌ 直径不经过根节点测试失败 - 期望6，实际返回深度");

            System.out.println("\n📋 错误总结：");
            System.out.println("1. 当前算法计算的是树的最大深度，而不是直径");
            System.out.println("2. 直径定义：任意两个节点间路径长度的最大值");
            System.out.println("3. 正确算法：遍历每个节点，计算左子树深度+右子树深度，取最大值");
            System.out.println("4. 需要添加一个全局变量来记录最大直径");

        } catch (AssertionError e) {
            System.err.println("❌ 测试失败: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("❌ 测试异常: " + e.getMessage());
            e.printStackTrace();
        }
    }
}