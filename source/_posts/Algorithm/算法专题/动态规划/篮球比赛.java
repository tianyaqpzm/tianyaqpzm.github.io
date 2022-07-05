import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/*
 * 篮球比赛：
 篮球（5V5）比赛中，每个球员拥有一个战斗力，
 每个队伍的所有球员战斗力之和为该队伍的总体战斗力。
 现有10个球员准备分为两队进行训练赛，
 教练希望2个队伍的战斗力差值能够尽可能的小，以达到最佳训练效果。
 给出10个球员的战斗力，如果你是教练，你该如何分队，才能达到最佳训练效果？
 请输出该分队方案下的最小战斗力差值。
输入描述:
10个篮球队员的战斗力（整数，范围[1,10000]），战斗力之间用空格分隔，
如：10 9 8 7 6 5 4 3 2 1
不需要考虑异常输入的场景。
输出描述:
最小的战斗力差值，如：1
示例1
输入
10 9 8 7 6 5 4 3 2 1
输出
1
说明
1 2 5 9 10分为一队，3 4 6 7 8分为一队，两队战斗力之差最小，输出差值1。备注：球员分队方案不唯一，但最小战斗力差值固定是1。
这是一道动态规划的问题，难度较高，而且因为要求均分2组，还不太好转化为典型的背包问题。
 */
public class 篮球比赛 {
    // 10 10 10 10 10 10 10 10 10 10
    public static void main1(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] a = new int[10];
        int[][] dp = new int[15][100005];
        for (int i = 0; i < 10; i++) {
            a[i] = scanner.nextInt();
        }
        for (int i = 0; i <= 11; i++) {
            for (int j = 0; j <= 100000; j++) {
                dp[i][j] = 0;
            }
        }
        int sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += a[i];
        }
        dp[0][0] = 1;
        // d[1][10000] = 1
        // d[0] d[1] d[2] d[3]
        // d[4][100000] 一旦选了j，剩余战力就要减少
        // d[i][k]一组i个人的 战力之和为k，的方式为只有1 代表该组选择i
        // d[i][k] = if(d[i-1][k-a[j]]) ==1 则为1
        for (int j = 0; j < 10; j++) {
            // 5v5 4v4 3v3
            for (int i = 5; i >= 1; i--) {
                for (int k = 100000; k >= a[j]; k--) {
                    if (dp[i - 1][k - a[j]] == 1) {
                        dp[i][k] = 1;
                    }
                }
            }
        }

        int ans = sum;
        //
        for (int i = 0; i <= 100000; i++) {

            if (dp[5][i] == 1) {
                System.out.println(i);
                ans = Math.min(ans, Math.abs(sum - 2 * i));
            }
        }
        System.out.println(ans);
    }

    public static void main(String[] args) {
        int[] arr = new int[] {
                10, 10, 10, 10, 10, 10, 10, 10, 10, 10
        };
        total = Arrays.stream(arr).sum();
        boolean[] visited = new boolean[10];
        fun(arr, visited, new ArrayList<Integer>());
        System.out.println(minValue);
    }

    private static int total = -1;
    private static List<Integer> res = new ArrayList<>();

    private static int minValue = Integer.MAX_VALUE;

    // 10 选5个方案
    private static void fun(int[] arr, boolean[] visited, List<Integer> tempList) {
        if (tempList.size() == 5) {
            int sum = tempList.stream().reduce(0, (a, b) -> a + b);
            minValue = Math.min(minValue, Math.abs(sum * 2 - total));
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            if (visited[i]) {
                continue;
            }
            visited[i] = true;
            tempList.add(arr[i]);
            fun(arr, visited, tempList);
            visited[i] = false;
            tempList.remove(tempList.size() - 1);
        }
    }

}
