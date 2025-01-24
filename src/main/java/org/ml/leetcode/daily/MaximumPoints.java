package org.ml.leetcode.daily;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 有一棵由 n 个节点组成的无向树，以 0 为根节点，节点编号从 0 到 n - 1 。给你一个长度为 n - 1 的二维 整数 数组 edges ，其中 edges[i] = [ai, bi] 表示在树上的节点 ai 和 bi 之间存在一条边。另给你一个下标从 0 开始、长度为 n 的数组 coins 和一个整数 k ，其中 coins[i] 表示节点 i 处的金币数量。
 *
 * 从根节点开始，你必须收集所有金币。要想收集节点上的金币，必须先收集该节点的祖先节点上的金币。
 *
 * 节点 i 上的金币可以用下述方法之一进行收集：
 *
 * 收集所有金币，得到共计 coins[i] - k 点积分。如果 coins[i] - k 是负数，你将会失去 abs(coins[i] - k) 点积分。
 * 收集所有金币，得到共计 floor(coins[i] / 2) 点积分。如果采用这种方法，节点 i 子树中所有节点 j 的金币数 coins[j] 将会减少至 floor(coins[j] / 2) 。
 * 返回收集 所有 树节点的金币之后可以获得的最大积分。
 */

// 当前解法会超时
public class MaximumPoints {
    static class Node{
        private int val;
        private List<Node> children;
        public Node(int val) {
            this.val = val;
            this.children = new ArrayList<Node>();
        }
        public void addChild(Node node){
            this.children.add(node);
        }
    }


    public int maximumPoints(int[][] edges, int[] coins, int k) {
        if (k ==0){
            return Arrays.stream(coins).reduce(Integer::max).getAsInt();
        }
        Node root = buildTree(edges);
        return process(root, coins, 0,  k);
    }

    private int process(Node root, int[] coins, int down, int k){
        if (root == null) return 0;
        int val = root.val;
        int coin = coins[val]>>down;
        int res1 = Math.abs(coin-k);
        for(Node child : root.children){
            res1 += process(child, coins, down, k);
        }
        int res2 = coin>>1;
        for(Node child : root.children){
            res2 += process(child, coins, down+1, k);
        }
        return Math.max(res1, res2);
    }

    public Node buildTree(int[][] edges) {
        Node[] nodes = new Node[edges.length+1];
        for (int i = 0; i < nodes.length; i++) {
            nodes[i] = new Node(i);
        }
        for(int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            nodes[from].addChild(nodes[to]);
            nodes[to].addChild(nodes[from]);
        }
        return nodes[0];
    }
}
