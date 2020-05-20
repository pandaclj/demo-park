package com.panda.demo.common.leetcode.thread.mashibing;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * 利用管道解决
 * 性能比较差
 * 利用 wait/notify
 *
 * @author huixiangdou
 * @date 2020/4/30
 */
public class PipedOutputStreamTest {
    private static PipedOutputStream os1 = new PipedOutputStream();
    private static PipedInputStream is1 = new PipedInputStream(1);
    private static PipedOutputStream os2 = new PipedOutputStream();
    private static PipedInputStream is2 = new PipedInputStream(1);

    static {
        //连接
        try {
            os1.connect(is1);
            os2.connect(is2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        char[] array1 = "12345".toCharArray();
        char[] array2 = "ABCDE".toCharArray();

        Runnable r1 = () -> {
            try {
                for (char a : array1) {
                    //从管道1读到数据才允许打印
                    is1.read();
                    System.out.print(a);
                    os2.write(1);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        };

        Runnable r2 = () -> {
            for (char a : array2) {
                try {
                    //先往管道1中写数据，让线程1先执行
                    os1.write(1);
                    //从管道2中读取到数据才允许打印
                    is2.read();
                    System.out.print(a);
                } catch (Exception e) {
                }
            }
        };

        Thread t1 = new Thread(r1, "t1");
        Thread t2 = new Thread(r2, "t2");

        t1.start();
        t2.start();

    }
}
