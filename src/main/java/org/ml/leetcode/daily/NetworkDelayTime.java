package org.ml.leetcode.daily;

import java.util.ArrayList;
import java.util.List;

/**
 * 有 n 个网络节点，标记为 1 到 n。
 * 给你一个列表 times，表示信号经过 有向 边的传递时间。 times[i] = (ui, vi, wi)，
 * 其中 ui 是源节点，vi 是目标节点， wi 是一个信号从源节点传递到目标节点的时间。
 * 现在，从某个节点 K 发出一个信号。需要多久才能使所有节点都收到信号？如果不能使所有节点收到信号，返回 -1 。
 * 1 <= k <= n <= 100
 * 1 <= times.length <= 6000
 * times[i].length == 3
 * 1 <= ui, vi <= n
 * ui != vi
 * 0 <= wi <= 100
 * 所有 (ui, vi) 对都 互不相同（即，不含重复边）
 */
public class NetworkDelayTime {
    static class Node{
        List<int[]> path;
        int minCost;

        public Node() {
            this.path = new ArrayList<>();
            minCost = Integer.MAX_VALUE;
        }

        public void addPath(int[] path){
            this.path.add(path);
        }
    }
    public int networkDelayTime(int[][] times, int n, int k) {
        Node[] nodes = new Node[n+1];
        nodes[0] = new Node();
        nodes[0].minCost = 0;
        for (int i = 1; i <= n; i++) {
            nodes[i] = new Node();
        }
        for(int[] time: times) {
            int from = time[0];
            int to = time[1];
            int cost = time[2];
            int[] path = new int[]{to, cost};
            nodes[from].addPath(path);
        }
        nodes[k].minCost = 0;
        process(nodes, k);
        int maxCost = Integer.MIN_VALUE;
        for(Node node: nodes) {
            if (node.minCost == Integer.MAX_VALUE) {
                return -1;
            }
            maxCost = Math.max(node.minCost, maxCost);
        }
        return maxCost;
    }

    private void process(Node[] nodes, int cur) {
        Node node = nodes[cur];
        for(int[] p: node.path) {
            int to = p[0];
            int cost = p[1];
            Node toNode = nodes[to];
            int newCost = node.minCost+cost;
            if (toNode.minCost > newCost) {
                toNode.minCost = newCost;
                process(nodes, to);
            }
        }
    }
}
