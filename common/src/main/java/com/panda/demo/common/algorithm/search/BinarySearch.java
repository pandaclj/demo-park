package com.panda.demo.common.algorithm.search;

/**
 * 二分查找法
 * 前提是队列是有序的
 *
 * @author huixiangdou
 * @date 2019-03-12
 */
public class BinarySearch {

    public static int search(int[] list, int searchValue) {
        int low = 0;
        int high = list.length - 1;
        while (low <= high) {
            int halfIndex = (low + high) / 2;
            if (list[halfIndex] == searchValue) {
                return halfIndex;
            } else if (list[halfIndex] < searchValue) {
                low = halfIndex + 1;
            } else {
                high = halfIndex -1;
            }
        }
        return -1; //表示未找到
    }

    public static void main(String[] args) {
//        int[] list = new int[]{1, 2, 3, 4, 5};
        int[] list = new int[]{1, 2};
        System.out.println(BinarySearch.search(list,2));
    }
}
