package org.ml.leetcode.daily;

import java.util.Arrays;

/**
 * 你有一个下标从 0 开始、长度为 偶数 的整数数组 nums ，同时还有一个空数组 arr 。
 * Alice 和 Bob 决定玩一个游戏，游戏中每一轮 Alice 和 Bob 都会各自执行一次操作。游戏规则如下：
 *
 * 每一轮，Alice 先从 nums 中移除一个 最小 元素，然后 Bob 执行同样的操作。
 * 接着，Bob 会将移除的元素添加到数组 arr 中，然后 Alice 也执行同样的操作。
 * 游戏持续进行，直到 nums 变为空。
 * 返回结果数组 arr 。
 */
public class NumberGame {
    public int[] numberGame(int[] nums) {
        Arrays.sort(nums);
        int idx = 0;
        while (idx < nums.length) {
            int temp = nums[idx];
            nums[idx] = nums[idx+1];
            nums[idx+1] = temp;
            idx+=2;
        }
        return nums;
    }
}
