package com.panda.demo.common.algorithm.other;

/**
 * @author huixiangdou
 * @date 2019-03-19
 */
public class Math {
    /**
     * 求最大公约数
     *
     * @param a
     * @param b
     * @return
     */
    public static int gcd(int a, int b) {
        int max = a > b ? a : b;
        int min = a < b ? a : b;

        int remainder = max % min;
        if (remainder == 0) {
            return min;
        } else {
            return gcd(min, remainder);
        }
    }

    /**
     * 求最小公倍数
     *
     * @param a
     * @param b
     * @return
     */
    public static int lcm(int a, int b) {
        return a * b / gcd(a, b);
    }

    public static void main(String[] args) {
        System.out.println(Math.gcd(4, 6));
        System.out.println(Math.lcm(4, 6));
    }
}
