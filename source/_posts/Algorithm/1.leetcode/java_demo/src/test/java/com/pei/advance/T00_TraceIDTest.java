package com.pei.advance;

import java.security.SecureRandom;

public class T00_TraceIDTest {

    public static void main(String[] args) throws InterruptedException {
        final int  i= new SecureRandom().nextInt();

        final Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(i);
            }
        });
        thread.start();
        thread.join();
    }
}
