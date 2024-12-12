package org.ml.leetcode.daily;

import java.util.Arrays;

/**
 * 给你一个下标从 0 开始大小为 m * n 的整数矩阵 values ，
 * 表示 m 个不同商店里 m * n 件不同的物品。每个商店有 n 件物品，
 * 第 i 个商店的第 j 件物品的价值为 values[i][j] 。
 * 除此以外，第 i 个商店的物品已经按照价值非递增排好序了，
 * 也就是说对于所有 0 <= j < n - 1 都有 values[i][j] >= values[i][j + 1] 。
 *
 * 每一天，你可以在一个商店里购买一件物品。具体来说，在第 d 天，你可以：
 *
 * 选择商店 i 。
 * 购买数组中最右边的物品 j ，开销为 values[i][j] * d 。
 * 换句话说，选择该商店中还没购买过的物品中最大的下标 j ，并且花费 values[i][j] * d 去购买。
 * 注意，所有物品都视为不同的物品。比方说如果你已经从商店 1 购买了物品 0 ，你还可以在别的商店里购买其他商店的物品 0 。
 *
 * 请你返回购买所有 m * n 件物品需要的 最大开销 。
 *
 * 该题目其实就完可以作为归并排序来处理，由于约往后所乘的系数越大，
 * 所以肯定是价值小的放在最前面购买
 * 且商店的个数在 10 个以内
 * 我们维护一个商店最后一个商品的 index 数组即可方便的获取每个商店目前最后一个商品的位置
 * 每次取所有商店中的最小价值的商品即可
 * 最终时间复杂度为 O(m*m*n)，由于 m<=10，最终的时间复杂度也基本为 O(n)
 */
public class MaxSpending {
    public static void main(String[] args) {
        long l = new MaxSpending().maxSpending(
                new int[][]{
                        new int[]{8, 5, 2},
                        new int[]{6, 4, 1},
                        new int[]{9, 7, 3}
                }
        );
        System.out.println(l);
    }
    public long maxSpending(int[][] values) {
        int m = values.length;
        int n = values[0].length;
        // 维护商店销售情况
        int[] idxes = new int[m];
        // 每个商店的最后一个商品初始位置
        Arrays.fill(idxes, n-1);
        // 天数
        long day = 1;
        // 总花费
        long cost = 0;
        // 搞一个死循环来做归并
        for (;;){
            // 当前一轮中最后一个商品价值最小的商店
            int minShop;
            // 当前遍历到的商店
            int currShop = 0;
            // 先找到第一个没有销售完商品的商店，初始化 minShop
            while (currShop < m && idxes[currShop] < 0){
                currShop++;
            }
            minShop = currShop;

            // 然后再遍历之后的商店，找出有未销售商品的商店中，价值最小的一个商品位置
            while (++currShop < m){
                if(idxes[currShop] < 0) {
                    continue;
                }
                if(values[currShop][idxes[currShop]] < values[minShop][idxes[minShop]]) {
                    minShop = currShop;
                }
            }

            // 如果已经超出商店索引，则说明已经全部售完，退出死循环
            if (minShop == m) {
                break;
            }
            // 计算当前一轮的花费
            cost += (long)values[minShop][idxes[minShop]]*day;
            // 下一天
            day++;
            // 维护卖出商品商店的剩余商品个数
            idxes[minShop]--;
        }
        return cost;
    }
}
