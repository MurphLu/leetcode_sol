package org.ml.leetcode.daily;

import java.util.Arrays;

/**
 * 给你两个长度为 n 下标从 0 开始的整数数组 cost 和 time ，分别表示给 n 堵不同的墙刷油漆需要的开销和时间。你有两名油漆匠：
 *
 * 一位需要 付费 的油漆匠，刷第 i 堵墙需要花费 time[i] 单位的时间，开销为 cost[i] 单位的钱。
 * 一位 免费 的油漆匠，刷 任意 一堵墙的时间为 1 单位，开销为 0 。但是必须在付费油漆匠 工作 时，免费油漆匠才会工作。
 * 请你返回刷完 n 堵墙最少开销为多少。
 *
 *
 *
 */
public class PaintWalls {
    public static void main(String[] args) {
        int i = new PaintWalls().paintWalls(
                new int[]{2,3,4,2},
                new int[]{1,1,1,1}
        );
        System.out.println(i);
    }
    public int paintWalls(int[] cost, int[] time) {
        int n = cost.length;
        int[][] memo = new int[n][n * 2 + 1];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
            // -1 表示没有计算过
        }
        return dfs(n - 1, 0, cost, time, memo);
    }

    /**
     *
     * @param i 第 i 堵墙
     * @param j 当前状态，如果第 i 堵墙付费刷，那么有time[j]堵墙可以免费刷 j 前进 time[j]，如果第 i 堵墙免费刷，那么 j 则后退 1，因为 该堵墙免费则前面免费的少刷一面墙
     * @param cost cost 数组
     * @param time time 数组
     * @param memo dp
     * @return
     */
    private int dfs(int i, int j, int[] cost, int[] time, int[][] memo) {
        if (j > i) {
            // i 之前的墙都可以免费刷，不需再遍历
            return 0;
        }
        if (i < 0) {
            // 上面 if 不成立，意味着 j < 0，无法刷完全部墙
            // 当前情况无法刷完所有墙
            return Integer.MAX_VALUE / 2;
            // 防止加法溢出
        }
        int k = j + memo.length;
        // 加上偏移量，防止出现负数
        if (memo[i][k] != -1) {
            // 之前计算过
            return memo[i][k];
        }
        // 付费刷第 i 堵墙
        int res1 = dfs(i - 1, j + time[i], cost, time, memo) + cost[i];
        // 免费刷第 i 堵墙
        int res2 = dfs(i - 1, j - 1, cost, time, memo);
        // 更新 dp 并返回
        return memo[i][k] = Math.min(res1, res2);
    }
}
