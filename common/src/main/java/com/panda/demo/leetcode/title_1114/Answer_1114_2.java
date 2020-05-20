package com.panda.demo.leetcode.title_1114;

/**
 * 输入 1,2,3 或者 1,3,2 只是表明线程的启动顺序
 *
 * @author huixiangdou
 * @date 2020/5/13
 */
public class Answer_1114_2 {

    static class Foo {
        enum Run {
            r1, r2, r3;
        }

        private volatile Enum runState = Run.r1;


        public void first(Runnable printFirst) throws InterruptedException {
            // printFirst.run() outputs "first". Do not change or remove this line.
            while (runState != Run.r1) {
            }
            printFirst.run();
            runState = Run.r2;
        }

        public void second(Runnable printSecond) throws InterruptedException {
            // printSecond.run() outputs "second". Do not change or remove this line.
            while (runState != Run.r2) {

            }
            printSecond.run();
            runState = Run.r3;
        }

        public void third(Runnable printThird) throws InterruptedException {
            // printThird.run() outputs "third". Do not change or remove this line.
            while (runState != Run.r3) {
            }
            printThird.run();
        }


        public Foo() {

        }

        public void one() {
            Runnable r = () -> {
                System.out.println("one");
            };
            try {
                first(r);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public void two() {
            Runnable r = () -> {
                System.out.println("tow");
            };
            try {
                second(r);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public void three() {
            Runnable r = () -> {
                System.out.println("three");
            };
            try {
                third(r);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Foo foo = new Foo();
        Runnable r1 = () -> {
            foo.one();
        };

        Runnable r2 = () -> {
            foo.two();
        };

        Runnable r3 = () -> {
            foo.three();
        };
        Thread t1 = new Thread(r1);
        Thread t3 = new Thread(r3);
        Thread t2 = new Thread(r2);

        t1.start();
        t2.start();
        t3.start();
    }

}
