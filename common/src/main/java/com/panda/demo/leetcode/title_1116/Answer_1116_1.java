package com.panda.demo.leetcode.title_1116;

import java.util.ArrayList;
import java.util.List;
import java.util.function.IntConsumer;

/**
 * Wait/Notify
 * 1把锁，1个条件
 *
 * @author huixiangdou
 * @date 2020/5/14
 */
public class Answer_1116_1 {
    static class ZeroEvenOdd {
        private int n;
        private List<Integer> list = new ArrayList<>(n);
        private volatile int index = 0;
        private Object lock = new Object();

        public ZeroEvenOdd(int n) {
            this.n = n;
            for (int i = 1; i <= n; i++) {
                list.add(0);
                list.add(i);
            }
        }

        public int judge(int x) {
            if (x == 0) {
                return 0;
            }
            if (x % 2 == 0) {
                return 2;
            } else {
                return 1;
            }

        }

        // printNumber.accept(x) outputs "x", where x is an integer.
        public void zero(IntConsumer printNumber) throws InterruptedException {
            synchronized (lock) {
                while (index < 2 * n) {
                    int v = list.get(index);
                    if (judge(v) != 0) {
                        lock.wait();
                    } else {
                        printNumber.accept(v);
                        index++;
                        lock.notifyAll();
                    }
                }
            }
        }

        public void even(IntConsumer printNumber) throws InterruptedException {
            synchronized (lock) {
                while (index < 2 * n) {
                    int v = list.get(index);
                    if (judge(v) != 2) {
                        lock.wait();
                    } else {
                        printNumber.accept(v);
                        index++;
                        lock.notifyAll();
                    }
                }
            }
        }

        public void odd(IntConsumer printNumber) throws InterruptedException {
            synchronized (lock) {
                while (index < 2 * n) {
                    int v = list.get(index);
                    if (judge(v) != 1) {
                        lock.wait();
                    } else {
                        printNumber.accept(v);
                        index++;
                        lock.notifyAll();
                    }
                }
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
