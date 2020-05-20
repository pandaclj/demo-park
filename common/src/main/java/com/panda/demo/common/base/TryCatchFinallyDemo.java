package com.panda.demo.common.base;

/**
 * @author huixiangdou
 * @date 2019/5/21
 */
public class TryCatchFinallyDemo {
    public static int doIt(int input) {
        try {
            if (input < 0) {
                throw new IllegalArgumentException("参数错误");
            } else {
                return input;
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            throw e;
        } finally {
            return -1;
        }
    }

    public static void main(String[] args) {
        System.out.println(doIt(1));
    }
}
