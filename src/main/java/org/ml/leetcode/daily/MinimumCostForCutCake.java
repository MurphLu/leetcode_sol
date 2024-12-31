package org.ml.leetcode.daily;

import java.util.Arrays;

/**
 * 有一个 m x n 大小的矩形蛋糕，需要切成 1 x 1 的小块。
 *
 * 给你整数 m ，n 和两个数组：
 *
 * horizontalCut 的大小为 m - 1 ，其中 horizontalCut[i] 表示沿着水平线 i 切蛋糕的开销。
 * verticalCut 的大小为 n - 1 ，其中 verticalCut[j] 表示沿着垂直线 j 切蛋糕的开销。
 * 一次操作中，你可以选择任意不是 1 x 1 大小的矩形蛋糕并执行以下操作之一：
 *
 * 沿着水平线 i 切开蛋糕，开销为 horizontalCut[i] 。
 * 沿着垂直线 j 切开蛋糕，开销为 verticalCut[j] 。
 * 每次操作后，这块蛋糕都被切成两个独立的小蛋糕。
 *
 * 每次操作的开销都为最开始对应切割线的开销，并且不会改变。
 *
 * 请你返回将蛋糕全部切成 1 x 1 的蛋糕块的 最小 总开销。
 */
public class MinimumCostForCutCake {
    public long minimumCost(int m, int n, int[] horizontalCut, int[] verticalCut) {
        Arrays.sort(horizontalCut);
        Arrays.sort(verticalCut);
        int h=horizontalCut.length-1,v=verticalCut.length-1;
        long res = 0;
        long hc = 1, vc=1;
        while (h>=0||v>=0) {
            if (v<0||(h>=0 && horizontalCut[h] > verticalCut[v])) {
                res += horizontalCut[h--]*(vc);
                hc++;
            } else {

                res += verticalCut[v--]*(hc);
                vc++;
            }
        }
        return res;
    }
}
