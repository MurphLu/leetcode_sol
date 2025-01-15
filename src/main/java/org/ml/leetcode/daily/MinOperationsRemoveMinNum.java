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
        // 准备一个优先队列，用来存储小于 k 的数，每次拿两个进行操作
        PriorityQueue<Long> queue = new PriorityQueue<>();
        // 同时找出一个大于 k 的最小值，以备最终优先队列里只剩一个数，这时就需要一个数来配合操作
        long min = Integer.MAX_VALUE;
        for(int num : nums) {
            // 小于 k 直接入队列
            if(num<k) queue.offer((long)num);
            // 否则看是否最小，并保存
            else min = Math.min(min, (long)num);
        }
        // 记录操作数
        int cnt = 0;
        // 队列不为空就继续操作
        while (!queue.isEmpty()) {
            // 取最小两个数
            long a = queue.poll();
            long b;
            // 队列为空就找当前数组中大于 k 的最小值，不为空就拿队列中的最小值
            if(queue.isEmpty()) {
                b = min;
            } else {
                b = queue.poll();
            }
            // 操作
            long res = Math.min(a,b)*2+Math.max(a,b);
            // 操作完的数字小于 k 入队列
            if (res < k) queue.offer(res);
            // 否则判断是否是大于等于 k 的最小值，是则更新最小值
            else min = Math.min(min, res);
            // 记录操作数字
            cnt++;
        }
        return cnt;
    }
}
