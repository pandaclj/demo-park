package com.panda.demo.bio;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author huixiangdou
 * @date 2019-03-20
 */
public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress("127.0.0.1", 8888));
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);


            Scanner sc = new Scanner(System.in);
            while (sc.hasNext()) {
                String line = sc.nextLine();
                System.out.println(line);
                pw.println(line);
                pw.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
