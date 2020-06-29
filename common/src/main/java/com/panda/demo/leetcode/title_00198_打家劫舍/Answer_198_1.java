package com.panda.demo.leetcode.title_00198_打家劫舍;

/**
 * 第二题见213
 *
 * @author huixiangdou
 * @date 2020/5/29
 */
public class Answer_198_1 {

    static class Solution {
        public int rob(int[] nums) {
            //异常情况处理
            if (nums == null || nums.length == 0) {
                return 0;
            }

            /**
             *1.声明存放历史数据，dp[n]表示偷窃N间房屋的最大金额
             */
            int[] dp = new int[nums.length];

            /**
             *3.寻找初始值
             * n = nums.length
             * a.n=0,返回0
             * b.n=1,返回nums[0]
             * c.n=2,返回max(nums[0],nums[1])
             */
            dp[0] = nums[0]; //1间房
            dp[1] = Math.max(nums[0], nums[1]);//2间房

            /**
             *2.寻找关系
             *
             * a.偷窃第N间房屋，则总金额等于前N-2间房屋的最大金额 + 第N间房屋的金额
             * b.不偷窃第N间房屋，则总金额等于前N-1间房屋的最大值
             * c.取这两种情况中的最大值，得出公式 dp[n] = max(dp[n-2]+nums[n],dp[n-1])
             */
            for (int i = 2; i < nums.length; i++) {
                dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
            }

            return dp[nums.length - 1];
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().rob(new int[]{1, 2, 3, 1}));
        System.out.println(new Solution().rob(new int[]{2, 7, 9, 3, 1}));
    }
}
