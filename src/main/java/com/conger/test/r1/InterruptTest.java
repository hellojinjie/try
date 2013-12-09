package com.conger.test.r1;

public class InterruptTest {

    public static void main(String[] args) {
        Thread.currentThread().interrupt();
        while (true) {
            try {
                synchronized (InterruptTest.class) {
                    InterruptTest.class.wait();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
        }
    }
}
