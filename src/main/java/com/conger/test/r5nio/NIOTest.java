package com.conger.test.r5nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NIOTest {
    public static final void main(String[] args) throws Exception {
        new NIOServer().start();
        new NIOClient().start();
    }
    
    public static class NIOServer extends Thread {
        @Override
        public void run() {
            
            try {
            
                Selector selector = Selector.open();
                
                ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
                serverSocketChannel.bind(new InetSocketAddress(23456));
                serverSocketChannel.configureBlocking(false);
                serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
                
                while (true) {
                    int selectChannels = selector.select(1000);
                    if (selectChannels > 0) {
                        Set<SelectionKey> keys = selector.selectedKeys();
                        Iterator<SelectionKey> iter = keys.iterator();
                        while (iter.hasNext()) {
                            SelectionKey key =  iter.next();
                            if (key.isAcceptable()) {
                                ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
                                SocketChannel socketChannel = ssc.accept();
                                socketChannel.configureBlocking(false);
                                System.out.println("Connected from " + socketChannel.getRemoteAddress());
                                socketChannel.register(selector, SelectionKey.OP_READ, new ServerContext());
                                iter.remove();
                            }
                            if (key.isReadable()) {
                                SocketChannel socketChannel = (SocketChannel) key.channel();
                                ByteBuffer bb = ByteBuffer.allocate(1024);
                                while (socketChannel.read(bb) > 0) {
                                    bb.flip();
                                    byte[] buffer = new byte[1024];
                                    bb.get(buffer, 0, bb.limit());
                                    System.out.println(new String(buffer));
                                    bb.clear();
                                }
                                key.cancel();
                                socketChannel.close();
                                iter.remove();
                            }
                        }
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
    
    public static class ServerContext {
        
    }
    
    public static class NIOClient {
        public void start() throws Exception {
            
        }
    }
}
