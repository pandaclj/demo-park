package com.panda.demo.common.algorithm.sort;

import com.alibaba.fastjson.JSON;

/**
 * 插入排序
 * 稳定排序
 * 时间复杂度为o(n^2)
 * <p>
 * 核心思想(假定左侧为排好序的队列，取排好序队列的下一个元素，逐个往前挪，找到合适的位置插入进行)
 * </p>
 * 插入排序适合在有序度高或者小规模的排序中
 *
 * @author huixiangdou
 * @date 2019-03-12
 */
public class InsertSort {
    public static void sort(int[] list) {
        if (list.length < 1) {
            return;
        }
        //假设第一个元素为已经排好序的元素
        for (int i = 1; i <= list.length - 1; i++) {
            if (list[i] < list[i - 1]) { //list[i]为待排元素,待排元素小于已经有序的最后一个，则需要做插入
                int tmp = list[i];//把待插入元素存储到临时变量,防止数组元素移位的时候被覆盖
                int index = 0; //待插入的下标
                for (int j = i - 1; j >= 0; j--) { //从待插入元素的下标往前遍历
                    if (list[j] > tmp) { //如果遍历到的元素比需要插入的元素大，则遍历到的元素需要往右移
                        list[j + 1] = list[j];
                    } else {
                        index = j;
                        break;
                    }
                }
                list[index] = tmp;
            }
        }
    }

    public static void main(String[] args) {
        int[] list = new int[]{3, 4, 5, 2, 1};
        InsertSort.sort(list);
        System.out.println(JSON.toJSONString(list));

        int[] list2 = new int[]{5, 4, 3, 2, 1};
        InsertSort.sort(list2);
        System.out.println(JSON.toJSONString(list2));
    }
}
