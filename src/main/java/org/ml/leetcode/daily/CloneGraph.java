package org.ml.leetcode.daily;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.List;


/**
 * 克隆图
 */
public class CloneGraph {
    static class Node {
        public int val;
        public List<Node> neighbors;
        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }
        Node[] nodes = new Node[100];
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            if (nodes[cur.val-1] != null) {
                continue;
            }
            nodes[cur.val-1] = cur;
            queue.addAll(cur.neighbors);
        }
        int count = 0;
        for (Node n: nodes){
            if (n!= null) {
                count++;
            } else {
                break;
            }
        }
        Node[] clonedNodes = new Node[count+1];
        for (int i = 0; i < count; i++) {
            clonedNodes[i] = new Node(i + 1);
        }
        for (int i = 0; i < count; i++) {
            Node source = nodes[i];
            Node target = clonedNodes[i];
            for(Node neighbot: source.neighbors) {
                target.neighbors.add(clonedNodes[neighbot.val-1]);
            }
        }
        return clonedNodes[0];
    }
}
