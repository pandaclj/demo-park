package com.panda.demo.leetcode.title_151;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author huixiangdou
 * @date 2020/5/19
 */
public class Answer_151_1 {
    static class Solution {
        public String reverseWords(String s) {
            List<String> list = Arrays.asList(s.trim().split("\\s+"));
            Collections.reverse(list);
            return String.join(" ", list);
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().reverseWords("  hello world! "));
    }
}
