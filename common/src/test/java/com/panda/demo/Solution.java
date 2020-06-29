package com.panda.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    /**
     * 结果集
     */
    private static List<String> RESULT = new ArrayList<>(10);

    /**
     * 参与全排列的数字
     */
    private static List<Integer> NUMS = Arrays.asList(1, 2, 3);

    /**
     * 遍历当前阶段的解
     * @param selectedNums   已选解集合
     * @param selectableNums 可选的解集合
     */
    public static void permutation(List<Integer> selectedNums, List<Integer> selectableNums) {

        // 满足条件，加入结果集
        if (selectedNums.size() == NUMS.size()) {
            RESULT.add(Arrays.toString(selectedNums.toArray()));
            return;
        }

        // 遍历每个阶段的可选解集合
        for (int i = 0; i < selectableNums.size(); i++) {
            Integer num = selectableNums.get(i);

            // 去除不符合条件的解，减枝
            if (selectedNums.contains(num)) {
                continue;
            }

            // 选择当前阶段其中一个解
            selectedNums.add(num);

            // 选完之后再进入下个阶段遍历
            permutation(selectedNums, selectableNums);

            // 回溯,换一个解继续遍历
            selectedNums.remove(num);
        }
    }

    public static void main(String[] args) {
        List<Integer> selectedNums = new ArrayList<>();
        permutation(selectedNums, NUMS);
        System.out.println(Arrays.toString(RESULT.toArray()));
    }
}