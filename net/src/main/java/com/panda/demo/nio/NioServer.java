package com.panda.demo.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * @author huixiangdou
 * @date 2019-03-20
 */
public class NioServer {
    public static void main(String[] args) {
        try {
            ServerSocketChannel ssc = ServerSocketChannel.open();
            ssc.configureBlocking(false);
            ssc.socket().bind(new InetSocketAddress(8887));

            Selector selector = Selector.open();
            ssc.register(selector, SelectionKey.OP_ACCEPT);

            while (true) {
                int nums = selector.select(); //会阻塞
                if (nums > 0) {
                    Set<SelectionKey> keys = selector.selectedKeys();

                    Iterator<SelectionKey> it = keys.iterator();
                    while (it.hasNext()) {
                        SelectionKey selectionKey = it.next();
                        it.remove();
                        if (selectionKey.isValid()) {
                            if (selectionKey.isAcceptable()) {
                                System.out.println("accept");
                                SocketChannel socketChannel = ssc.accept();
                                socketChannel.configureBlocking(false);
                                socketChannel.register(selector, SelectionKey.OP_READ);
                            } else if (selectionKey.isReadable()) {
                                SocketChannel socketChannel = (SocketChannel)selectionKey.channel();
                                ByteBuffer buffer = ByteBuffer.allocate(10);
                                try {
                                    socketChannel.read(buffer);
                                    System.out.println(new String(buffer.array(),"UTF-8"));
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
