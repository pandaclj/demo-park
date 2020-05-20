package com.panda.demo.common.algorithm.sort;

import com.alibaba.fastjson.JSON;

import java.util.Arrays;

/**
 * 归并排序
 * <p>
 *     核心思想是将数组分成左右两部分，递归分，直到每部分列表长度都是1，然后将左右两个有序数组进行合并
 * </p>
 *
 * @author huixiangdou
 * @date 2019-03-18
 */
public class MergeSort {
    /**
     * 归并排序
     *
     * @param array
     * @return
     */
    public static int[] sort(int[] array) {
        if (array.length < 2) {
            return array;
        }
        int mid = array.length / 2;
        int[] left = Arrays.copyOfRange(array, 0, mid);
        int[] right = Arrays.copyOfRange(array, mid, array.length);
        return merge(sort(left), sort(right));
    }

    /**
     * 归并排序——将两段排序好的数组结合成一个排序数组
     *
     * @param left
     * @param right
     * @return
     */
    public static int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        for (int index = 0, i = 0, j = 0; index < result.length; index++) {
            if (i >= left.length) {
                result[index] = right[j++];
            } else if (j >= right.length) {
                result[index] = left[i++];
            } else if (left[i] > right[j]) {
                result[index] = right[j++];
            } else {
                result[index] = left[i++];
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] list = new int[]{3, 4, 5, 2, 1};
//        int[] list = new int[]{1, 2, 3, 4, 5};
        int[] result = MergeSort.sort(list);
        System.out.println(JSON.toJSONString(result));
    }
}
