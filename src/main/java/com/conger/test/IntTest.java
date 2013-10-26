package com.conger.test;

import java.util.Map;

import com.google.common.collect.Maps;

public class IntTest {

    public static void main(String[] args) {
        Map<String, Integer> ints = Maps.newHashMap();
        Integer i = ints.get("f");
        if (i == null) {
            System.out.println(" i is null");
        }
    
    }
}
