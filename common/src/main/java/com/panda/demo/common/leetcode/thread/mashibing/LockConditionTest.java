package com.panda.demo.common.leetcode.thread.mashibing;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author huixiangdou
 * @date 2020/4/30
 */
public class LockConditionTest {


    private static volatile boolean t2_start = false;

    public static void main(String[] args) {
        char[] array1 = "12345".toCharArray();
        char[] array2 = "ABCDE".toCharArray();
        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();


        Runnable r1 = () -> {
            try {
                lock.lock();
                t2_start = true;
                for (char a : array1) {
                    System.out.print(a);
                    condition.signal();
                    condition.await();
                }

                condition.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        };

        Runnable r2 = () -> {
            try {
                lock.lock();
                while (!t2_start) {
                    condition.await();
                }

                for (char a : array2) {
                    System.out.print(a);
                    condition.signal();
                    condition.await();
                }
                condition.signal();
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
