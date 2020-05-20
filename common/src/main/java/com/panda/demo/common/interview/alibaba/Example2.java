package com.panda.demo.common.interview.alibaba;

import java.util.Arrays;

/**
 * 题目2：
 * 输入描述：输入n个字符串，保证每个字符串中的字符的 ASCII 码是非递减的。
 * 输出描述： 拼接出一个尽可能长的字符串，保证ASCII 码是非递减的。
 *
 * 示例1：输入
 * aaa
 * bcd
 * zzz
 * bcdef
 *
 * 输出
 * aaabcdefzzz
 *
 *
 * 输入abc、bbb，输出空字符串
 */
public class Example2 {
    private static final String EMPTY = "";

    public static String append(String... array) {
        if (array == null || array.length == 0) {
            return EMPTY;
        }

        if (array.length == 1) {
            return array[0];
        }

        Arrays.sort(array);

        StringBuilder sb = new StringBuilder();
        //第一个子串直接放入结果集
        String firstString = array[0];
        sb.append(firstString);
        int len = firstString.length();
        //最后一个字符
        char lastChar = firstString.charAt(len - 1);
        int lastCharRepeatNum = 1;
        int i;
        //找最后一个字符的重复个数
        for (i = len - 2; i >= 0; i--) {
            if (lastChar == firstString.charAt(i)) {
                lastCharRepeatNum++;
            }
        }

        for (int j = 1; j < array.length; j++) {
            String ele = array[j];
            if (ele == null || ele.length() == 0) {
                continue;
            }
            //看首尾能否拼接
            len = ele.length();
            for (i = 0; i < len; i++) {
                if (ele.charAt(i) > lastChar) {
                    break;
                } else if (ele.charAt(i) == lastChar) {
                    if (lastCharRepeatNum > 0) {
                        lastCharRepeatNum--;
                    } else {
                        break;
                    }
                }
            }

            if (i <= len - 1) {
                sb.append(ele.substring(i));
            }

            if (sb.indexOf(ele) < 0) {
                return EMPTY;
            }

            //获取新字符串的末尾字符
            lastChar = ele.charAt(len - 1);
            lastCharRepeatNum = 1;
            for (i = len - 2; i >= 0; i--) {
                if (lastChar == ele.charAt(i)) {
                    lastCharRepeatNum++;
                }
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(Example2.append(new String[]{"abc", "bbb"}));
        System.out.println(Example2.append(new String[]{"aaa", "abc", "abcd", "bcdef", "zzz"}));
        System.out.println(Example2.append(new String[]{"abbc", "bcd"}));
        System.out.println(Example2.append(new String[]{"abbc", "abc"}));
        System.out.println(Example2.append(new String[]{"abbbc", "bbb"}));
        System.out.println(Example2.append(new String[]{"acef", "bef"}));
        System.out.println(Example2.append(new String[]{"abc", "aabc"}));
    }
}
