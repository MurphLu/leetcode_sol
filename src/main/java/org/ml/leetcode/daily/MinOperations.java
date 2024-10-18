package org.ml.leetcode.daily;

/**
 * 给你一个二进制数组 nums 。
 *
 * 你可以对数组执行以下操作 任意 次（也可以 0 次）：
 *
 * 选择数组中 任意连续 3 个元素，并将它们 全部反转 。
 * 反转 一个元素指的是将它的值从 0 变 1 ，或者从 1 变 0 。
 *
 * 请你返回将 nums 中所有元素变为 1 的 最少 操作次数。如果无法全部变成 1 ，返回 -1 。
 */
public class MinOperations {
    public static void main(String[] args) {

    }
    public int minOperations(int[] nums) {
        int end = 3;
        int step = 0;
        int start = 0;
        while (end < nums.length) {
            if (nums[start] == 1) {
                start++;
                end++;
            } else {
                step += 1;
                int temp = start;
                while (temp<end){
                    nums[temp] = nums[temp] == 1 ? 0 : 1;
                    temp++;
                }
            }
        }
        int cnt = 0;
        while (start < end) {
            cnt += nums[start++];
        }
        if (cnt == 3) {
            return step+1;
        } else if (cnt == 0) {
            return step;
        }
        return -1;

    }

}
