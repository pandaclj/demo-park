package com.panda.demo.leetcode.title_00035_搜索插入位置;

/**
 * 采用双指针，low和height
 * 每次采用二分的方式不断缩小low和height之间的距离，直到low等于height
 *
 * @author huixiangdou
 * @date 2020/6/4
 */
public class Answer_35_1 {
    static class Solution {
        public int searchInsert(int[] nums, int target) {
            int low = 0;
            int height = nums.length - 1;

            while (low <= height) {
                int half = (height + low) / 2;

                if (target == nums[half]) {
                    return half;
                } else if (target < nums[half]) {
                    height = half - 1;
                } else {
                    low = half + 1;
                }
            }
            return low;
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 3, 5, 6};

        System.out.println(new Solution().searchInsert(nums, 5));
        System.out.println(new Solution().searchInsert(nums, 2));
        System.out.println(new Solution().searchInsert(nums, 7));
        System.out.println(new Solution().searchInsert(nums, 0));
    }
}
