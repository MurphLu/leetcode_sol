package org.ml.leetcode;

/**
 * 在一条环路上有 n 个加油站，其中第 i 个加油站有汽油 gas[i] 升。
 *
 * 你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。你从其中的一个加油站出发，开始时油箱为空。
 *
 * 给定两个整数数组 gas 和 cost ，如果你可以按顺序绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1 。如果存在解，则 保证 它是 唯一 的。
 * // [1,2,3,4,5], cost = [3,4,5,1,2]
 */
public class CanCompleteCircuit {
    public static void main(String[] args) {
//        new CanCompleteCircuit().canCompleteCircuit(new int[]{1,2,3,4,5}, new int[]{3,4,5,1,2});
        Road road = new Road(new int[]{1, -1, -1, -3, -4, 5, 6, 7, -10, 1, 2});
        road.zip();
        System.out.println(road);

    }
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;
        int[] remain = new int[n];
        int totalGas = 0;
        int totalCost = 0;


        for (int i = 0; i < n; i++) {
            totalCost+= cost[i];
            totalGas += gas[i];
            remain[i] = gas[i] - cost[i];
        }
        if (totalCost > totalGas) {
            return -1;
        }
        if (n == 1) {
            return 0;
        }
        Road r = new Road(remain);
        r.zip();
        return 0;
    }

    static class Road{
        Info info;
        int size;

        public Road(int[] remain) {
            size = remain.length;
            info = new Info(0, remain[0], null);
            buildRoad(remain);
        }

        private void buildRoad(int[] remain) {
            Info tail = info;
            for (int i = 1; i < remain.length; i++) {
                Info next = new Info(i, remain[i], null);
                tail.next = next;
                tail = next;
            }
            tail.next = info;
        }

        public void zip() {
            Info node = info;
            while ((node.remain >= 0 && node.next.remain < 0) || (node.remain < 0 && node.next.remain >= 0)){
                node = node.next;
            }
            Info start = node;
            while (node.next != start) {
                if ((node.remain >= 0 && node.next.remain >= 0) || (node.remain < 0 && node.next.remain < 0)) {
                    node.remain += node.next.remain;
                    node.next = node.next.next;
                    size--;
                } else {
                    node = node.next;
                }
            }
            info = start;
        }

        public void merge() {
            Info node = info;
            Info start = node;
            while (node.next != start) {
                if (node.remain > 0 && (node.remain + node.next.remain < 0)) {
                    node.remain += node.next.remain;
                    node.next = node.next.next;
                    size--;
                } else{
                    node = node.next;
                }
            }
            info = start;

        }
    }

    static class Info{
        int idx;
        int remain;
        Info next;

        public Info(int idx, int remain, Info next) {
            this.idx = idx;
            this.remain = remain;
            this.next = next;
        }
    }
}
