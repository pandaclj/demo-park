package com.panda.demo.common.algorithm.sort;

import com.alibaba.fastjson.JSON;

/**
 * 希尔排序
 *
 * @author huixiangdou
 * @date 2019-03-12
 */
public class XiErSort {

    public static void sort(int[] list) {
        int tmp, step = list.length / 2;
        while (step > 0) {
            for (int i = step; i < list.length; i++) {
                tmp = list[i];
                int preIndex = i - step;
                while (preIndex >= 0 && list[preIndex] > tmp) {
                    list[preIndex + step] = list[preIndex];
                    preIndex -= step;
                }
                list[preIndex + step] = tmp;
            }
            step /= 2;
        }
    }

    public static void main(String[] args) {
        int[] list = new int[]{3, 4, 5, 2, 1};
//        int[] list = new int[]{1, 2, 3, 4, 5};
        XiErSort.sort(list);
        System.out.println(JSON.toJSONString(list));
    }

}
