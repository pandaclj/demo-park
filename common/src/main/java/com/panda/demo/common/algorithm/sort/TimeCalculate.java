package com.panda.demo.common.algorithm.sort;

import java.util.Arrays;
import java.util.Random;
import java.util.function.Consumer;

/**
 * @author huixiangdou
 * @date 2019-03-12
 */
public class TimeCalculate {
    private static void sort(Consumer<int[]> function, int[] list, int[] list2) {
        list = Arrays.copyOf(list, list.length);
        list2 = Arrays.copyOf(list2, list2.length);
        long start = System.currentTimeMillis();
        function.accept(list);
        System.out.println("有序队列：" + (System.currentTimeMillis() - start) + "ms");
//        System.out.println(JSON.toJSONString(list));
        start = System.currentTimeMillis();
        function.accept(list2);
        System.out.println("乱序队列：" + (System.currentTimeMillis() - start) + "ms");
//        System.out.println(JSON.toJSONString(list2));
    }

    public static void main(String[] args) {
        int[] list = new int[10000];
        for (int i = 0; i < list.length; i++) {
            list[i] = i;
        }

        int[] list2 = new int[10000];
        Random random = new Random();
        for (int i = 0; i < list.length; i++) {
            list2[i] = random.nextInt(list2.length);
        }

        System.out.println("冒泡排序");
        TimeCalculate.sort((a) -> BubbleSort.sort2(a), list, list2);
        System.out.println("选择排序");
        TimeCalculate.sort((a) -> SelectorSort.sort2(a), list, list2);
        System.out.println("插入排序");
        TimeCalculate.sort((a) -> InsertSort.sort2(a), list, list2);
        System.out.println("希尔排序");
        TimeCalculate.sort((a) -> XiErSort.sort(a), list, list2);
        System.out.println("归并排序");
        TimeCalculate.sort((a) -> MergeSort.sort(a), list, list2);
        System.out.println("快速排序");
        TimeCalculate.sort((a) -> QuickSort.sort(a, 0, a.length - 1), list, list2);
    }

}
