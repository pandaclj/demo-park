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
    public static void sort(int[] list) {
        int currentIndex = 1; //未排好序的第一个元素
        while (currentIndex <= list.length) {
            int minIndex = currentIndex - 1; //假定最小值为当前轮次的第一个元素
            for (int i = currentIndex; i < list.length; i++) {
                if (list[i] < list[minIndex]) {
                    minIndex = i; //更换最小值下标
                }
            }
            if (minIndex != currentIndex - 1) {
                int tmp = list[currentIndex - 1];
                list[currentIndex - 1] = list[minIndex];
                list[minIndex] = tmp;
            }
            currentIndex += 1;
        }
    }

    public static void sort2(int[] list) {
        for (int i = 0; i < list.length; i++) {
            int minIndex = i;
            for (int j = i; j < list.length; j++) {
                if (list[j] < list[minIndex]) { //找到最小的数
                    minIndex = j; //将最小数的索引保存
                }
            }
            int temp = list[minIndex];
            list[minIndex] = list[i];
            list[i] = temp;
        }
    }

    public static void main(String[] args) {
        int[] list = new int[]{3, 4, 5, 2, 1};
//        int[] list = new int[]{1, 2, 3, 4, 5};
//        int[] list = new int[]{1};
        SelectorSort.sort(list);
        System.out.println(JSON.toJSONString(list));
    }
}
