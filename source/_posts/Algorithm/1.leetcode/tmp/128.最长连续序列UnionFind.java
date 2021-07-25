import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
 * @lc app=leetcode.cn id=128 lang=java
 *
 * [128] 最长连续序列
 */

// @lc code=start
class Solution {
    /**
     * https://leetcode-cn.com/problems/longest-consecutive-sequence/solution/javacong-bao-li-fa-dao-zi-shang-er-xia-dong-tai-gu/
     */
    Set<Integer> checkNums = new HashSet<>();
    Set<Integer> checkInNums = new HashSet<>();

    class UF {
        // 记录树的“重量”
        private int maxValue = 1;
        // 存储⼀棵树
        private Map<Integer, Integer> parent = new HashMap<>();
        private Map<Integer, Integer> size = new HashMap<>();

        public UF(int[] nums) {
            for (int i = 0; i < nums.length; i++) {
                parent.put(nums[i], nums[i]);
                size.put(nums[i], 1);
            }
        }

        private int find(int x) {
            while (parent.get(x) != x) {
                int tmp = parent.get(x);
                // 进⾏路径压缩
                parent.put(x, parent.get(tmp));
                x = tmp;
            }
            return x;
        }

        public void union(int p, int q) {
            if (!checkNums.contains(p) || !checkNums.contains(q)) {
                return;
            }
            int pRoot = find(p);
            int qRoot = find(q);
            if (pRoot == qRoot) {
                return;
            }
            // 将小树 放在大树下面
            if (size.get(pRoot) > size.get(qRoot)) {
                parent.put(pRoot, qRoot);
                size.put(pRoot, size.get(pRoot) + size.get(qRoot));
                maxValue = Math.max(maxValue, size.get(pRoot));
            } else {
                parent.put(qRoot, pRoot);
                size.put(qRoot, size.get(pRoot) + size.get(qRoot));
                maxValue = Math.max(maxValue, size.get(qRoot));
            }
        }

        public int findMax() {
            return maxValue;
        }

    }

    public int longestConsecutive(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        for (int i = 0; i < nums.length; i++) {
            checkNums.add(nums[i]);
        }
        UF uf = new UF(nums);
        for (int i = 0; i < nums.length; i++) {
            uf.union(nums[i], nums[i] - 1);
        }
        return uf.findMax();
    }
}
// @lc code=end
