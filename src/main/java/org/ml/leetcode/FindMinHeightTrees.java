package org.ml.leetcode;

import java.util.*;

/**
 * 树是一个无向图，其中任何两个顶点只通过一条路径连接。 换句话说，任何一个没有简单环路的连通图都是一棵树。
 *
 * 给你一棵包含 n 个节点的树，标记为 0 到 n - 1 。
 * 给定数字 n 和一个有 n - 1 条无向边的 edges 列表（每一个边都是一对标签），其中 edges[i] = [ai, bi] 表示树中节点 ai 和 bi 之间存在一条无向边。
 *
 * 可选择树中任何一个节点作为根。当选择节点 x 作为根节点时，设结果树的高度为 h 。在所有可能的树中，具有最小高度的树（即，min(h)）被称为 最小高度树 。
 *
 * 请你找到所有的 最小高度树 并按 任意顺序 返回它们的根节点标签列表。
 *
 * 树的 高度 是指根节点和叶子节点之间最长向下路径上边的数量。
 *
 * 拓扑排序，循环删除出度为 1 节点（叶节点），直到剩余一个或连个节点即为最终结果
 */
public class FindMinHeightTrees {
    static class Node{
        int val;
        Map<Integer, Node> children;

        public Node(int val) {
            this.val = val;
            children = new HashMap<>();
        }

        public int outSize() {
            return children.size();
        }
        public void addChild(Node child) {
            children.put(child.val, child);
        }
        public Node deleteSelfFromTree() {
            if (children.size() == 1) {
                for(Node child: children.values()) {
                    child.deleteNode(this);
                    if (child.outSize() == 1) {
                        return child;
                    }
                }
            }
            return null;
        }

        private void deleteNode(Node node) {
            children.remove(node.val);
        }
    }
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> res = new ArrayList<>();
        if (n <= 2) {
            res = n == 1 ? Arrays.asList(0) : Arrays.asList(0,1);
            return res;
        }
        Map<Integer, Node> map = new HashMap<>();

        for(int[] edge: edges) {
            int a = edge[0];
            int b = edge[1];
            Node nodeA = map.getOrDefault(a, new Node(a));
            Node nodeB = map.getOrDefault(b, new Node(b));
            nodeA.addChild(nodeB);
            nodeB.addChild(nodeA);
            map.put(a, nodeA);
            map.put(b, nodeB);
        }
        List<Node> nodeList = new ArrayList<>();
        for (Node node: map.values()) {
            if(node.outSize() == 1) nodeList.add(node);
        }
        while (map.size() > 2) {
            List<Node> nodes = new ArrayList<>();
            for (Node oneOutNode: nodeList) {
                Node newOne = oneOutNode.deleteSelfFromTree();
                map.remove(oneOutNode.val);
                if (newOne != null) {
                    nodes.add(newOne);
                }
            }
            nodeList = nodes;
        }
        res.addAll(map.keySet());
        return res;
    }


}
