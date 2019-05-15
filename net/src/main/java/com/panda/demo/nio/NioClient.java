package com.panda.demo.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

/**
 * @author huixiangdou
 * @date 2019-03-20
 */
public class NioClient {
    public static void main(String[] args) {
        try {
            SocketChannel socketChannel = SocketChannel.open();
            socketChannel.socket().connect(new InetSocketAddress("127.0.0.1",8887));
            socketChannel.configureBlocking(false);
            Scanner sc = new Scanner(System.in);
            ByteBuffer buffer = ByteBuffer.allocate(10);
            while(sc.hasNext()){
                String line = sc.nextLine();
                System.out.println(line);
                buffer.clear();
                buffer.put(line.getBytes());
                buffer.flip();
                while(buffer.hasRemaining()){
                    socketChannel.write(buffer);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
