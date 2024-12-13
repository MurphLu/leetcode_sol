package org.ml.leetcode.daily;

import java.util.PriorityQueue;

/**
 * 给你一个整数数组 nums ，一个整数 k 和一个整数 multiplier 。
 *
 * 你需要对 nums 执行 k 次操作，每次操作中：
 *
 * 找到 nums 中的 最小 值 x ，如果存在多个最小值，选择最 前面 的一个。
 * 将 x 替换为 x * multiplier 。
 * 请你返回执行完 k 次乘运算之后，最终的 nums 数组。
 */
public class GetFinalState {
    public int[] getFinalState(int[] nums, int k, int multiplier) {
        PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> {
            if(nums[o1] - nums[o2] == 0) {
                return o1-o2;
            }
            return nums[o1] - nums[o2];
        });
        for (int i = 0; i < nums.length; i++) {
            queue.add(i);
        }
        while (k > 0) {
            int idx = queue.poll();
            nums[idx] *= multiplier;
            queue.add(idx);
            k--;
        }
        return nums;
    }
}
