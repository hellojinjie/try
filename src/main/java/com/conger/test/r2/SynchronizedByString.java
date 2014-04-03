package com.conger.test.r2;

import java.io.File;

public class SynchronizedByString
{

    public static void main(String[] args) throws Exception
    {
        final File f = new File("/home/jj", ".vimrc");
        System.out.println(f.getAbsolutePath());

        final File f1 = new File("/home/jj", ".vimrc");
        System.out.println(f1.getAbsolutePath());

        System.out.println(f.getAbsolutePath().intern() == f1.getAbsolutePath().intern());
        System.out.println(f.getAbsolutePath() == f1.getAbsolutePath());
/*
        new Thread()
        {
            @Override
            public void run()
            {
                synchronized (f.getAbsolutePath())
                {
                    try
                    {
                        Thread.sleep(5000);
                    }
                    catch (InterruptedException e)
                    {
                    }
                    System.out.println("wakeup from f");
                }
            }
        }.start();

        Thread.sleep(1000);

        new Thread()
        {
            @Override
            public void run()
            {
                synchronized (f1.getAbsolutePath())
                {
                    System.out.println("wakeup from f1");
                }
            }
        }.start();
*/
        new Thread()
        {
            @Override
            public void run()
            {
                synchronized (f.getAbsolutePath().intern())
                {
                    try
                    {
                        Thread.sleep(5000);
                    }
                    catch (InterruptedException e)
                    {
                    }
                    System.out.println("intern wakeup from f");
                }
            }
        }.start();

        Thread.sleep(1000);

        new Thread()
        {
            @Override
            public void run()
            {
                synchronized (f1.getAbsolutePath().intern())
                {
                    System.out.println("intern wakeup from f1");
                }
            }
        }.start();
    }

}
