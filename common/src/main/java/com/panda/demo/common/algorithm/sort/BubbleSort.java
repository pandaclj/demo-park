package com.panda.demo.common.algorithm.sort;

import com.alibaba.fastjson.JSON;

/**
 * 冒泡排序
 * 时间复杂度为o(n^2)
 * <p>
 * 核心思想(从最开始两两比较，如果前者比后者大，则交换位置，大的往下沉)
 * 每次一次循环，最大的数就会在最下面,最小的数会往上冒
 * </p>
 *
 * @author huixiangdou
 * @date 2019-03-12
 */
public class BubbleSort {

    public static void sort(int[] list) {
        for (int i = 0; i < list.length - 1; i++) {
            for (int j = 0; j < list.length - 1 - i; j++) {
                if (list[j + 1] < list[j]) { //后面的小于前面的，需要交换
                    int tmp = list[j + 1];
                    list[j + 1] = list[j];
                    list[j] = tmp;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] list = new int[]{3, 4, 5, 2, 1};
        BubbleSort.sort(list);
        System.out.println(JSON.toJSONString(list));

        int[] list2 = new int[]{5, 4, 3, 2, 1};
        BubbleSort.sort(list2);
        System.out.println(JSON.toJSONString(list2));
    }
}
