package com.conger.test.r4;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

import com.google.common.base.Stopwatch;

public class AzureTestClient {

    private long totalCount = 0;
    private long count = 0;
    private long lastOutput = 0;
    
    public static final void main(String[] args) throws Exception {
       new AzureTestClient().run(args[0]);
    }
    
    private void run(String ip) throws Exception {
        Stopwatch stopwatch = new Stopwatch();
        stopwatch.start();
        while (true) {
            Socket s = new Socket();
            s.connect(new InetSocketAddress(ip, 12121));
            s.getOutputStream().write(1);
            s.close();
            count++;
            totalCount++;
            long elapsed = stopwatch.elapsed(TimeUnit.SECONDS);
            if (elapsed >= (lastOutput + 5)) {
                lastOutput = elapsed;
                System.out.println("last 5 seconds, finish " + count + " TCP connections. " + elapsed + " elapsed, total connections " + totalCount);
                count = 0;
            }
        }
    }
}
