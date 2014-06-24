package com.conger.test.r4;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class NginxKeepAliveTest {

    public static final void main(String[] args) throws Exception {
        for (int i = 0; i < 100; i++) {
            c1();
        }
    }
    
    private static void c1() throws Exception {
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress(80));
        InputStream is = socket.getInputStream();
        OutputStream os = socket.getOutputStream();
        os.write(new String("GET /receiver/ProxyBean HTTP/1.1\n").getBytes());
        os.write(new String("Host: keepalive.test\n").getBytes());
        os.write(new String("Connection: keep-alive\n").getBytes());
        os.write(new String("Cache-Control: max-age=0\n").getBytes());
        os.write(new String("Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8\n").getBytes());
        os.write(new String("User-Agent: Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Ubuntu Chromium/34.0.1847.116 Chrome/34.0.1847.116 Safari/537.36\n").getBytes());
        os.write(new String("Accept-Encoding: gzip,deflate,sdch\n").getBytes());
        os.write(new String("Accept-Language: en-US,en;q=0.8,zh-CN;q=0.6,zh;q=0.4,es;q=0.2,zh-TW;q=0.2\n\n").getBytes());
        byte[] buffer = new byte[1024];
        is.read(buffer);
        System.out.println(new String(buffer));
        socket.close();
    }
}
