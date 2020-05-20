package com.panda.demo.leetcode.title_1116;

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

/**
 * Semaphore
 * 等于是三把锁，三个条件
 *
 * @author huixiangdou
 * @date 2020/5/14
 */
public class Answer_1116_2 {
    static class ZeroEvenOdd {
        private int n;

        private Semaphore A = new Semaphore(1);//0
        private Semaphore B = new Semaphore(0);//偶数
        private Semaphore C = new Semaphore(0);//奇数

        public ZeroEvenOdd(int n) {
            this.n = n;
        }

        // printNumber.accept(x) outputs "x", where x is an integer.
        public void zero(IntConsumer printNumber) throws InterruptedException {
            for (int i = 0; i < n; i++) {
                A.acquire();
                printNumber.accept(0);
                if (i % 2 == 0) { //唤醒奇数
                    C.release();
                } else {//唤醒偶数
                    B.release();
                }
            }
        }

        //偶数
        public void even(IntConsumer printNumber) throws InterruptedException {
            for (int i = 2; i <= n; i = i + 2) {
                B.acquire();
                printNumber.accept(i);
                A.release();
            }
        }

        //奇数
        public void odd(IntConsumer printNumber) throws InterruptedException {
            for (int i = 1; i <= n; i = i + 2) {
                C.acquire();
                printNumber.accept(i);
                A.release();
            }
        }
    }

    public static void main(String[] args) {
        ZeroEvenOdd test = new ZeroEvenOdd(5);

        IntConsumer consumer = (x) -> {
            System.out.println(Thread.currentThread().getName() + ":" + x);
        };

        Thread t1 = new Thread(() -> {
            try {
                test.zero(consumer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "A");

        Thread t2 = new Thread(() -> {
            try {
                test.even(consumer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "B");

        Thread t3 = new Thread(() -> {
            try {
                test.odd(consumer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "C");
        t1.start();
        t3.start();
        t2.start();
    }
}
