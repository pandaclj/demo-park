package com.panda.demo.leetcode.title_1116;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.IntConsumer;

/**
 * Lock/Condition
 * 1把锁3个条件
 * <p>
 * 可能1先抢到锁，然后1等待，B抢到锁，B等待，C抢到锁，C等待
 * <p>
 * 多个条件争抢少点
 *
 * @author huixiangdou
 * @date 2020/5/14
 */
public class Answer_1116_3 {
    static class ZeroEvenOdd {
        private int n;
        private ReentrantLock lock = new ReentrantLock();
        private Condition A = lock.newCondition();
        private Condition B = lock.newCondition();
        private Condition C = lock.newCondition();

        enum Run {A, B, C}

        private volatile Run run = Run.A;


        public ZeroEvenOdd(int n) {
            this.n = n;
        }

        // printNumber.accept(x) outputs "x", where x is an integer.
        public void zero(IntConsumer printNumber) throws InterruptedException {
            try {
                lock.lock();
                for (int i = 0; i < n; i++) {
                    while (run != Run.A) {
                        A.await();

                    }
                    printNumber.accept(0);
                    if (i % 2 == 0) { //奇数
                        run = Run.C;
                        C.signal();
                    } else {
                        run = Run.B;
                        B.signal();
                    }
                }
            } finally {
                lock.unlock();
            }
        }

        //偶数
        public void even(IntConsumer printNumber) throws InterruptedException {
            try {
                lock.lock();
                for (int i = 2; i <= n; i = i + 2) {
                    while (run != Run.B) {
                        B.await();
                    }
                    printNumber.accept(i);
                    run = Run.A;
                    A.signal();
                }
            } finally {
                lock.unlock();
            }
        }

        //奇数
        public void odd(IntConsumer printNumber) throws InterruptedException {
            try {
                lock.lock();
                for (int i = 1; i <= n; i = i + 2) {
                    while (run != Run.C) {
                        C.await();
                    }
                    printNumber.accept(i);
                    run = Run.A;
                    A.signal();
                }
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        ZeroEvenOdd test = new ZeroEvenOdd(3);

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
