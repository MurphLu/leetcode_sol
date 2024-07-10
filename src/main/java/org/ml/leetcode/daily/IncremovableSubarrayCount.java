package org.ml.leetcode.daily;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个下标从 0 开始的 正 整数数组 nums 。
 *
 * 如果 nums 的一个子数组满足：移除这个子数组后剩余元素 严格递增 ，那么我们称这个子数组为 移除递增 子数组。
 * 比方说，[5, 3, 4, 6, 7] 中的 [3, 4] 是一个移除递增子数组，因为移除该子数组后，[5, 3, 4, 6, 7] 变为 [5, 6, 7] ，是严格递增的。
 *
 * 请你返回 nums 中 移除递增 子数组的总数目。
 *
 * 注意 ，剩余元素为空的数组也视为是递增的。
 *
 * 子数组 指的是一个数组中一段连续的元素序列。
 */
public class IncremovableSubarrayCount {
    public static void main(String[] args) {
        new IncremovableSubarrayCount().incremovableSubarrayCount(new int[]{
                2,10,9
        });
    }
    public int incremovableSubarrayCount(int[] nums) {
        int n = nums.length;
        int cnt = 0;
        List<Integer> right = new ArrayList<>();
        List<Integer> left = new ArrayList<>();
        left.add(nums[0]);

        for (int i = 0; i < n - 1; i++) {
            if(nums[i] < nums[i+1]){
                left.add(nums[i+1]);
            } else {
                break;
            }
        }
        right.add(nums[n - 1]);
        for (int i = n-1; i > 0  ; i--) {
            if (nums[i] > nums[i-1]) {
                right.add(nums[i-1]);
            } else {
                break;
            }
        }
        if (left.size() == n) {
            return (1+n)*n/2;
        }
        cnt += left.size();
        cnt += right.size();
        int idx = 0;
        while (idx < right.size() ) {
            int leftIdx = left.size() - 1;
            while (leftIdx >= 0 && right.get(idx) <= left.get(leftIdx)) {
                leftIdx--;
            }
            cnt += leftIdx == -1 ? 0 : leftIdx+1;
            idx++;
        }
        return cnt+1;
    }
}
