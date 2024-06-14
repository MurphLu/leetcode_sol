package org.ml.leetcode.daily;

import java.util.Arrays;

/**
 * 给你一个下标从 0 开始的整数数组 nums 和一个正整数 x 。
 *
 * 你 一开始 在数组的位置 0 处，你可以按照下述规则访问数组中的其他位置：
 *
 * 如果你当前在位置 i ，那么你可以移动到满足 i < j 的 任意 位置 j 。
 * 对于你访问的位置 i ，你可以获得分数 nums[i] 。
 * 如果你从位置 i 移动到位置 j 且 nums[i] 和 nums[j] 的 奇偶性 不同，那么你将失去分数 x 。
 * 请你返回你能得到的 最大 得分之和。
 */
public class MaxScore {
    public static void main(String[] args) {
        System.out.println(new MaxScore().maxScore(new int[]{2,4,6,8}, 3));
    }
    static class Info {
        int num;
        long maxEvenTo;
        long maxOddTo;

        public Info(int num, long maxEvenTo, long maxOddTo) {
            this.num = num;
            this.maxEvenTo = maxEvenTo;
            this.maxOddTo = maxOddTo;
        }

        @Override
        public String toString() {
            return "Info{" +
                    "num=" + num +
                    ", maxEvenTo=" + maxEvenTo +
                    ", maxOddTo=" + maxOddTo +
                    '}';
        }
    }
    public long maxScore(int[] nums, int x) {
        Info[] res = new Info[nums.length];
        for (int i = nums.length - 1; i > 0 ; i--) {
            res[i] = generate(nums[i], x, i == nums.length - 1 ? null : res[i+1]);
        }
        int num = nums[0];

        return num % 2 == 1 ? num + res[1].maxOddTo : num + res[1].maxEvenTo;
    }

    private Info generate(int num, int x, Info next) {
        boolean isOdd = num % 2 == 1;
        long maxOddTo;
        long maxEvenTo;
        if (next == null) {
            maxEvenTo = isOdd ? Math.max(num - x, 0) : num;
            maxOddTo = isOdd ? num : Math.max(num - x, 0);
        } else {
            maxEvenTo =  isOdd ? Math.max(num-x+next.maxOddTo, next.maxEvenTo) : num + next.maxEvenTo;
            maxOddTo = isOdd ? num + next.maxOddTo : Math.max(num - x + next.maxEvenTo, next.maxOddTo);
        }
        return new Info(num, maxEvenTo, maxOddTo);
    }
}
