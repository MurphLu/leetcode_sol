package org.ml.leetcode.daily;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * 给你一个长度为 n 的整数数组 nums 和一个 正 整数 k 。
 *
 * 一个 子序列 的 能量 定义为子序列中 任意 两个元素的差值绝对值的 最小值 。
 *
 * 请你返回 nums 中长度 等于 k 的 所有 子序列的 能量和 。
 *
 * 由于答案可能会很大，将答案对 10^9 + 7 取余 后返回。
 */
public class SumOfPowers  {
    public static void main(String[] args) {
        int i = new SumOfPowers().sumOfPowers(
                new int[]{-24, -921, 119, -291, -65, -628, 372, 274, 962, -592, -10, 67, -977, 85, -294, 349, -119, -846, -959, -79, -877, 833, 857, 44, 826, -295, -855, 554, -999, 759, -653, -423, -599, -928},
                19
        );
        System.out.println(i);
    }
    int mod = 1000000007;
    int res = 0;
    public int sumOfPowers(int[] nums, int k) {
        Arrays.sort(nums);
        for (int i = 0; i <= nums.length - k; i++) {
            process(nums, nums[i], k-1, Integer.MAX_VALUE, i+1);
        }
        return res;
    }

    private void process(int[] nums, int last, int k, int min, int idx) {
        if (k == 0) {
            res += min;
            res %= mod;
            return;
        }
        if (nums.length - idx < k) {
            return;
        }
        process(nums, last, k, min, idx+1);
        process(nums, nums[idx], k-1, Math.min(min, nums[idx]-last), idx+1);

    }


    int MOD = (int) 1e9 + 7;
    Map<String , Long> memo;
    public int sumOfPowersCP(int[] nums, int k) {
        memo = new HashMap<>();
        Arrays.sort(nums);
        //递归入口 ， pre 和 min 取Integer.MAX_VALUE / 2 ，防止计算负数时溢出
        return (int) dfs(nums.length - 1 , k , Integer.MAX_VALUE / 2 , Integer.MAX_VALUE / 2 , nums);
    }

    /**
     *
     * @param remain 遍历剩余
     * @param restK 剩余未选
     * @param pre 上一个所选数字
     * @param min 最小差值绝对值
     * @param nums 源数组
     * @return
     */
    long dfs(int remain , int restK , int pre , int min , int[] nums){
        //当前剩余i + 1 个数字， 剩余数字小于还要选择的数字， 怎么选都不能够选出k个数 ， 返回0
        if (remain + 1 < restK){
            return 0;
        }
        //选够了
        if (restK == 0){
            return min;
        }
        //把当前状态 映射为字符串
        // 共剩余，剩余未选，上一个数字，选择上一个数字时最小差绝对值，如果当前状态满足相同的情况下，之后各种情况得到的最小差值也是相同的
        String key = remain + "#" + restK + "#" + pre + "#" + min;
        //如果之前计算过
        if (memo.containsKey(key)){
            return memo.get(key);
        }
        //不选 当前元素
        long res1 = dfs(remain - 1, restK , pre , min , nums);
        //选 当前元素
        long res2 = dfs(remain - 1 , restK - 1 , nums[remain] , Math.min(min , pre - nums[remain]) , nums);
        memo.put(key , (res1 + res2) % MOD);
        return (res1 + res2) % MOD;
    }

}
