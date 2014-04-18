package com.conger.test.r3;

public class CloneTest implements Cloneable
{
    private String status;

    private Test ttt;
    private TestTest sss;
    
    public static void main(String[] args) throws CloneNotSupportedException
    {
        CloneTest ct = new CloneTest();
        ct.ttt = new Test();
        ct.ttt.hello = 4;
        ct.status = "1";
        ct.sss = new TestTest();
        ct.sss.hello = 3;
        System.out.println(ct);
        CloneTest t = (CloneTest) ct.clone();
        System.out.println(t);
    }
    
    public String toString()
    {
        return status + " " + ttt.hello + " " + sss.hello + " " + ttt + " " + sss;
    }
    
    @Override
    public Object clone() throws CloneNotSupportedException
    {
        CloneTest t = (CloneTest) super.clone();
        t.sss = t.sss.clone();
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
    public TestTest clone() throws CloneNotSupportedException
    {
        return (TestTest) super.clone();
    }
    
}