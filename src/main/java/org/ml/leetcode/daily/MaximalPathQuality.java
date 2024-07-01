package org.ml.leetcode.daily;

import java.util.*;

/**
 * 给你一张 无向 图，图中有 n 个节点，节点编号从 0 到 n - 1 （都包括）。
 * 同时给你一个下标从 0 开始的整数数组 values ，其中 values[i] 是第 i 个节点的 价值 。
 * 同时给你一个下标从 0 开始的二维整数数组 edges ，其中 edges[j] = [uj, vj, timej] 表示节点 uj 和 vj 之间有一条需要 timej 秒才能通过的无向边。
 * 最后，给你一个整数 maxTime 。
 *
 * 合法路径 指的是图中任意一条从节点 0 开始，最终回到节点 0 ，且花费的总时间 不超过 maxTime 秒的一条路径。
 * 你可以访问一个节点任意次。一条合法路径的 价值 定义为路径中 不同节点 的价值 之和 （每个节点的价值 至多 算入价值总和中一次）。
 *
 * 请你返回一条合法路径的 最大 价值。
 *
 * n == values.length
 * 1 <= n <= 1000
 * 0 <= values[i] <= 10^8
 * 0 <= edges.length <= 2000
 * edges[j].length == 3
 * 0 <= uj < vj <= n - 1
 * 10 <= timej, maxTime <= 100
 * [uj, vj] 所有节点对 互不相同 。
 * 每个节点 至多有四条 边。
 * 图可能不连通
 */
public class MaximalPathQuality {
    static class Graph{
        Node[] nodes;

        public Graph(int[] values) {
            nodes = new Node[values.length];
            for (int i = 0; i < values.length; i++) {
                nodes[i] = new Node(i, values[i]);
            }
        }

        public Node getNode(int node) {
            return nodes[node];
        }

        public void initEdges(int[][] edges) {
            for(int[] edge: edges) {
                Node node1 = nodes[edge[0]];
                Node node2 = nodes[edge[1]];
                int time = edge[2];
                Edge edge1 = new Edge(node1, node2, time);
                Edge edge2 = new Edge(node2, node1, time);
                node1.addEdge(edge1);
                node2.addEdge(edge2);
            }
        }
    }

    static class Edge{
        Node from;
        Node to;
        int time;

        public Edge(Node from, Node to, int time) {
            this.from = from;
            this.to = to;
            this.time = time;
        }
    }

    static class Node{
        int val;
        int price;

        List<Edge> edges;

        public Node(int val, int price) {
            this.val = val;
            this.price = price;
            edges = new ArrayList<>();
        }

        public void addEdge(Edge edge) {
            edges.add(edge);
        }
    }
    int ans = 0;
    public int maximalPathQuality(int[] values, int[][] edges, int maxTime) {
        Graph graph = new Graph(values);
        graph.initEdges(edges);

        Node node = graph.getNode(0);
        int price = node.price;
        node.price = 0;
        process(node, maxTime, price);

        return ans;
    }

    private void process(Node node, int maxTime, int price) {
        if (node.val == 0) {
            ans = Math.max(ans, price);
        }
        for(Edge edge: node.edges) {
            if (maxTime - edge.time >= 0) {
                int temp = edge.to.price;
                edge.to.price = 0;
                process(edge.to, maxTime - edge.time, price + temp);
                edge.to.price = temp;
            }
        }
    }
}
