package com.panda.demo.common.leetcode.thread.mashibing;

/**
 * @author huixiangdou
 * @date 2020/4/29
 */
public class Volatile {
    enum Run {T1, T2;}
    private static volatile Run state = Run.T1;



    public static void main(String[] args) {
        char[] array1 = "12345".toCharArray();
        char[] array2 = "ABCDE".toCharArray();

        //为0时执行t1
        Runnable r1 = () -> {
            for (char a : array1) {
                //自旋
                while (state == Run.T2) {
                }
                System.out.print(a);
                state = Run.T2;
            }
        };

        //为1时执行t2
        Runnable r2 = () -> {
            for (char a : array2) {
                //自旋
                while (state == Run.T1) {
                }
                System.out.print(a);
                state = Run.T1;
            }
        };


        Thread t1 = new Thread(r1, "t1");
        Thread t2 = new Thread(r2, "t2");

        t1.start();
        t2.start();
    }
}
