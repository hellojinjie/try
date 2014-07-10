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
        new NIOClient().start();
        new NIOClient().start();
        new NIOClient().start();
        new NIOClient().start();
        new NIOClient().start();
        new NIOClient().start();
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
    
    public static class NIOClient extends Thread {
        
        @Override
        public void run() {
            try {
                Selector selector = Selector.open();
                for (int i = 0; i < 100; i++) {
                    newConnect(selector);
                }
                while (true) {
                    int socketChannels = selector.select(1000);
                    if (socketChannels > 0) {
                        Set<SelectionKey> selectedKeys = selector.selectedKeys();
                        Iterator<SelectionKey> iter = selectedKeys.iterator();
                        ByteBuffer bb = ByteBuffer.allocate(10000);
                        while (iter.hasNext()) {
                            SelectionKey key = iter.next();
                            if (key.isConnectable()) {
                                SocketChannel sc = (SocketChannel) key.channel();
                                sc.finishConnect();
                                bb.clear();
                                bb.put(new String("GET /ProxyBean HTTP/1.1\n").getBytes());
                                bb.put(new String("Host: 172.16.0.237\n").getBytes());
                                bb.put(new String("Connection: keep-alive\n").getBytes());
                                bb.put(new String("Cache-Control: max-age=0\n").getBytes());
                                bb.put(new String("Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8\n").getBytes());
                                bb.put(new String("User-Agent: Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Ubuntu Chromium/34.0.1847.116 Chrome/34.0.1847.116 Safari/537.36\n").getBytes());
                                bb.put(new String("Accept-Encoding: gzip,deflate,sdch\n").getBytes());
                                bb.put(new String("Accept-Language: en-US,en;q=0.8,zh-CN;q=0.6,zh;q=0.4,es;q=0.2,zh-TW;q=0.2\n\n").getBytes());
                                bb.flip();
                                try {
                                    sc.write(bb);
                                    key.interestOps(SelectionKey.OP_READ);
                                } catch (Exception e) {
                                    System.out.println("Error writing data " + e.getMessage());
                                    key.cancel();
                                    key.channel().close();
                                    newConnect(selector);
                                    iter.remove();
                                    continue;
                                }
                            }
                            if (key.isReadable()) {
                                SocketChannel sc = (SocketChannel) key.channel();
                                bb.clear();
                                try {
                                    sc.read(bb);
                                } catch (Exception e) {
                                    System.out.println("Error reading data " + e.getMessage());
                                    key.cancel();
                                    key.channel().close();
                                    newConnect(selector);
                                    iter.remove();
                                    continue;
                                }
                                bb.flip();
                                byte[] buffer = new byte[bb.limit()];
                                bb.get(buffer);
                                key.cancel();
                                key.channel().close();
                                newConnect(selector);
                            }
                            iter.remove();
                        }
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        
        private void newConnect(Selector selector) {
            try {
                SocketChannel socketChannel = SocketChannel.open();
                socketChannel.configureBlocking(false);
                socketChannel.register(selector, SelectionKey.OP_CONNECT);
                socketChannel.connect(new InetSocketAddress("172.16.0.237", 80));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
