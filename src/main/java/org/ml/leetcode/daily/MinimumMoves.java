package org.ml.leetcode.daily;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 给你一个下标从 0 开始的二进制数组 nums，其长度为 n ；另给你一个 正整数 k 以及一个 非负整数 maxChanges 。
 *
 * Alice 在玩一个游戏，游戏的目标是让 Alice 使用 最少 数量的 行动 次数从 nums 中拾起 k 个 1 。
 * 游戏开始时，Alice 可以选择数组 [0, n - 1] 范围内的任何索引 aliceIndex 站立。
 * 如果 nums[aliceIndex] == 1 ，Alice 会拾起一个 1 ，并且 nums[aliceIndex] 变成0（这 不算 作一次行动）。
 * 之后，Alice 可以执行 任意数量 的 行动（包括零次），在每次行动中 Alice 必须 恰好 执行以下动作之一：
 *
 * - 选择任意一个下标 j != aliceIndex 且满足 nums[j] == 0 ，然后将 nums[j] 设置为 1 。这个动作最多可以执行 maxChanges 次。
 * - 选择任意两个相邻的下标 x 和 y（|x - y| == 1）且满足 nums[x] == 1, nums[y] == 0 ，
 *   然后交换它们的值（将 nums[y] = 1 和 nums[x] = 0）。
 *   如果 y == aliceIndex，在这次行动后 Alice 拾起一个 1 ，并且 nums[y] 变成 0 。
 * 返回 Alice 拾起 恰好 k 个 1 所需的 最少 行动次数。
 *
 * 2 <= n <= 10^5
 * 0 <= nums[i] <= 1
 * 1 <= k <= 10^5
 * 0 <= maxChanges <= 10^5
 * maxChanges + sum(nums) >= k
 *
 *
 * 以下几种情况：
 * k-3 不大于 maxChanges，且三个相邻的 1，，那么 相邻的三个 1 拿到需要的步数为 1+0+1，剩下的全部由 maxChanges 提供，每个两步 2+(k-3)*2L
 * k-2 不大于 maxChanges，且有两个相邻的 1，那么 相邻的两个 1 拿到需要的步数为 0+1，剩下的全部由 maxChanges 提供，每个两步 1+(k-2)*2L
 * k-1 不大于 maxChanges，如果数组中有 1，那么拿到该 1 的步数为 0，剩下的全部由 maxChanges 提供，（k-1）*2L
 * 剩下的就从数组种拿 k - maxChanges 个 1，计算最小距离，最小距离其实就是找到队列的中间元素，计算每个元素到中间元素的距离，最终再加上 maxChanges*2 即得结果
 *
 */
public class MinimumMoves {
    public static void main(String[] args) {
        System.out.println(new MinimumMoves().minimumMoves(
                new int[]{1,1,0,0,0,1,1,0,0,1},
                3,1
        ));
    }

    public long minimumMoves1(int[] nums, int k, int maxChanges) {
        boolean hasConsThree = false;
        boolean hasConsTwo = false;
        boolean hasOne = false;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                hasOne = true;
            }
            if (i>0 && nums[i] == 1 && nums[i-1] == 1) {
                hasConsTwo = true;
            }
            if(i>0 && i < nums.length - 1 && nums[i-1] == 1 && nums[i] == 1 && nums[i+1] ==1) {
                hasConsThree = true;
                break;
            }
        }

        if (hasConsThree && k-3 <= maxChanges) {
            return 2+(k-3)*2L;
        }
        if (hasConsTwo && k-2 <= maxChanges) {
            return 1+(k-2)*2L;
        }
        if (hasOne && k-1 <= maxChanges) {
            return (k-1)*2L;
        }

        LinkedList<Integer> left = new LinkedList<>();
        LinkedList<Integer> right = new LinkedList<>();
        int need = k-maxChanges;
        long minStep = Long.MAX_VALUE;
        long sumLeft = 0;
        long sumRight = 0;
        int idx = 0;
        while (idx<nums.length) {
            if(nums[idx] == 0){
                continue;
            }
            if (left.size()+right.size() == need) {
                minStep = Math.min( sumRight - (left.size() == right.size()?sumLeft : sumLeft-left.getLast()), minStep);
                sumLeft-=left.removeFirst();
            }
            right.add(idx);
            sumRight+=idx;

            while (left.size() < right.size()) {
                int r = right.removeFirst();
                sumRight-=r;
                left.add(r);
                sumLeft+=r;
            }
            idx++;
        }
        return minStep + maxChanges*2L;
    }


    public long minimumMoves(int[] nums, int k, int maxChanges) {
        int n = nums.length;
        long[] indexSum = new long[n + 1], sum = new long[n + 1];
        for (int i = 0; i < n; i++) {
            indexSum[i + 1] = indexSum[i] + nums[i] * i;
            sum[i + 1] = sum[i] + nums[i];
        }
        long res = Long.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            if (f(i, nums) + maxChanges >= k) {
                if (k <= f(i, nums)) {
                    res = Math.min(res, (long)k - nums[i]);
                } else {
                    res = Math.min(res, (long)2 * k - f(i, nums) - nums[i]);
                } continue;
            }
            int left = 0, right = n;
            while (left <= right) {
                int mid = (left + right) / 2;
                int i1 = Math.max(i - mid, 0), i2 = Math.min(i + mid, n - 1);
                if (sum[i2 + 1] - sum[i1] >= k - maxChanges) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            int i1 = Math.max(i - left, 0), i2 = Math.min(i + left, n - 1);
            if (sum[i2 + 1] - sum[i1] > k - maxChanges) {
                i1++;
            }
            long count1 = sum[i + 1] - sum[i1], count2 = sum[i2 + 1] - sum[i + 1];
            res = Math.min(res, indexSum[i2 + 1] - indexSum[i + 1] - i * count2 + i * count1 - (indexSum[i + 1] - indexSum[i1]) + 2 * maxChanges);
        }
        return res;
    }
    public int f(int i, int[] nums) {
        int x = nums[i];
        if (i - 1 >= 0) {
            x += nums[i - 1];
        }
        if (i + 1 < nums.length) {
            x += nums[i + 1];
        }
        return x;
    }



}
