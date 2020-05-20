package com.panda.demo.common.leetcode.thread.mashibing;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author huixiangdou
 * @date 2020/4/29
 */
public class ArrayBlockingTest {
    private static ArrayBlockingQueue<Integer> q1 = new ArrayBlockingQueue(1);
    private static ArrayBlockingQueue<Integer> q2 = new ArrayBlockingQueue(1);

    public static void main(String[] args) {
        char[] array1 = "12345".toCharArray();
        char[] array2 = "ABCDE".toCharArray();

        Runnable r1 = () -> {
            try {
                for (char a : array1) {
                    q1.take();
                    System.out.print(a);
                    q2.put(1);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Runnable r2 = () -> {
            for (char a : array2) {
                try {
                    q1.put(1);
                    q2.take();
                    System.out.print(a);
                } catch (InterruptedException e) {
                }
            }
        };

        Thread t1 = new Thread(r1, "t1");
        Thread t2 = new Thread(r2, "t2");

        t1.start();
        t2.start();

    }
}
