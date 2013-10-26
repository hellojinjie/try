package com.conger.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class SerializeTest {

    public static void main(String[] args) throws Exception {
        File file = new File("out.ser");
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        SerializeMe serializeMe = new SerializeMe();
        serializeMe.a = 10;
        serializeMe.c = 20;
        oos.writeObject(serializeMe);
        oos.close();

        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);

        SerializeMe dto = (SerializeMe) ois.readObject();
        System.out.println("data a: " + dto.a);
//        System.out.println("data b: " + dto.b);
        System.out.println("data c: " + dto.c);
        ois.close();
    }
}

class SerializeMe implements Serializable {

    private static final long serialVersionUID = 1L;
    
    public int c;
//    public int b;
    public int a;
    
}