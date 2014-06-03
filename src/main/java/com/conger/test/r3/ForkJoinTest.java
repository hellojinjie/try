package com.conger.test.r3;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class ForkJoinTest
{

    public static void main(String[] args)
    {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Long> forkJoinTask = new RecursiveTask<Long>()
        {
            private static final long serialVersionUID = 1L;

            @Override
            protected Long compute()
            {
                ForkJoinTask<Long> forkJoinTask = new RecursiveTask<Long>() {
                    private static final long serialVersionUID = 1L;

                    @Override
                    protected Long compute() {
                        return 1000l;
                    }
                };
                forkJoinTask.fork();
                return 100l + forkJoinTask.join();
            }
        };
        System.out.println(forkJoinPool.invoke(forkJoinTask));
    }
}
