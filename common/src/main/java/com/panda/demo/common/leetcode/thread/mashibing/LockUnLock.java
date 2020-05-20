package com.panda.demo.common.leetcode.thread.mashibing;

import java.util.concurrent.locks.LockSupport;

/**
 * @author huixiangdou
 * @date 2020/4/28
 */
public class LockUnLock {
    private static Thread t1;
    private static Thread t2;

    public static void main(String[] args) {
        char[] array1 = "12345".toCharArray();
        char[] array2 = "ABCDE".toCharArray();

        Runnable r1 = () -> {
            for (char a : array1) {
                System.out.print(a);
                LockSupport.unpark(t2);
                LockSupport.park();
            }
        };

        Runnable r2 = () -> {
            for (char a : array2) {
                LockSupport.park();
                System.out.print(a);
                LockSupport.unpark(t1);
            }
        };

        t1 = new Thread(r1, "t1");
        t2 = new Thread(r2, "t2");
        t1.start();
        t2.start();
    }
}
