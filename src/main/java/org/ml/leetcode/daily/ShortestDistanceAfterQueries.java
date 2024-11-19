package org.ml.leetcode.daily;

import java.util.*;

public class ShortestDistanceAfterQueries {
    public int[] shortestDistanceAfterQueries(int n, int[][] queries) {
        List<Integer>[] list = new List[n];
        for (int i = 0; i < list.length-1; i++) {
            List<Integer> next = new ArrayList<>();
            next.add(i+1);
            list[i] = next;
        }
        list[list.length-1] = new ArrayList<>();
        int[] res = new int[queries.length];
        int idx = 0;

        for (int[] query: queries) {
            list[query[0]].add(query[1]);
            int[] dp = new int[n];
            Arrays.fill(dp, -1);
            res[idx++] = dfs(list, 0, dp);
        }
        return res;
    }

    private int dfs(List<Integer>[] list, int cur, int[] dp) {
        if (list[cur].size() == 0) {
            return 0;
        }
        if (dp[cur] != -1) {
            return dp[cur];
        }
        int min = Integer.MAX_VALUE;
        for (int i: list[cur]) {
            min = Math.min(min, dfs(list, i, dp));
        }
        dp[cur] = min + 1;
        return min + 1;
    }
}
