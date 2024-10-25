package org.ml.leetcode.daily;

import java.util.Arrays;

public class MaxTotalReward {
    public static void main(String[] args) {
        new MaxTotalReward().maxTotalReward(new int[]{6,13,6,6,2,2,11,13,16,4,16,7});
    }
    public int maxTotalReward(int[] rewardValues) {
        Arrays.sort(rewardValues);
        int m = rewardValues[rewardValues.length - 1];
        int[] dp = new int[2 * m];
        dp[0] = 1;
        for (int x : rewardValues) {
            for (int k = 2 * x - 1; k >= x; k--) {
                if (dp[k - x] == 1) {
                    dp[k] = 1;
                }
            }
        }
        int res = 0;
        for (int i = 0; i < dp.length; i++) {
            if (dp[i] == 1) {
                res = i;
            }
        }
        return res;
    }
}
