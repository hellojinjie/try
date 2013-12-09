package com.conger.test.r1;

import java.io.File;

public class TmpTest {

    public static void main(String[] args) {
        File tmp = new File(System.getProperty("java.io.tmpdir") + "/hive-junit-test-" + System.nanoTime());
        tmp.mkdir();
        tmp.deleteOnExit();
        System.out.println(tmp.getAbsolutePath());
    }
}
