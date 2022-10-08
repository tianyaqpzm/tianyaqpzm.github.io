package com.pei.learn.lock.jol;

import org.openjdk.jol.info.ClassLayout;

/**
 * 功能描述 java对象模型：
 *
 * @author pei
 * @since 2022-10-02
 */
public class T01_HelloJOL {

    public static void main(String[] args) {
        Object o = new Object();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());

        synchronized (o) {
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }
    }
}
