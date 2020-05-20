package com.panda.demo.common.leetcode.thread.mashibing;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author huixiangdou
 * @date 2020/4/29
 */
public class AtomicBooleanTest {
    private static AtomicBoolean lock = new AtomicBoolean(false);

    public static void main(String[] args) {
        char[] array1 = "12345".toCharArray();
        char[] array2 = "ABCDE".toCharArray();

        Runnable r1 = () -> {
            for (char a : array1) {
                while(lock.get() == true){

                }
                System.out.print(a);
                lock.set(true);
            }
        };

        Runnable r2 = () -> {
            for (char a : array2) {
                while(lock.get() == false){}
                System.out.print(a);
                lock.set(false);
            }
        };


        Thread t1 = new Thread(r1, "t1");
        Thread t2 = new Thread(r2, "t2");

        t1.start();
        t2.start();
    }
}
