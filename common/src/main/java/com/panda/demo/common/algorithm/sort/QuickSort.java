package com.panda.demo.common.algorithm.sort;

import com.alibaba.fastjson.JSON;

/**
 * 快速排序
 *
 * @author huixiangdou
 * @date 2019-03-18
 */
public class QuickSort {
    public static void sort(int[] list, int _left, int _right) {
        int left = _left;
        int right = _right;

        if (left <= right) {
            int base = list[left];
            while (left != right) {
                while (right > left && list[right] >= base) {
                    right--;
                }
                list[left] = list[right];

                while (left < right && list[left] <= base) {
                    left++;
                }
                list[right] = list[left];
            }
            list[right] = base;

            sort(list, _left, left - 1);
            sort(list, right + 1, _right);
        }
    }

    public static void main(String[] args) {
        int[] list = new int[]{3, 4, 5, 2, 1};
//        int[] list = new int[]{1, 2, 3, 4, 5};
        QuickSort.sort(list, 0, list.length - 1);
        System.out.println(JSON.toJSONString(list));
    }
}
