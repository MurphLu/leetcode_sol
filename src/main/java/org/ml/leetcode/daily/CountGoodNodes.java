package org.ml.leetcode.daily;

import java.util.*;

public class CountGoodNodes {
    public static void main(String[] args) {
        new CountGoodNodes().countGoodNodes(new int[][]{
                new int[]{0,1},
                new int[]{0,2},
                new int[]{1,3},
                    new int[]{1,4},
        new int[]{2,5},
        new int[]{2,6}
        });
    }

    static class Node {
        List<Integer> path;
        int val;

        public Node(int val) {
            this.val = val;
            this.path = new ArrayList<>();
        }

        public void addPath(int val) {
            path.add(val);
        }
    }

    public int countGoodNodes(int[][] edges) {
        Map<Integer, Node> nodeMap = new HashMap();
        for (int i = 0; i <=edges.length; i++) {
            nodeMap.put(i, new Node(i));
        }
        Map<Integer, List<Integer>> map = new HashMap<>();
        for(int[] edge: edges) {
            int n = edge[0];
            int c = edge[1];
            nodeMap.get(n).addPath(c);
            nodeMap.get(c).addPath(n);
        }
        Set<Integer> isGood = new HashSet<>();
        process(nodeMap, -1, 0, isGood);
        return isGood.size();
    }

    private int process(Map<Integer, Node> nodeMap, int fromVal, int nodeVal, Set<Integer> isGood) {
        Node node = nodeMap.get(nodeVal);
        List<Integer> paths = node.path;
        if (node.path.isEmpty()) {
            isGood.add(nodeVal);
            return 1;
        }
        Set<Integer> res = new HashSet<>();
        int total = 0;
        boolean good = true;
        for(int i: paths) {
            if (i == fromVal) {
                continue;
            }
            int cnt = process(nodeMap, nodeVal, i, isGood);
            if(!res.isEmpty() && !res.contains(cnt)){
                good =false;
            }
            res.add(cnt);
            total+= cnt;
        }
        if (good) {
            isGood.add(nodeVal);
        }
        total+=1;
        return total;
    }

}
