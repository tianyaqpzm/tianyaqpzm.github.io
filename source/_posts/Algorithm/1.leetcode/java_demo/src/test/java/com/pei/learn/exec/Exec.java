package com.pei.learn.exec;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.io.IOException;

public class Exec {

    @Test
    @DisplayName("mybatis")
    public void test_mybatis() {

        Runtime runtime = Runtime.getRuntime();
        String arg = "";
        try {
            //  尽可能使用api,而不是系统命令
            runtime.exec("cmd.exe /c dir" + arg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
