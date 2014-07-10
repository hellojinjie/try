package com.conger.test.r5nio;

import java.net.InetSocketAddress;
import java.net.Socket;

public class As3Test
{

    private static final String header = "GET /crossdomain.xml HTTP/1.1\r\n" +
            "Host: nlls02.nlv2.com\r\n" +
            "Connection: keep-alive\r\n" +
            "Cache-Control: no-cache\r\n" +
            "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8\r\n" +
            "Pragma: no-cache\r\n" +
            "User-Agent: Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Ubuntu Chromium/34.18.116 Chrome/34.0.1847.116 Safari/537.36\r\n" +
            "Accept-Encoding: gzip,deflate,sdch\r\n" +
            "Accept-Language: en-US,en;q=0.8,zh-CN;q=0.6,zh;q=0.4,es;q=0.2,zh-TW;q=0.2\r\n\r\n";
    
    public static void main(String[] args) throws Exception
    {
        Socket s = new Socket();
        s.connect(new InetSocketAddress("nlls02.nlv2.com", 80));
        s.getOutputStream().write(header.getBytes());
        s.getOutputStream().flush();
        byte[] buffer = new byte[10000];
        s.getInputStream().read(buffer);
        System.out.println(new String(buffer));
    }
}
