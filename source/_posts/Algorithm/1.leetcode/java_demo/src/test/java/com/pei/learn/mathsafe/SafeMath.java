package com.pei.learn.mathsafe;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.math.BigInteger;

public class SafeMath {


    @Test
    @DisplayName("校验前进行标准化")
    public void test_normalize(int left, int right) {
//        1、先决条件检查
        if (right > 0 ? left > Integer.MAX_VALUE - right : left < Integer.MIN_VALUE - right) {
            throw new ArithmeticException("integer");
        }


        boolean mulCondition = right < -1 ? left > Integer.MIN_VALUE / right || left < Integer.MAX_VALUE / right
                : right == -1 && left == Integer.MIN_VALUE;
        if (right > 0 ? left > Integer.MAX_VALUE / right || left < Integer.MIN_VALUE / right : mulCondition) {
            throw new ArithmeticException("integer");
        }

        //2、 java8 Math.*Exact()  抛异常  没有：除法和取绝对值的方法
        int i = Math.addExact(left, right);

//        3、 向上类型转换,期望int,
        final long reg = 100L;
        int reg1 = (int) reg;
//        4、使用BigInteger
        BigInteger bigMaxInteger = BigInteger.valueOf(Integer.MAX_VALUE);
        BigInteger bigMinInteger = BigInteger.valueOf(Integer.MIN_VALUE);
        BigInteger multiply = BigInteger.valueOf(left).multiply((BigInteger.valueOf(right)));
        int i1 = multiply.intValue();  // 安全转换

    }
}
