package com.panda.demo.common.algorithm.sort;

import com.alibaba.fastjson.JSON;

/**
 * 快速排序
 * <p>
 * 核心思想：找一个基准元素，将大于该基准元素的放到右边，小于基准元素的放到左边，递归进行此操作
 * 找基准元素我们可以认为第一个元素为基准元素，也可以用随机的方式生成
 * </p>
 * <p>
 * 有两个指针，一个在最低位，一个在最高位
 * 先从高位向右移动，再从低位像左移动
 *
 * @author huixiangdou
 * @date 2019-03-18
 */
public class QuickSort {
    public static void sort(int[] list) {
        if (list.length < 2) {
            return;
        }
        _sort(list, 0, list.length - 1);

    }

    private static void _sort(int[] list, int low, int height) {
        if (low < height) {
            int base = getBaseIndex(list, low, height);
            _sort(list, low, base - 1);
            _sort(list, base + 1, height);
        }
    }

    //返回基准数据应该处于的位置
    private static int getBaseIndex(int[] list, int low, int height) {
        int base = list[low];
        while (low < height) {
            while (low < height && list[height] >= base) {
                height--;
            }
            list[low] = list[height];
            while (low < height && list[low] <= base) {
                low++;
            }
            list[height] = list[low];
        }
        //跳出循环，则low=height
        list[low] = base;
        return low;
    }

    public static void main(String[] args) {
        int[] list = new int[]{3, 4, 5, 2, 1};
        QuickSort.sort(list);
        System.out.println(JSON.toJSONString(list));

        int[] list2 = new int[]{5, 4, 3, 2, 1};
        QuickSort.sort(list2);
        System.out.println(JSON.toJSONString(list2));
    }
}
