package org.ml.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你一棵有 n 个节点的无向树，节点编号为 0 到 n-1 ，它们中有一些节点有苹果。通过树上的一条边，需要花费 1 秒钟。你从 节点 0 出发，请你返回最少需要多少秒，可以收集到所有苹果，并回到节点 0 。
 *
 * 无向树的边由 edges 给出，其中 edges[i] = [fromi, toi] ，表示有一条边连接 from 和 toi 。
 * 除此以外，还有一个布尔数组 hasApple ，其中 hasApple[i] = true 代表节点 i 有一个苹果，否则，节点 i 没有苹果。
 *
 *
 * 1 <= n <= 10^5
 * edges.length == n - 1
 * edges[i].length == 2
 * 0 <= ai < bi <= n - 1
 * hasApple.length == n
 */
public class MinTime {
    static class Node{
        boolean hasApple;
        List<Node> children;

        public Node(boolean hasApple) {
            this.hasApple = hasApple;
            children = new ArrayList<>();
        }
        public void addChild(Node child) {
            children.add(child);
        }
    }
    public int minTime(int n, int[][] edges, List<Boolean> hasApple) {
        Node[] nodes = new Node[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = new Node(hasApple.get(i));
        }
        for(int[] edge: edges) {
            int from = edge[0];
            int to = edge[1];
            nodes[from].addChild(nodes[to]);
            nodes[to].addChild(nodes[from]);
        }
        int res = collect(nodes[0], null);
        return res>0 ? res-2:res;
    }

    private int collect(Node node, Node from) {
        if (node == null) {
            return 0;
        }
        int cnt = 0;
        for(Node child: node.children) {
            if(from==child) {
                continue;
            }
            cnt += collect(child, node);
        }
        if (cnt == 0 && !node.hasApple) {
            return 0;
        } else {
            return cnt + 2;
        }
    }
}
