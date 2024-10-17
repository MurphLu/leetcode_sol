package org.ml.leetcode.daily;

import java.util.Arrays;

/**
 * 给你一个整数 n 和一个二维数组 requirements ，其中 requirements[i] = [endi, cnti] 表示这个要求中的末尾下标和 逆序对 的数目。
 *
 * 整数数组 nums 中一个下标对 (i, j) 如果满足以下条件，那么它们被称为一个 逆序对 ：
 *
 * i < j 且 nums[i] > nums[j]
 * 请你返回 [0, 1, 2, ..., n - 1] 的 排列 perm 的数目，满足对 所有 的 requirements[i] 都有 perm[0..endi] 恰好有 cnti 个逆序对。
 *
 * 由于答案可能会很大，将它对 109 + 7 取余 后返回。
 */
public class NumberOfPermutations {
    public int numberOfPermutations(int n, int[][] requirements) {
        final int MOD = 1_000_000_007;
        int[] req = new int[n];
        Arrays.fill(req, -1);
        req[0] = 0;
        int m = 0;
        for (int[] p : requirements) {
            req[p[0]] = p[1]; m = Math.max(m, p[1]);
        }
        if (req[0] > 0) {
            return 0;
        }
        int[][] f = new int[n][m + 1];
        f[0][0] = 1;
        for (int i = 1; i < n; i++) {
            int mx = req[i] < 0 ? m : req[i];
            int r = req[i - 1]; if (r >= 0) {
                for (int j = r; j <= Math.min(i + r, mx); j++) {
                    f[i][j] = f[i - 1][r];
                }
            } else {
                for (int j = 0; j <= mx; j++) {
                    for (int k = 0; k <= Math.min(i, j); k++) {
                        f[i][j] = (f[i][j] + f[i - 1][j - k]) % MOD;
                    }
                }
            }
        }
        return f[n - 1][req[n - 1]];
    }
}
