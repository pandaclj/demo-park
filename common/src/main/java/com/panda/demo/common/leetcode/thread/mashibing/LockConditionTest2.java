package com.panda.demo.common.leetcode.thread.mashibing;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author huixiangdou
 * @date 2020/4/30
 */
public class LockConditionTest2 {

    public static void main(String[] args) {
        char[] array1 = "12345".toCharArray();
        char[] array2 = "ABCDE".toCharArray();
        ReentrantLock lock = new ReentrantLock();
        Condition condition1 = lock.newCondition();
        Condition condition2 = lock.newCondition();


        Runnable r1 = () -> {
            try {
                lock.lock();
                for (char a : array1) {
                    System.out.print(a);
                    condition2.signal();
                    condition1.await();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        };

        Runnable r2 = () -> {
            try {
                lock.lock();
                for (char a : array2) {
                    condition2.await();
                    System.out.print(a);
                    condition1.signal();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        };


        Thread t1 = new Thread(r1, "t1");
        Thread t2 = new Thread(r2, "t2");

        t2.start();
        t1.start();

    }

}
