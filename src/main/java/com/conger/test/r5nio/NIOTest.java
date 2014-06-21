package com.conger.test.r5nio;

import java.net.ServerSocket;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;

public class NIOTest {
    public static final void main(String[] args) throws Exception {
        new NIOServer().start();
        new NIOClient().start();
    }
    
    public static class NIOServer {
        public void start() throws Exception {
            
            Selector selector = Selector.open();
            
            @SuppressWarnings("resource")
            ServerSocket ss = new ServerSocket(2345);
            SelectableChannel sc = ss.getChannel().configureBlocking(false);
            sc.register(selector, SelectionKey.OP_ACCEPT);
        }
    }
    
    public static class NIOClient {
        public void start() throws Exception {
            
        }
    }
}
