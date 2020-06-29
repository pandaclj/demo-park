package com.panda.demo.leetcode.title_00198_打家劫舍;

/**
 * @author huixiangdou
 * @date 2020/6/1
 */
public class Answer_198_2 {

    static class Solution {
        public int rob(int[] nums) {
            //异常情况处理
            if (nums == null || nums.length == 0) {
                return 0;
            }

            /**
             *1.声明存放历史数据，dp[n]表示偷窃N间房屋的最大金额
             * 空间从o(n)优化到o(1)
             */
//            int[] dp = new int[nums.length];

            //只要存储n-2和前n-1的值就行
            int dp_n2;
            int dp_n1;
            int dp_n = 0;

            /**
             *3.寻找初始值
             * n = nums.length
             * a.n=0,返回0
             * b.n=1,返回nums[0]
             * c.n=2,返回max(nums[0],nums[1])
             */
            dp_n2 = nums[0]; //1间房
            if(nums.length == 1){
                return dp_n2;
            }
            dp_n1 = Math.max(nums[0], nums[1]);//2间房
            if(nums.length == 2){
                return dp_n1;
            }

            /**
             *2.寻找关系
             *
             * a.偷窃第N间房屋，则总金额等于前N-2间房屋的最大金额 + 第N间房屋的金额
             * b.不偷窃第N间房屋，则总金额等于前N-1间房屋的最大值
             * c.取这两种情况中的最大值，得出公式 dp[n] = max(dp[n-2]+nums[n],dp[n-1])
             */
            //从3间房开始
            for (int i = 2; i < nums.length; i++) {
                dp_n = Math.max(dp_n2 + nums[i], dp_n1);
                dp_n2 = dp_n1;
                dp_n1 = dp_n;
            }

            return dp_n;
        }
    }

    public static void main(String[] args) {
        System.out.println(new Answer_198_2.Solution().rob(new int[]{1, 2, 3, 1}));
        System.out.println(new Answer_198_2.Solution().rob(new int[]{2, 7, 9, 3, 1}));
    }
}
