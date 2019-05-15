package com.panda.demo.common.algorithm.sort;

import com.alibaba.fastjson.JSON;

/**
 * 冒泡排序
 * 时间复杂度为o(n^2)
 * <p>
 * 核心思想(从最开始两两比较，如果前者比后者大，则交换位置，大的往下沉)
 * </p>
 *
 * @author huixiangdou
 * @date 2019-03-12
 */
public class BubbleSort{

    public static void sort(int[] list) {
        //length表示可参与排序的元素长度
        int length = list.length;
        while (length > 1) {
            for (int i = 0; i < list.length - 1; i++) {
                if (list[i] > list[i + 1]) {
//                    int temp = list[i];
//                    list[i] = list[i + 1];
//                    list[i + 1] = temp;
                    list[i] = list[i] ^ list[i + 1];
                    list[i + 1] = list[i] ^ list[i + 1];
                    list[i] = list[i] ^ list[i + 1];
                }
            }
            length = length - 1;
        }
    }

    public static void sort2(int[] list) {
        for (int i = 0; i < list.length; i++) {
            for (int j = 0; j < list.length - 1 - i; j++) {
                if (list[j + 1] < list[j]) {
                    int temp = list[j + 1];
                    list[j + 1] = list[j];
                    list[j] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] list = new int[]{3, 4, 5, 2, 1};
//        int[] list = new int[]{1, 2, 3, 4, 5};
        BubbleSort.sort2(list);
        System.out.println(JSON.toJSONString(list));
    }
}
