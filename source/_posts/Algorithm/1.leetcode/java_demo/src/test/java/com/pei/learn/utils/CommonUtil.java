package com.pei.learn.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;


@Slf4j
public class CommonUtil {

    public static void sleep(int timeout, TimeUnit unit) {
        try {
            unit.sleep(timeout);
            // Thread.sleep(3000);
        } catch (InterruptedException e) {
            log.error("failed to sleep,", e);
        }
    }
}
