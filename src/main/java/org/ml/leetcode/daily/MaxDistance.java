package org.ml.leetcode.daily;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 给定 m 个数组，每个数组都已经按照升序排好序了。
 *
 * 现在你需要从两个不同的数组中选择两个整数（每个数组选一个）并且计算它们的距离。两个整数 a 和 b 之间的距离定义为它们差的绝对值 |a-b| 。
 *
 * 返回最大距离。
 *
 * 示例 1：
 *
 * 输入：[[1,2,3],[4,5],[1,2,3]]
 * 输出：4
 * 解释：
 * 一种得到答案 4 的方法是从第一个数组或者第三个数组中选择 1，同时从第二个数组中选择 5 。
 */
public class MaxDistance {
    public int maxDistance(List<List<Integer>> arrays) {
        PriorityQueue<Integer> minQueue = new PriorityQueue<>((idx1, idx2)->arrays.get(idx2).get(0)-arrays.get(idx1).get(0));
        PriorityQueue<Integer> maxQueue = new PriorityQueue<>((idx1, idx2)->{
            List<Integer> integers1 = arrays.get(idx1);
            List<Integer> integers2 = arrays.get(idx2);
            return integers1.get(integers1.size()-1) - integers2.get(integers2.size()-1);
        });

        for (int i = 0; i < arrays.size(); i++) {
            if (minQueue.size() < 2) {
                minQueue.offer(i);
                maxQueue.offer(i);
            } else {
                minQueue.offer(i);
                maxQueue.offer(i);
                minQueue.poll();
                maxQueue.poll();
            }
        }
        int minIdx1 = minQueue.poll();
        int minIdx2 = minQueue.poll();
        int maxIdx1 = maxQueue.poll();
        List<Integer> maxArr1 = arrays.get(maxIdx1);
        int maxValue1 = maxArr1.get(maxArr1.size()-1);
        int maxIdx2 = maxQueue.poll();
        List<Integer> maxArr2 = arrays.get(maxIdx2);
        int maxValue2 = maxArr2.get(maxArr2.size()-1);
        int max = 0;
        if (minIdx1 != maxIdx1) {
            max = Math.max(max, maxValue1 - arrays.get(minIdx1).get(0));
        }
        if (minIdx1 != maxIdx2) {
            max = Math.max(max, maxValue2 - arrays.get(minIdx1).get(0));
        }
        if (minIdx2 != maxIdx1) {
            max = Math.max(max, maxValue1 - arrays.get(minIdx2).get(0));
        }
        if (minIdx2 != maxIdx2) {
            max = Math.max(max, maxValue2 - arrays.get(minIdx2).get(0));
        }
        return max;
    }

}
