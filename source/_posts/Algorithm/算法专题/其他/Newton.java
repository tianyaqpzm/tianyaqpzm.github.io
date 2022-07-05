public class Newton {

    // 自然对数 e
    double e = Math.E;

    // f0(x) = xe^x - 1 代求的原函数
    public double f(double x0) {
        return x0 * Math.pow(e, x0) - 1; // f(x) = xe^x - 1
    }

    // f'(x) = x^e + xe^x 导数
    public double fDao(double x) {
        return Math.pow(e, x) + x * Math.pow(e, x); // f'(x) = e^x + x*e^x
    }

    // 牛顿下山法核心算法函数 x0:初始近似值 s：精度
    public double getAnswer(double x0, double s) { // 存储答案
        double answerNum;// 先计算出f0、fDao0、x1等基本数据
        double f0 = f(x0);
        double fDao0 = fDao(x0);
        double x1 = x0 - f0 / fDao0;// 已经计算出x1，则x1和x0都有了，接下来进入循环。先暂存x1为answer。
        // （若不进入循环用）暂存当前x1为a，暂存当前x2为b（若进入循环用）
        double a = x0;
        double b = x1;
        double c;
        answerNum = b;// 进入牛顿循环
        while (Math.abs(a - b) >= s) { // 牛顿下山法 判断fk和fk-1的大小
            double r = 1.0; // 下山因子 初始值为1.0
            if (f(b - r * f(b) / fDao(b)) >= f(b)) {
                r = r / 2;
            } // c临时变量：把用x1求出x2
            c = b - r * (f(b) / fDao(b));// 更新a和b，相当于把x1赋给x0，x2赋给x1.以便开启下一轮循环
            a = b;
            b = c;
            answerNum = b;
        }
        return answerNum;
    }

    // 主函数测试

    public static void main(String[] args) {

        Newton newton = new Newton();
        // xe^x - 1
        System.out.println(newton.getAnswer(0.5, Math.pow(10, -20)));
    }

}