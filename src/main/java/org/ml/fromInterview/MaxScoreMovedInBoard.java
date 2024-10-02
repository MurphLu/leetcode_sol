package org.ml.fromInterview;

import java.util.Arrays;

/**
 * 薯队长从北向南穿过一片红薯地(南北长M，东西宽N)，
 * 红薯地被划分为1x1的方格他可以从北边的任何一个格子出发，到达南边的任何一个格子，
 * 但每一步只能走到东南、正南、西南方向的三个格子之一，
 * 而且不能跨出红薯地，他可以获得经过的格子上的所有红薯，请问他可以获得最多的红薯个数。
 */
public class MaxScoreMovedInBoard {

    public static int maxScore(int[][] map) {
        int row = map.length;
        int col = map[0].length;
        int[][] dp = new int[row][col];
        for(int[] arr: dp) {
            Arrays.fill(dp, -1);
        }
        int ans = 0;
        for (int i = 0; i < col; i++) {
            ans = Math.max(ans, process(map, 0, col, dp));
        }
        return ans;
    }

    public static int process(int[][] map, int row, int col, int[][] dp){
        if (col < 0 || col == map[0].length) {
            return 0;
        }
        if (row==map.length-1) {
            return map[row][col];
        }
        if (dp[row][col] != -1) {
            return dp[row][col];
        }
        int max = Math.max(process(map, row+1, col-1, dp), process(map, row+1, col, dp));
        max = Math.max(max,process(map, row+1, col+1, dp));
        dp[row][col] = map[row][col] + max;
        return dp[row][col];
    }


    public static int maxScoreFill(int[][] map) {
        int row = map.length;
        int col = map[0].length;
        int res = 0;
        for (int i = row - 2; i >= 0 ; i--) {
            for (int j = 0; j < col; j++) {
                int maxBlow = j-1 >=0 ? map[i+1][j-1] : 0;
                maxBlow = Math.max(map[i+1][j], maxBlow);
                maxBlow = Math.max(maxBlow, j+1 < col ? map[i+1][j+1] : 0);
                map[i][j] = maxBlow + map[i][j];
                if (i == 0) {
                    res = Math.max(map[i][j], res);
                }
            }
        }
        return res;
    }
}

