package org.ml.leetcode.daily;

import java.util.*;

/**
 * 给你一个正整数数组 values，其中 values[i] 表示第 i 个观光景点的评分，并且两个景点 i 和 j 之间的 距离 为 j - i。
 *
 * 一对景点（i < j）组成的观光组合的得分为 values[i] + values[j] + i - j ，也就是景点的评分之和 减去 它们两者之间的距离。
 *
 * 返回一对观光景点能取得的最高分。
 *
 * 示例 1：
 *
 * 输入：values = [8,1,5,2,6]
 * 输出：11
 * 解释：i = 0, j = 2, values[i] + values[j] + i - j = 8 + 5 + 0 - 2 = 11
 * 示例 2：
 *
 * 输入：values = [1,2]
 * 输出：2
 * 提示：
 *
 * 2 <= values.length <= 5 * 104
 * 1 <= values[i] <= 1000
 */
public class MaxScoreSightseeingPair {
    public static void main(String[] args) {
        new MaxScoreSightseeingPair().maxScoreSightseeingPair(new int[]{8,1,5,2,6});
    }
    public int maxScoreSightseeingPair(int[] values) {
        int n = values.length;
        int[] i = new int[n];
        int[] j = new int[n];
        List<Integer> idx = new ArrayList<>(n);
        for (int k = 0; k < values.length; k++) {
            i[k] = values[k] + k;
            j[k] = values[k] - k;
            idx.add(k);
        }
        idx.sort(((o1, o2)-> j[o1] - j[o2]));
//        Arrays.sort(idx, ((o1, o2)-> j[(int) o2]-j[(int) o1]));
//        PriorityQueue<Integer> queue = new PriorityQueue<>(((o1, o2)-> j[o2]-j[o1]));

//        for (int k = 0; k < j.length; k++) {
//            queue.add(k);
//        }
        int max = Integer.MIN_VALUE;
        for (int k = 0; k < i.length; k++) {
            while (!idx.isEmpty() && k >= idx.get(idx.size()-1)) {
                idx.remove(idx.size()-1);
            }
            if (!idx.isEmpty()) {
                max = Math.max(max, i[k] + j[idx.get(idx.size()-1)]);
            }
        }
        return max;
    }
}
