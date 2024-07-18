package org.ml.leetcode.daily;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给你一个二维数组 edges 表示一个 n 个点的无向图，
 * 其中 edges[i] = [ui, vi, lengthi] 表示节点 ui 和节点 vi 之间有一条需要 lengthi 单位时间通过的无向边。
 *
 * 同时给你一个数组 disappear ，其中 disappear[i] 表示节点 i 从图中消失的时间点，在那一刻及以后，你无法再访问这个节点。
 *
 * 注意，图有可能一开始是不连通的，两个节点之间也可能有多条边。
 *
 * 请你返回数组 answer ，answer[i] 表示从节点 0 到节点 i 需要的 最少 单位时间。如果从节点 0 出发 无法 到达节点 i ，那么 answer[i] 为 -1 。
 */
public class MinimumTime {
    public int[] minimumTime(int n, int[][] edges, int[] disappear) {
        List<int[]>[] paths = new List[n];
        for (int i = 0; i < n; i++) {
            paths[i] = new ArrayList<>();
        }
        for (int[] edge: edges) {
            int from = edge[0];
            int to = edge[1];
            int length = edge[2];
            paths[from].add(new int[]{to, length});
            paths[to].add(new int[]{from, length});
        }
        int[] res = new int[n];
        Arrays.fill(res, -1);
        res[0] = 0;
        for(int[] path: paths[0] ) {
            travel(paths, path[0], path[1], res, disappear);
        }
        return res;
    }

    private void travel(List<int[]>[] paths, int to, int totalDis, int[] res, int[] disappear) {
        if (totalDis >= disappear[to]) {
            return;
        }
        if(res[to] != -1 && res[to] <= totalDis) {
            return;
        }
        res[to] = totalDis;
        for(int[] path: paths[to]) {
            travel(paths, path[0], totalDis+path[1], res, disappear);
        }
    }
}
