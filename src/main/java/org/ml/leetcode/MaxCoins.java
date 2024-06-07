package org.ml.leetcode;

import java.util.Arrays;

public class MaxCoins {
    public int maxCoins(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n + 2][n + 2];
        for(int[] arr: dp) {
            Arrays.fill(arr, -1);
        }
        int[] newNums = new int[n + 2];
        newNums[0] = 1;
        for (int i = 0; i < nums.length; i++) {
            newNums[i+1] = nums[i];
        }
        newNums[n+1] = 1;
        return maxCoin(newNums, 0, n + 1, dp);
    }

    private int maxCoin(int[] nums, int left, int right, int[][] dp) {
        if (dp[left][right] != -1) {
            return dp[left][right];
        }
        if (left >= right) {
            return 0;
        }
        int res = 0;
        // i 作为该区间最后一个戳破的气球，可获得的最大收益
        for (int i = left+1; i < right ; i++) {
            int score = nums[left] * nums[i] * nums[right];
            score += maxCoin(nums, left, i, dp) + maxCoin(nums, i, right, dp);
            res = Math.max(res, score);
        }
        dp[left][right] = res;
        return res;
    }
}
