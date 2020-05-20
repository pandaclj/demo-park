package com.panda.demo.common.leetcode.thread.mashibing;

/**
 * @author huixiangdou
 * @date 2020/4/29
 */
public class Template {
    public static void main(String[] args) {
        char[] array1 = "12345".toCharArray();
        char[] array2 = "ABCDE".toCharArray();

        Runnable r1 = () -> {
            for (char a : array1) {
                System.out.print(a);
            }
        };

        Runnable r2 = () -> {
            for (char a : array2) {
                System.out.print(a);
            }
        };


        Thread t1 = new Thread(r1, "t1");
        Thread t2 = new Thread(r2, "t2");

        t1.start();
        t2.start();

    }
}
