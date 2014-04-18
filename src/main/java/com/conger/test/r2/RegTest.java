package com.conger.test.r2;

public class RegTest
{

    public static void main(String[] args)
    {
        String description = "FL";
        int i = description.replaceAll("\\s+", "").toLowerCase().hashCode();
        System.out.println(i);
        
        description = "F L";
        i = description.replaceAll("\\s+", "").toLowerCase().hashCode();
        System.out.println(i);
        
    }
}
