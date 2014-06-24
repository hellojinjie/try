package com.conger.test.r4;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

import com.google.common.base.Stopwatch;

public class AzureTestServer {

    private long totalCount = 0;
    private long count = 0;
    private long lastOutput = 0;
    
    public static final void main(String[] args) throws Exception {
       new AzureTestServer().run();
    }
    
    private void run() throws Exception {
        Stopwatch stopwatch = new Stopwatch();
        stopwatch.start();
        @SuppressWarnings("resource")
        ServerSocket ss = new ServerSocket();
        ss.bind(new InetSocketAddress(12121));
        while (true) {
            Socket s = ss.accept();
            s.getInputStream().read();
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
