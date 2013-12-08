package com.conger.test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class ZipTest {
    public static void main(String[] argv) throws Exception {
        
        long start = System.currentTimeMillis();
        FileInputStream originInput = new FileInputStream(new File("/home/jj/heavyTableList"));
        byte[] buffer = new byte[1024 * 1024 * 200];
        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
        int count = 0;
        int bytes = 0;
        while((bytes = originInput.read(buffer)) != -1) {
            count += bytes;
            byteBuffer.write(buffer, 0, bytes);
        }
        System.out.println("read " + count);
        originInput.close();
        System.out.println("time to read origin " + (System.currentTimeMillis() - start));
       
        for (int i = 0; i < 10; i++) {
        start = System.currentTimeMillis();
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        GZIPOutputStream zipOutput = new GZIPOutputStream(bao, 1024 * 1024);
        zipOutput.write(byteBuffer.toByteArray(), 0, count);
        zipOutput.flush();
        zipOutput.close();
        System.out.println("time to zip  " + (System.currentTimeMillis() - start));
        
        start = System.currentTimeMillis();
        GZIPInputStream zipInput = new GZIPInputStream(new ByteArrayInputStream(bao.toByteArray()), 1024 * 1024);
        count = 0;
        bytes = 0;
        byteBuffer = new ByteArrayOutputStream();
        while((bytes = zipInput.read(buffer)) != -1) {
            count += bytes;
            byteBuffer.write(buffer, 0, bytes);
        }
        zipInput.close();
        System.out.println("time to unzip " + (System.currentTimeMillis() - start));
        }
    }
}
