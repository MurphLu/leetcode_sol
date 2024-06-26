package org.ml.leetcode.daily;

import java.util.Arrays;

/**
 * 给你一个下标从 0 开始的整数数组 nums ，它包含 n 个 互不相同 的正整数。如果 nums 的一个排列满足以下条件，我们称它是一个特别的排列：
 *
 * 对于 0 <= i < n - 1 的下标 i ，要么 nums[i] % nums[i+1] == 0 ，要么 nums[i+1] % nums[i] == 0 。
 * 请你返回特别排列的总数目，由于答案可能很大，请将它对 10^9 + 7 取余 后返回。
 *
 * 示例 1：
 *
 * 输入：nums = [2,3,6]
 * 输出：2
 * 解释：[3,6,2] 和 [2,6,3] 是 nums 两个特别的排列。
 * 示例 2：
 *
 * 输入：nums = [1,4,3]
 * 输出：2
 * 解释：[3,1,4] 和 [4,1,3] 是 nums 两个特别的排列。
 * 提示：
 *
 * 2 <= nums.length <= 14
 * 1 <= nums[i] <= 10^9
 */
public class SpecialPerm {
    public static void main(String[] args) {
        new SpecialPerm().specialPerm(new int[]{
                2,3,6
        });
    }

    public int specialPerm(int[] nums) {
        final int mod = (int) 1e9 + 7;
        int n = nums.length;
        int m = 1 << n;
        int[][] f = new int[m][n];
        for (int i = 1; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if ((i >> j & 1) == 1) {
                    int ii = i ^ (1 << j);
                    if (ii == 0) {
                        f[i][j] = 1; continue;
                    }
                    for (int k = 0; k < n; ++k) {
                        if (nums[j] % nums[k] == 0 || nums[k] % nums[j] == 0) {
                            f[i][j] = (f[i][j] + f[ii][k]) % mod;
                        }
                    }
                }
            }
        }
        int ans = 0;
        for (int x : f[m - 1]) {
            ans = (ans + x) % mod;
        }
        return ans;
    }

    private final static int mod = (int)1e9 + 7;
    int[][] dp;
    boolean[][] divisible;
    int[] nums;
    int m, n;
    public int specialPerm2(int[] nums) {
        n = nums.length;
        m = (1 << n) - 1;
        this.nums = nums;
        dp = new int[n][1 << n];
        for (int[] row : dp) Arrays.fill(row, -1);
        divisible = new boolean[n][n];
        // 可以相邻的数字字典
        for (int i = 0; i < n; ++i){
            for (int j = i + 1; j < n; ++j){
                if (nums[i] % nums[j] == 0 || nums[j] % nums[i] == 0 ){
                    divisible[i][j] = divisible[j][i] = true;
                }
            }
        }

        int result = 0;
        for(int i = 0; i < n; i++){
            result += dfs(i, 1 << i);
            result %= mod;
        }

        return result;
    }

    /**
     *
     *
     * @param pre 上一个数字的 index
     * @param mask 数字使用情况统计
     * @return
     */
    int dfs(int pre, int mask){
        // 全部使用之后，说明可以全部用完，符合条件
        if(mask == m)
            return 1;

        // 如果该情况已经统计过，直接返回
        if(dp[pre][mask] != -1)
            return dp[pre][mask];


        long result = 0;
        // 未使用数字
        int avaliable = (~mask) & m;
        // 分别尝试每个数字是否能放到当前数字之后，如果可以则继续 dfs 遍历，否则尝试下一个数字
        for(int s = avaliable; s > 0; s &= s -1){
            int i = Integer.bitCount((s & (-s)) - 1);
            if(divisible[pre][i])
                result += dfs(i, mask | (1 << i));
        }

        return dp[pre][mask] = (int) (result % mod);
    }


}
