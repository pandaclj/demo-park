package com.panda.demo.common.algorithm.sort;

import com.alibaba.fastjson.JSON;

/**
 * 选择排序
 * 时间复杂度为o(n^2)
 * <p>
 * 核心思想(找出数组中的最小值和第一个元素进行交换,第二趟就是和第二个元素交换，小的往上浮)
 * </p>
 *
 * @author huixiangdou
 * @date 2019-03-12
 */
public class SelectorSort {
    //不需要开辟额外的内存空间
    public static void sort(int[] list) {
        for (int i = 0; i < list.length - 1; i++) {
            for (int j = i + 1; j < list.length; j++) {
                if (list[i] > list[j]) {
                    list[i] = list[i] ^ list[j];
                    list[j] = list[i] ^ list[j];
                    list[i] = list[i] ^ list[j];
                }
            }
        }
    }

    //需要额外内存来记录临时数据
    public static void sort2(int[] list) {
        for (int i = 0; i < list.length - 1; i++) { //<=length-2
            for (int j = i + 1; j < list.length; j++) { // <=length-1
                if (list[i] > list[j]) {
                    int temp = list[j];
                    list[j] = list[i];
                    list[i] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] list = new int[]{3, 4, 5, 2, 1};
        SelectorSort.sort(list);
        System.out.println(JSON.toJSONString(list));

        int[] list2 = new int[]{5, 4, 3, 2, 1};
        SelectorSort.sort(list2);
        System.out.println(JSON.toJSONString(list2));
    }
}
