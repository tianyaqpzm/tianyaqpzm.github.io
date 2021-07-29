
public class Job {

    // 按效益大小对x 进行 排序,
    // x[j] 作业编号
    /**
     * 
     * @param D 时限， 已排序
     * @param x
     * @param n 作业数
     * @return
     */
    public static int[] job(int[] d, int[] x, int n) {
        int k = 0;
        x[0] = 0;
        for (int j = 1; j < n; j++) {
            // 如果作业j 能在时限内完成就加进去
            int r = k; // k 现在的位置
            // 寻找j 插入对位置， 在 仅次于比他大的效益前面，
            while (r > 0 && d[x[r]] > d[j] && d[x[r]] > r + 1) {
                r--;
            }
            if (r < 0 || d[x[r]] < d[j] && d[x[r]] > r + 1) {
                // 后面的平移
                for (int i = k; i > r + 1; i--) {
                    x[i + 1] = x[i];
                }
                x[r + 1] = j;
                k++;
            }

        }
        return x;
    }

}
