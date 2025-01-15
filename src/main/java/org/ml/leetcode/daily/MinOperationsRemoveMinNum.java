package org.ml.leetcode.daily;

import java.util.PriorityQueue;

/**
 * 给你一个下标从 0 开始的整数数组 nums 和一个整数 k 。
 *
 * 一次操作中，你将执行：
 *
 * 选择 nums 中最小的两个整数 x 和 y 。
 * 将 x 和 y 从 nums 中删除。
 * 将 min(x, y) * 2 + max(x, y) 添加到数组中的任意位置。
 * 注意，只有当 nums 至少包含两个元素时，你才可以执行以上操作。
 *
 * 你需要使数组中的所有元素都大于或等于 k ，请你返回需要的 最少 操作次数。
 */
public class MinOperationsRemoveMinNum {
    public int minOperations(int[] nums, int k) {
        PriorityQueue<Long> queue = new PriorityQueue<>();
        int min = Integer.MAX_VALUE;
        for(int num : nums) {
            if(num<k) queue.offer((long)num);
            else min = Math.min(min, num);
        }
        int cnt = 0;
        while (!queue.isEmpty()) {
            long a = queue.poll();
            long b;
            if(queue.isEmpty()) {
                b = min;
            } else {
                b = queue.poll();
            }
            long res = Math.min(a,b)*2+Math.max(a,b);
            if (res < k) queue.offer(res);
            cnt++;
        }
        return cnt;
    }
}
