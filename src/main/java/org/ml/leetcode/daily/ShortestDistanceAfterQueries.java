package org.ml.leetcode.daily;

import java.util.*;

/**
 * 给你一个整数 n 和一个二维整数数组 queries。
 *
 * 有 n 个城市，编号从 0 到 n - 1。初始时，每个城市 i 都有一条单向道路通往城市 i + 1（ 0 <= i < n - 1）。
 *
 * queries[i] = [ui, vi] 表示新建一条从城市 ui 到城市 vi 的单向道路。每次查询后，你需要找到从城市 0 到城市 n - 1 的最短路径的长度。
 *
 * 返回一个数组 answer，对于范围 [0, queries.length - 1] 中的每个 i，answer[i] 是处理完前 i + 1 个查询后，从城市 0 到城市 n - 1 的最短路径的长度。
 *
 *
 * 3 <= n <= 500
 * 1 <= queries.length <= 500
 * queries[i].length == 2
 * 0 <= queries[i][0] < queries[i][1] < n
 * 1 < queries[i][1] - queries[i][0]
 * 查询中没有重复的道路。
 */
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
