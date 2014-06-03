package com.conger.test.r3;

import java.io.BufferedReader;
import java.io.FileReader;

public class TryResource
{

    public static void main(String[] args) throws Exception {
        int i = 1_333_43;
        System.out.println(i);
        try(BufferedReader br = new BufferedReader(new FileReader("/etc/passwd"))) {
            String line = br.readLine();
            System.out.println(line);
        }
    }
}
