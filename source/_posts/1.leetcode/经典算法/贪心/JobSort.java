
/*
*日期:2010-04-19 22:02
*开发者:heroyan
*联系方式:zndxysf@126.com
*功能:带有限期的作业排序，假设每个作业都可在单位时间内完成，完成后获得一定的效益，求效益的最大值和作业顺序
*/
import java.util.Scanner;
import java.util.Arrays;

public class JobSort {
	private static int MAX = 100;
	private Job[] jbs = new Job[MAX];
	private boolean[] visited = new boolean[MAX];// 标志此时间是否已经被安排作业
	private int[] target = new int[MAX];// 目标编号
	private double maxfit = 0.0;// 最大收益
	private int n;// 作业个数

	public JobSort() {
	}

	class Job {
		public int no;// 序号
		public int tt;// 期限
		public double fit;// 效益
	}

	public static void main(String args[]) {
		int[] d = new int[] { 100, 27, 15, 10 };
		int[] x = new int[] { 0, 3, 2, 1 };
		// int[] x = new int[4];
		JobSort job = new JobSort();
		job.js(d, x, x.length);

		JobSort sp = new JobSort();
		sp.init();
		sp.js();
		sp.print();
	}

	// 初始化
	public void init() {

		Scanner scan = new Scanner(System.in);
		// int p, q;
		// double w;
		System.out.println("Input the job number:");
		// 作业总数
		n = scan.nextInt();
		System.out.println("Input the time and benifit:");
		for (int i = 1; i <= n; ++i) {
			jbs[i] = new Job();
			jbs[i].no = i;
			jbs[i].tt = scan.nextInt(); // 期限
			jbs[i].fit = scan.nextDouble();
		}
		scan.close();
		maxfit = 0.0;
		Arrays.fill(visited, false);
		sort();
	}

	// 按效益从大到小排序
	public void sort() {
		for (int i = 1; i < n; ++i) {
			for (int j = i + 1; j <= n; ++j) {
				// 如果后面大，那么调换， 一次过后，最小的放在最后
				if (jbs[i].fit < jbs[j].fit) {
					Job tmp = jbs[i];
					jbs[i] = jbs[j];
					jbs[j] = tmp;
				}
			}
		}
	}

	// 按效益大小对x 进行 排序,
	// x[j] 作业编号
	/**
	 * 
	 * @param D 时限， 已排序
	 * @param x 结果
	 * @param n 作业数
	 * @return
	 */
	public int[] js(int[] d, int[] x, int n) {
		int k = 0;
		x[0] = 0;
		for (int j = 1; j < n; j++) {
			// 如果作业j 能在时限内完成就加进去
			int r = k; // k 现在的位置
			// 寻找j 插入对位置， 在 仅次于比他大的效益前面，
			// 检查x[r] 是否可以往后移动
			while (r >= 0 && d[x[r]] > d[j] && d[x[r]] > r + 1) {
				r--;
			}
			if (r < 0 || d[x[r]] <= d[j] && d[x[r]] > r + 1) {
				// 后面的平移
				for (int i = k; i >= r + 1; i--) {
					x[i + 1] = x[i];
				}
				x[r + 1] = j;
				k++;
			}

		}
		return x;
	}

	// 算法主体
	public void js() {
		int cnt = 0;
		for (int i = 1; i <= n; ++i) {
			if (!visited[jbs[i].tt]) {
				visited[jbs[i].tt] = true;
				target[cnt++] = jbs[i].no;
				maxfit += jbs[i].fit;
			}
		}
		target[cnt] = -1;
	}

	// 打印结果
	public void print() {
		for (int i = 0; target[i] > 0; ++i) {
			System.out.println("NO." + target[i] + " job is choosed");
		}
		System.out.println("maximum fit is " + maxfit);
	}
}
