package com.conger.test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import org.xerial.snappy.SnappyInputStream;
import org.xerial.snappy.SnappyOutputStream;

public class DeflaterTest {

    public static void main(String[] args) throws Exception {

        bestSpeed();
        bestCompression();
        testSnappy();
        snappyPureJava();
        crossTest();
    }
    
    private static void bestSpeed() throws Exception {
        System.out.println("\nbest speed");
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
        System.out.println("read " + byteBuffer.toByteArray().length);
        originInput.close();
        System.out.println("time to read origin " + (System.currentTimeMillis() - start));

        // Compress the bytes
        byte[] output = new byte[byteBuffer.toByteArray().length * 2];
        start = System.currentTimeMillis();
        Deflater compresser = new Deflater(Deflater.BEST_SPEED);
        compresser.setInput(byteBuffer.toByteArray());
        compresser.finish();
        int compressedDataLength = compresser.deflate(output);
        compresser.end();
        System.out.println("time to compress " + (System.currentTimeMillis() - start) + " data length " + compressedDataLength + " ratio " + ((double)compressedDataLength/byteBuffer.toByteArray().length));

        // Decompress the bytes
        start = System.currentTimeMillis();
        Inflater decompresser = new Inflater();
        decompresser.setInput(output, 0, compressedDataLength);
        byte[] result = new byte[byteBuffer.toByteArray().length * 2];
        int resultLength = decompresser.inflate(result);
        decompresser.end();
        System.out.println("time to decompress " + (System.currentTimeMillis() - start));
    }
    private static void bestCompression() throws Exception {
        System.out.println("\ndefault compressed");
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
        System.out.println("read " + byteBuffer.toByteArray().length);
        originInput.close();
        System.out.println("time to read origin " + (System.currentTimeMillis() - start));

        // Compress the bytes
        byte[] output = new byte[byteBuffer.toByteArray().length * 2];
        start = System.currentTimeMillis();
        Deflater compresser = new Deflater(Deflater.DEFAULT_COMPRESSION);
        compresser.setInput(byteBuffer.toByteArray());
        compresser.finish();
        int compressedDataLength = compresser.deflate(output);
        compresser.end();
        System.out.println("time to compress " + (System.currentTimeMillis() - start) + " data length " + compressedDataLength + " ratio " + ((double)compressedDataLength/byteBuffer.toByteArray().length));

        // Decompress the bytes
        start = System.currentTimeMillis();
        Inflater decompresser = new Inflater();
        decompresser.setInput(output, 0, compressedDataLength);
        byte[] result = new byte[byteBuffer.toByteArray().length * 2];
        int resultLength = decompresser.inflate(result);
        decompresser.end();
        System.out.println("time to decompress " + (System.currentTimeMillis() - start));
    }
    
    private static void testSnappy() throws Exception {
        System.out.println("\nsnappy");
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
        System.out.println("read " + byteBuffer.toByteArray().length);
        originInput.close();
        System.out.println("time to read origin " + (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        ByteArrayOutputStream compress = new ByteArrayOutputStream();
        OutputStream snappyOut = new SnappyOutputStream(compress);
        snappyOut.write(byteBuffer.toByteArray(), 0, byteBuffer.toByteArray().length);
        System.out.println("time to compress " + (System.currentTimeMillis() - start) + " data length " + compress.toByteArray().length + " ratio " + ((double)compress.toByteArray().length/byteBuffer.toByteArray().length));
        
        
        start = System.currentTimeMillis();
        ByteArrayInputStream decompress = new ByteArrayInputStream(compress.toByteArray());
        InputStream snappyIn = new SnappyInputStream(decompress);
        count = 0;
        bytes = 0;
        byteBuffer = new ByteArrayOutputStream();
        while((bytes = snappyIn.read(buffer)) != -1) {
            count += bytes;
            byteBuffer.write(buffer, 0, bytes);
        }
        System.out.println("time to decompress " + (System.currentTimeMillis() - start));
        
        
    }
    
    private static void snappyPureJava() throws Exception {
        
        System.out.println("\nsnappy pure java");
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
        System.out.println("read " + byteBuffer.toByteArray().length);
        originInput.close();
        System.out.println("time to read origin " + (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        ByteArrayOutputStream compress = new ByteArrayOutputStream();
        OutputStream snappyOut = new org.iq80.snappy.SnappyOutputStream(compress);
        snappyOut.write(byteBuffer.toByteArray(), 0, byteBuffer.toByteArray().length);
        System.out.println("time to compress " + (System.currentTimeMillis() - start) + " data length " + compress.toByteArray().length + " ratio " + ((double)compress.toByteArray().length/byteBuffer.toByteArray().length));
        
        
        start = System.currentTimeMillis();
        ByteArrayInputStream decompress = new ByteArrayInputStream(compress.toByteArray());
        InputStream snappyIn = new org.iq80.snappy.SnappyInputStream(decompress);
        count = 0;
        bytes = 0;
        byteBuffer = new ByteArrayOutputStream();
        while((bytes = snappyIn.read(buffer)) != -1) {
            count += bytes;
            byteBuffer.write(buffer, 0, bytes);
        }
        System.out.println("time to decompress " + (System.currentTimeMillis() - start));
        
    }
    
    private static void crossTest() throws Exception {
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
        //System.out.println("read " + byteBuffer.toByteArray().length);
        originInput.close();
        
        ByteArrayOutputStream compress = new ByteArrayOutputStream();
        OutputStream snappyOut = new org.iq80.snappy.SnappyOutputStream(compress);
        snappyOut.write(byteBuffer.toByteArray(), 0, byteBuffer.toByteArray().length);
        //System.out.println("time to compress " + (System.currentTimeMillis() - start) + " data length " + compress.toByteArray().length + " ratio " + ((double)compress.toByteArray().length/byteBuffer.toByteArray().length));
        
        OutputStream out = new FileOutputStream(new File("/home/jj/heavyTableList.q"));
        ByteArrayInputStream decompress = new ByteArrayInputStream(compress.toByteArray());
        InputStream snappyIn = new SnappyInputStream(decompress);
        count = 0;
        bytes = 0;
        while((bytes = snappyIn.read(buffer)) != -1) {
            count += bytes;
            out.write(buffer, 0, bytes);
        }
        
        out.close();
    }
}
