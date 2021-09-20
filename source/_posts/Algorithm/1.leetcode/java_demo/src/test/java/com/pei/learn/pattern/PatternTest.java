package com.pei.learn.pattern;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.text.Normalizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternTest {

    private final static String CONTENT = "javascript";

    @Test
    @DisplayName("正则方式1")
    public void test_binary_search() {
        Pattern js = Pattern.compile("js", Pattern.CASE_INSENSITIVE);
        Matcher matcher = js.matcher("javascript");
        Assertions.assertThat(matcher.matches()).isFalse();
    }


    /**
     * JDK引擎时NFA, 其回溯机制 导致 如果不匹配时，所花费时间更多，要匹配所有失败情况，证明不匹配
     * 存在ReDos攻击对正则主要有两类
     * 1、包含自我重复的分组  ^(\d+)+$, ^(\d*)*$, ^(\d+)*$, ^(\d+|\s+)*$
     * 2、包含替换的重复性分组： ^(\d|\d|\d)+$   ^(\d|\d?)+$
     * 防范：1、 匹配的文本长度校验，2、写简单的正则  3、减少分组  4、减少动态构建正则
     */
    @Test
    @DisplayName("ReDos攻击")
    public void test_binary_redos() {
        Pattern js = Pattern.compile("js", Pattern.CASE_INSENSITIVE);
        Matcher matcher = js.matcher("javascript");
        Assertions.assertThat(matcher.matches()).isFalse();
    }

    private void white(String ser) {
        StringBuilder stringBuilder = new StringBuilder(ser.length());
        for (int i = 0; i < ser.length(); i++) {
            char c = ser.charAt(i);
            // 字母或数字
            if (Character.isLetterOrDigit(c) || c == ' ' || c == '\'') {
                stringBuilder.append(c);
            }
        }
        ser = stringBuilder.toString();


    }

    @Test
    @DisplayName("校验前进行标准化")
    public void test_normalize() {
        String script = "\uFE64" + "script" + "\uFE65";
        String s = Normalizer.normalize(script, Normalizer.Form.NFC);

        Pattern compile = Pattern.compile("[<>]");
        Matcher matcher = compile.matcher(script);
        if (matcher.find()) {
            throw new IllegalStateException();
        }
    }

}
