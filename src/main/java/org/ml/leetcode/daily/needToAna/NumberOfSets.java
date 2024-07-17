package org.ml.leetcode.daily.needToAna;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 一个公司在全国有 n 个分部，它们之间有的有道路连接。一开始，所有分部通过这些道路两两之间互相可以到达。
 *
 * 公司意识到在分部之间旅行花费了太多时间，所以它们决定关闭一些分部（也可能不关闭任何分部），同时保证剩下的分部之间两两互相可以到达且最远距离不超过 maxDistance 。
 *
 * 两个分部之间的 距离 是通过道路长度之和的 最小值 。
 *
 * 给你整数 n ，maxDistance 和下标从 0 开始的二维整数数组 roads ，其中 roads[i] = [ui, vi, wi] 表示一条从 ui 到 vi 长度为 wi的 无向 道路。
 *
 * 请你返回关闭分部的可行方案数目，满足每个方案里剩余分部之间的最远距离不超过 maxDistance。
 *
 * 注意，关闭一个分部后，与之相连的所有道路不可通行。
 *
 * 注意，两个分部之间可能会有多条道路。
 */
public class NumberOfSets {
    public int numberOfSets(int n, int maxDistance, int[][] roads) {
        int ans = 0;
        for (int mask = 0; mask < 1 << n; ++mask) {
            int[][] g = new int[n][n];
            for (var e : g) {
                Arrays.fill(e, 1 << 29);
            }
            for (var e : roads) {
                int u = e[0], v = e[1], w = e[2];
                if ((mask >> u & 1) == 1 && (mask >> v & 1) == 1) {
                    g[u][v] = Math.min(g[u][v], w);
                    g[v][u] = Math.min(g[v][u], w);
                }
            }
            for (int k = 0; k < n; ++k) {
                if ((mask >> k & 1) == 1) {
                    g[k][k] = 0;
                    for (int i = 0; i < n; ++i) {
                        for (int j = 0; j < n; ++j) {
                            g[i][j] = Math.min(g[i][j], g[i][k] + g[k][j]);
                        }
                    }
                }
            }
            int ok = 1;
            for (int i = 0; i < n && ok == 1; ++i) {
                for (int j = 0; j < n && ok == 1; ++j) {
                    if ((mask >> i & 1) == 1 && (mask >> j & 1) == 1) {
                        if (g[i][j] > maxDistance) {
                            ok = 0;
                        }
                    }
                }
            }
            ans += ok;
        }
        return ans;
    }
}
