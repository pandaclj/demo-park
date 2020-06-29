package com.panda.demo.leetcode.title_01116_打印零与奇偶数;

import java.util.concurrent.locks.LockSupport;
import java.util.function.IntConsumer;

/**
 * Lock/Condition
 * 1把锁1个条件
 *
 * @author huixiangdou
 * @date 2020/5/14
 */
public class Answer_1116_5 {
    static class ZeroEvenOdd {
        private int n;
        private Thread t1;
        private Thread t2;
        private Thread t3;


        enum Run {A, B, C}

        private volatile Run run = Run.A;


        public ZeroEvenOdd(int n) {
            this.n = n;
        }

        // printNumber.accept(x) outputs "x", where x is an integer.
        public void zero(IntConsumer printNumber) throws InterruptedException {
            if (t1 == null) {
                t1 = Thread.currentThread();
            }

            for (int i = 0; i < n; i++) {
                while (run != Run.A) {
                    LockSupport.park();
                }

                printNumber.accept(0);

                if (i % 2 == 0) {
                    run = Run.C;
                    if (t3 != null) {
                        LockSupport.unpark(t3);
                    }
                } else {
                    run = Run.B;
                    if (t2 != null) {
                        LockSupport.unpark(t2);
                    }
                }
            }

        }

        //偶数
        public void even(IntConsumer printNumber) throws InterruptedException {
            if (t2 == null) {
                t2 = Thread.currentThread();
            }

            for (int i = 2; i <= n; i = i + 2) {
                while (run != Run.B) {
                    LockSupport.park();
                }
                printNumber.accept(i);
                run = Run.A;
                if (t1 != null) {
                    LockSupport.unpark(t1);
                }
            }

        }

        //奇数
        public void odd(IntConsumer printNumber) throws InterruptedException {
            if (t3 == null) {
                t3 = Thread.currentThread();
            }
            for (int i = 1; i <= n; i = i + 2) {
                while (run != Run.C) {
                    LockSupport.park();
                }
                printNumber.accept(i);
                run = Run.A;
                LockSupport.unpark(t1);
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
