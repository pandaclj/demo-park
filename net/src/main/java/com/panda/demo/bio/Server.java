package com.panda.demo.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author huixiangdou
 * @date 2019-03-20
 */
public class Server {
    private static ExecutorService pool = Executors.newFixedThreadPool(8);

    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(8888);
            while (true) {
                Socket socket = ss.accept();
                System.out.println("接收到客户端连接");

                pool.execute(() -> {
                    try (BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                        while (true) {
                            System.out.println(br.readLine());
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
