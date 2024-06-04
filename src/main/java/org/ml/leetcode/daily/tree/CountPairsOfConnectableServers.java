package org.ml.leetcode.daily.tree;

import java.util.*;

/**
 * 给你一棵无根带权树，树中总共有 n 个节点，分别表示 n 个服务器，服务器从 0 到 n - 1 编号。
 * 同时给你一个数组 edges ，其中 edges[i] = [ai, bi, weighti] 表示节点 ai 和 bi 之间有一条双向边，边的权值为 weighti 。
 * 再给你一个整数 signalSpeed 。
 *
 * 如果两个服务器 a ，b 和 c 满足以下条件，那么我们称服务器 a 和 b 是通过服务器 c 可连接的 ：
 *
 * a < b ，a != c 且 b != c 。
 * 从 c 到 a 的距离是可以被 signalSpeed 整除的。
 * 从 c 到 b 的距离是可以被 signalSpeed 整除的。
 * 从 c 到 b 的路径与从 c 到 a 的路径没有任何公共边。
 * 请你返回一个长度为 n 的整数数组 count ，其中 count[i] 表示通过服务器 i 可连接 的服务器对的 数目 。
 *
 * 2 <= n <= 1000
 * edges.length == n - 1
 * edges[i].length == 3
 * 0 <= ai, bi < n
 * edges[i] = [ai, bi, weighti]
 * 1 <= weighti <= 106
 * 1 <= signalSpeed <= 106
 * 输入保证 edges 构成一棵合法的树。
 */
public class CountPairsOfConnectableServers {
    public static void main(String[] args) {
        int[] ints = new CountPairsOfConnectableServers().countPairsOfConnectableServers(
                new int[][]{new int[]{0, 6, 3}, new int[]{6, 5, 3}, new int[]{0, 3, 1}, new int[]{3, 2, 7}, new int[]{3, 1, 6}, new int[]{3, 4, 2}},
                3
        );
        System.out.println(Arrays.toString(ints));
    }
    static class Node {
        int val;

        List<Edge> edgeList;
        List<Node> nexts;
        Map<Node, Integer> distance;

        public Node(int val) {
            this.val = val;
            edgeList = new ArrayList<>();
            nexts = new ArrayList<>();
            distance = new HashMap<>();
        }

        public void addEdge(Edge e) {
            edgeList.add(e);
            nexts.add(e.to);
        }
    }
    static class Edge{
        Node from;
        Node to;
        int weight;

        public Edge(Node from, Node to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    public int[] countPairsOfConnectableServers(int[][] edges, int signalSpeed) {
        int n = 0;
        Map<Integer, Node> nodeMap = new HashMap<>();
        for(int[] edge: edges) {
            int a = edge[0];
            int b = edge[1];
            int w = edge[2];
            n = Math.max(a, n);
            n = Math.max(b, n);
            Node nodeA = nodeMap.getOrDefault(a, new Node(a));
            Node nodeB = nodeMap.getOrDefault(b, new Node(b));
            Edge aToB = new Edge(nodeA, nodeB, w);
            Edge bToA = new Edge(nodeB, nodeA, w);
            nodeA.addEdge(aToB);
            nodeB.addEdge(bToA);
            nodeMap.put(a, nodeA);
            nodeMap.put(b, nodeB);
        }
        int[] res = new int[n+1];
        for (int i = 0; i <= n; i++) {
            Node node = nodeMap.get(i);
            if (node.edgeList.size() == 1) {
                res[i] = 0;
            } else {
                List<Integer> counts = new ArrayList<>();
                for(Edge edge: node.edgeList) {
                    Node to = edge.to;
                    int weight = edge.weight;
                    Set<Integer> set = new HashSet<>();
                    set.add(i);
                    set.add(to.val);
                    counts.add(dfs(to, weight, set, signalSpeed));
                }
                int total = 0;
                for (int j = 0; j < counts.size(); j++) {
                    for (int k = j+1; k < counts.size(); k++) {
                        total += counts.get(j) * counts.get(k);
                    }
                }
                res[i] = total;
            }
        }
        return res;
    }

    private int dfs(Node node, int dis, Set<Integer> go, int signalSpeed) {
        List<Edge> edges = node.edgeList;
        int res = (dis % signalSpeed == 0 ? 1 : 0);

        for(Edge edge: edges) {
            Node to = edge.to;
            int weight = edge.weight;
            if (go.add(to.val)) {
                res += dfs(to, dis+weight, go, signalSpeed);
            }
        }
        return res;
    }
}
