package org.ml.leetcode.daily;

/**
 * 给你一个 下标从 1 开始的 整数数组 prices ，其中 prices[i] 表示你购买第 i 个水果需要花费的金币数目。
 *
 * 水果超市有如下促销活动：
 *
 * 如果你花费 prices[i] 购买了下标为 i 的水果，那么你可以免费获得下标范围在 [i + 1, i + i] 的水果。
 * 注意 ，即使你 可以 免费获得水果 j ，你仍然可以花费 prices[j] 个金币去购买它以获得它的奖励。
 *
 * 请你返回获得所有水果所需要的 最少 金币数。
 *
 * 将问题拆解为购买当前水果的情况下其之后的水果花费的最小金额，那么我们就可以从后往前遍历
 * 比如，购买第 n-1 个水果的话，那么花费 prices[n-1]
 *      购买第 n-2 个水果的话，因为可以免费获得 i+1 ~ 2i+1 范围的水果，如果 i+1 ~ 2i+1都不买，那么2i+2就必须购买，
 *      那么就转化为 i+1 ~ 2i+2 范围内购买哪个水果可以保证购买之后的水果花费的最少
 *      依次类推
 */
public class MinimumCoins {
    public int minimumCoins(int[] prices) {
        int n = prices.length;
        int[] res = new int[n];
        res[n-1] = prices[n-1];
        for (int i = n-2; i >=0 ; i--) {
            int idx = i+1;
            int rangeEnd = 2*i+2;
            int min = Integer.MAX_VALUE;
            while (idx<=rangeEnd){
                if (idx>=n) {
                    min = 0;
                    break;
                }
                min = Math.min(min, res[idx++]);
            }
            res[i] = min+prices[i];
        }
        return res[0];
    }
}
