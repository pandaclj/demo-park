package com.panda.demo.common.algorithm.sort;

import com.alibaba.fastjson.JSON;

/**
 * 插入排序
 * 时间复杂度为o(n^2)
 * <p>
 * 核心思想(假定左侧为排好序的队列，取排好序队列的下一个元素，逐个往前挪，找到合适的位置插入进行)
 * </p>
 *
 * @author huixiangdou
 * @date 2019-03-12
 */
public class InsertSort {
    public static void sort(int[] list) {
        int currentIndex = 1; //未排好序的下标
        while (currentIndex < list.length) {
            for (int i = currentIndex - 1; i >= 0; i--) {
                if ((i == 0 && list[currentIndex] <= list[i]) || (list[currentIndex] <= list[i] && list[currentIndex] >= list[i - 1])) {
                    int tmpIndex = currentIndex;
                    int tmpValue = list[currentIndex];

                    while (tmpIndex > i) {
                        list[tmpIndex] = list[tmpIndex - 1];
                        tmpIndex--;
                    }
                    list[i] = tmpValue;
                    break;
                }
            }
            currentIndex++;
        }
    }

    public static void sort2(int[] list) {
        int current;
        for (int i = 0; i < list.length - 1; i++) {
            current = list[i + 1];
            int preIndex = i;
            while (preIndex >= 0 && current < list[preIndex]) {
                list[preIndex + 1] = list[preIndex];
                preIndex--;
            }
            list[preIndex + 1] = current;
        }
    }

    public static void main(String[] args) {
        int[] list = new int[]{3, 4, 5, 2, 1};
//        int[] list = new int[]{1, 2, 3, 4, 5};
//        int[] list = new int[]{1};
        InsertSort.sort2(list);
        System.out.println(JSON.toJSONString(list));
    }
}
