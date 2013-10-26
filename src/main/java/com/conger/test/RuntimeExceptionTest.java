package com.conger.test;

public class RuntimeExceptionTest {

    public static void main(String[] args) {
        new Thread(new Runnable() {
            public void run() {
                throw new RuntimeException();
            }
        }).start();
    }
}
