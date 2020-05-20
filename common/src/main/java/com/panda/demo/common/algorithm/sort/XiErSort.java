package com.panda.demo.common.algorithm.sort;

import com.alibaba.fastjson.JSON;

/**
 * 希尔排序
 * 希尔排序时间复杂度是o(nlog2n)
 * 希尔排序是一种特殊的插入排序
 * 在插入排序上包一层，计算间隔
 *
 * @author huixiangdou
 * @date 2019-03-12
 */
public class XiErSort {
    public static void sort(int[] list) {
        for (int gap = list.length >> 1; gap > 0; gap >>= 1) {
            _sort(list, gap);
        }
    }


    private static void _sort(int[] list, int gap) {
        if (list.length < 1) {
            return;
        }
        //假设第一个元素为已经排好序的元素
        for (int i = gap; i <= list.length - gap; i = i + gap) {
            if (list[i] < list[i - gap]) { //list[i]为待排元素,待排元素小于已经有序的最后一个，则需要做插入
                int tmp = list[i];//把待插入元素存储到临时变量,防止数组元素移位的时候被覆盖
                int index = 0; //待插入的下标
                for (int j = i - gap; j >= 0; j--) { //从待插入元素的下标往前遍历
                    if (list[j] > tmp) { //如果遍历到的元素比需要插入的元素大，则遍历到的元素需要往右移
                        list[j + gap] = list[j];
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
        XiErSort.sort(list);
        System.out.println(JSON.toJSONString(list));

        int[] list2 = new int[]{1, 2, 3, 4, 5};
        XiErSort.sort(list2);
        System.out.println(JSON.toJSONString(list2));
    }

}
