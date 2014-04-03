package com.conger.test.r2;

public class WaitTest
{
    
    private final int l = 0;
    
    public static void main(String[] args) throws Exception
    {
        //new WaitTest().doTest();
        WaitTest w = new Hia();
        System.out.println(w.l);
        System.out.println(w.getL());
    }
 
    public void doTest() throws Exception
    {
        this.wait();
        
        this.notify();
        
    }
    
    void dodo() {
        
    }

    public int getL()
    {
        return l;
    }
}


class Hia extends WaitTest {
    private int l = 1;

    public int getL()
    {
        return l;
    }

    public void setL(int l)
    {
        this.l = l;
    }
}