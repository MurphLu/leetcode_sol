package org.ml.leetcode.daily;

/**
 * 给你一个长度为 n 的整数数组 nums 和一个正整数 k 。
 *
 * 一个数组的 能量值 定义为：
 *
 * 如果 所有 元素都是依次 连续 且 上升 的，那么能量值为 最大 的元素。
 * 否则为 -1 。
 * 你需要求出 nums 中所有长度为 k 的 子数组 的能量值。
 *
 * 请你返回一个长度为 n - k + 1 的整数数组 results ，其中 results[i] 是子数组 nums[i..(i + k - 1)] 的能量值。
 */
public class ResultsArray {
    public int[] resultsArray(int[] nums, int k) {
        if (k == 1) {
            return nums;
        }
        int n = nums.length;
        int lIdx = 0;
        int rIdx = 0;
        int lastUpBegin = 0;
        while (rIdx < k-1) {
            if (nums[rIdx] != nums[rIdx+1]-1){
                lastUpBegin = rIdx+1;
            }
            rIdx++;
        }
        int[] res = new int[n-k+1];
        if (lastUpBegin <= lIdx) {
            res[lIdx] = nums[rIdx];
        } else {
            res[lIdx] = -1;
        }
        while (rIdx < n-1) {
            if (nums[rIdx] != nums[rIdx+1]-1){
                lastUpBegin = rIdx+1;
            }
            rIdx++;
            lIdx++;
            if (lastUpBegin <= lIdx) {
                res[lIdx] = nums[rIdx];
            } else {
                res[lIdx] = -1;
            }
        }
        return res;
    }
}
