package com.pei.learn.labada;

import com.pei.learn.UTHelper;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;


public class ArraysDemoTest {

    @Test
    @DisplayName("数组转换为list")
    public void test_array_to_list() {
        final List<Integer> list = Arrays.asList(1, 2, 3);
        assertThat(list).containsSequence(1, 2, 3);
//        转出来的list 不支持add / remove 操作
        UTHelper.invokeWithException(() -> list.add(4), UnsupportedOperationException.class,
                "this list should not support and operation");
        UTHelper.invokeWithException(() -> list.remove(4), UnsupportedOperationException.class,
                "this list should not support and operation");
    }

    @Test
    @DisplayName("二分搜索有序数组")
    public void test_binary_search() {
        assertThat(Arrays.binarySearch(new int[]{1, 2, 3, 5, 7, 9, 11}, 3)).isEqualTo(2);
//        找不到数据的话， 返回值为负数
        assertThat(Arrays.binarySearch(new int[]{1, 2, 3, 5, 7, 9, 11}, 4)).isLessThan(0);

        // 不支持逆序数组的二分搜索
        assertThat(Arrays.binarySearch(new int[]{11, 9, 7, 5, 3, 2, 1}, 3)).isLessThan(4);

    }

    @Test
    @DisplayName("拷贝数组")
    public void test_copy_array() {
        final int[] array = {1, 2, 3, 5};
        assertThat(Arrays.copyOf(array, 3)).containsExactly(1, 2, 3);
//        数组长度增长
        assertThat(Arrays.copyOf(array, 5)).containsExactly(1, 2, 3, 5, 0);
//        拷贝部分数组
        assertThat(Arrays.copyOfRange(array, 0, 2)).containsExactly(1, 2);
        // 下限越界
        UTHelper.invokeWithException(() -> Arrays.copyOfRange(array, -2, 2), ArrayIndexOutOfBoundsException.class,
                "下限越界");

    }

    @Test
    @DisplayName("数组比较")
    public void test_compare_array() {
        final int[] array = {1, 2, 3, 5};
        assertThat(Arrays.equals(new int[]{1, 2, 3}, new int[]{1, 2, 3})).isTrue();
    }


    @Test
    @DisplayName("数组流式处理")
    public void test_stream_array() {
        assertThat(Arrays.stream(new int[]{1, 2, 3, 3, 4, 5, 6}).filter(x -> x == 3).count()).isEqualTo(2);
        // boxed 拆箱类型stream 转 装箱类型, 基本类型转为装箱类型
        assertThat(Arrays.stream(new int[]{1, 2, 3, 4, 5, 6, 7})
                .filter(x -> x != 3)
                .map(x -> x * 2)
                .boxed()
                .sorted((a, b) -> b - a)
                .collect(Collectors.toList())).containsExactly(14, 12, 10, 8, 4, 2);
    }


    @Test
    @DisplayName("数组toString")
    public void test_array_tostring() {
        assertThat(Arrays.toString(new int[]{1, 2, 3})).isEqualTo("[1, 2, 3]");
    }


}