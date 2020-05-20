package com.panda.demo.common.leetcode.thread.mashibing;

/**
 * ABCDE
 * 12345
 * <p>
 * 利用wait、notify交叉打印
 *
 * @author huixiangdou
 * @date 2020/4/27
 */
public class WaitNotifyPrint {
    private static volatile boolean t2_start = false;

    public static void main(String[] args) {
        char[] array1 = "12345".toCharArray();
        char[] array2 = "ABCDE".toCharArray();

        Object lock = new Object();

        Runnable r1 = () -> {
            synchronized (lock) {
                t2_start = true;
                try {
                    for (char a : array1) {
                        System.out.print(a);
                        lock.notify();
                        lock.wait();
                    }
                    //线程2还会wait
                    lock.notify();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Runnable r2 = () -> {
            synchronized (lock) {
                try {
                    while (!t2_start) {
                        lock.wait();
                    }
                    for (char a : array2) {
                        System.out.print(a);
                        lock.notify();
                        lock.wait();
                    }
                    //可能线程1会wait，但如果控制了第一次的执行顺序，就可以看情况判断
                    lock.notify();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        Thread t1 = new Thread(r1, "t1");
        Thread t2 = new Thread(r2, "t2");
        t2.start();
        t1.start();
    }

}
