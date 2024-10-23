package org.ml.leetcode.daily;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 给你一个整数数组 nums，和一个整数 k 。
 *
 * 对于每个下标 i（0 <= i < nums.length），将 nums[i] 变成 nums[i] + k 或 nums[i] - k 。
 *
 * nums 的 分数 是 nums 中最大元素和最小元素的差值。
 */
public class SmallestRangeII {
    public int smallestRangeII(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        int ans = nums[n - 1] - nums[0];
        for (int i = 1; i < n; i++) {
            int mx = Math.max(nums[i - 1] + k, nums[n - 1] - k);
            int mn = Math.min(nums[0] + k, nums[i] - k);
            ans = Math.min(ans, mx - mn);
        }

        return ans;
    }
}
