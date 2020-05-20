package com.panda.demo.leetcode.title_136;

/**
 * 用异或的特性
 * <p>
 * a ^ a = 0
 * a ^ 0 = a
 *
 * @author huixiangdou
 * @date 2020/5/18
 */
public class Answer_136_1 {

    static class Solution {
        public int singleNumber(int[] nums) {
            int result = 0;
            for (int num : nums) {
                result ^= num;
            }
            return result;
        }
    }

    public static void main(String[] args) {

        System.out.println(new Solution().singleNumber(new int[]{2, 2, 1}));
    }
}
