package com.panda.demo.common.algorithm.other;

import com.alibaba.fastjson.JSON;

/**
 * 合并两个有序队列
 *
 * @author huixiangdou
 * @date 2019-03-18
 */
public class MergeTwoOrderedList {
    public static int[] merge(int[] list1, int[] list2) {
        int[] result = new int[list1.length + list2.length];
        int i = 0, j = 0, index = 0;
        while (i < list1.length && j < list2.length) {
            if (list1[i] < list2[j]) {
                result[index++] = list1[i++];
            } else if (list1[i] == list2[j]) {
                result[index++] = list1[i++];
                result[index++] = list2[j++];
            } else {
                result[index++] = list2[j++];
            }
        }

        while (i < list1.length) {
            result[index++] = list1[i++];
        }

        while (j < list2.length) {
            result[index++] = list2[j++];
        }

        return result;
    }

    public static void main(String[] args) {
        int[] list1 = new int[]{1, 10};
        int[] list2 = new int[]{3, 4, 5};
        System.out.println(JSON.toJSONString(merge(list1, list2)));
    }
}
