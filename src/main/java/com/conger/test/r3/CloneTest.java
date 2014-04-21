package com.conger.test.r3;

import java.util.ArrayList;

public class CloneTest implements Cloneable
{
    private String status;

    private Test ttt;
    private TestTest sss;
    
    private TestTest[] sa;
    private Test[] ta;
    
    private static final TestTest[] PRIVATE_VALUES =
    { new TestTest() };

    public static final TestTest[] values()
    {
        return PRIVATE_VALUES.clone();
    }
    
    public static void main(String[] args) throws CloneNotSupportedException
    {
        CloneTest ct = new CloneTest();
        ct.ttt = new Test();
        ct.ttt.hello = 4;
        ct.status = "1";
        ct.sss = new TestTest();
        ct.sss.hello = 3;
        ct.ta = new Test[]{new Test()};
        ct.sa = new TestTest[]{new TestTest()};
        
        System.out.println(ct);
        CloneTest t = (CloneTest) ct.clone();
        System.out.println(t);
        
        System.out.println(ct.ta.clone()[0]);
        System.out.println(t.ta.clone()[0]);
        
        System.out.println(ct.sa[0]);
        System.out.println(t.sa[0]);
        
        System.out.println(CloneTest.values()[0]);
        CloneTest.values()[0] = new TestTest();
        System.out.println(CloneTest.values()[0]);
        
    }
    
    public String toString()
    {
        return status + " " + ttt.hello + " " + sss.hello + " " + ttt + " " + sss;
    }
    
    @Override
    public Object clone() throws CloneNotSupportedException
    {
        CloneTest t = (CloneTest) super.clone();
        t.sss = (TestTest) t.sss.clone();
        return t;
    }
}

class Test
{
    public int hello = 0;
}

class TestTest implements Cloneable
{
    public int hello = 0;
    
    @Override 
    public Object clone() throws CloneNotSupportedException
    {
        return super.clone();
    }
    
}

