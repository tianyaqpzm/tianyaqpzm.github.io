package com.pei.learn;

import static org.assertj.core.api.Assertions.fail;

public class UTHelper {

    public static void invokeWithException(Invocation invocation) {
        invokeWithException(invocation, "");
    }

    public static void invokeWithException(Invocation invocation, String message) {
        invokeWithException(invocation, Throwable.class, message);
    }

    public static void invokeWithException(Invocation invocation, Class<? extends Throwable> expectExceptionClass) {
        invokeWithException(invocation, expectExceptionClass, "");

    }

    /**
     * 比对异常是否一致
     *
     * @param invocation
     * @param expectExceptionClass
     * @param message
     */
    public static void invokeWithException(Invocation invocation, Class<? extends Throwable> expectExceptionClass, String message) {
        try {
            invocation.invoke();
        } catch (Throwable e) {
            if (!e.getClass().isAssignableFrom(expectExceptionClass)) {
                fail(message + ", except exception [" + expectExceptionClass.getName() + "], actual " +
                        "exception [" + e.getClass().getName() + "]");
            }
        }
    }
}
