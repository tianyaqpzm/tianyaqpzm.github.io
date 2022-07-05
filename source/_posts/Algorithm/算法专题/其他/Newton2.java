public class Newton2 {
    // https://www.nowcoder.com/discuss/729934?channel=-1&order=0&page=0&pos=19&source_id=discuss_center_5_nctrack&type=5

    // y^7+0.5y=x,给定x求y的牛顿下山法解析

    private static double JD = 0.001;

    public static void main(String[] args) {
        int x = 129;

        double pyn = 0.5 * x;
        double tyn = pyn;
        while (compute(tyn, x) > JD && count < 20000) {
            pyn = tyn;
            tyn = next(tyn, x);
            double mark = 0.5;
            if (wc(tyn, pyn, x)) {
                tyn = (tyn - pyn) * mark + pyn;
                mark /= 2;
            }
            ++count;
        }
        System.out.println(pyn);

    }

    static double Next(double pre, double x) {
        double ans = 0;
        ans = pre - (pow(pre, 7) + 0.5 * pre - x) / (7 * pow(pre, 6) + 0.5);
        return ans;
    }

    private static double compute(double y, double x) {
        return Math.pow(y, 7) + 0.5 * y - x;
    }

}