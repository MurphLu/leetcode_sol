package org.ml.leetcode.daily;

import java.util.HashMap;
import java.util.Map;

/**
 * 给你 k 枚相同的鸡蛋，并可以使用一栋从第 1 层到第 n 层共有 n 层楼的建筑。
 *
 * 已知存在楼层 f ，满足 0 <= f <= n ，任何从 高于 f 的楼层落下的鸡蛋都会碎，从 f 楼层或比它低的楼层落下的鸡蛋都不会破。
 *
 * 每次操作，你可以取一枚没有碎的鸡蛋并把它从任一楼层 x 扔下（满足 1 <= x <= n）。
 * 如果鸡蛋碎了，你就不能再次使用它。如果某枚鸡蛋扔下后没有摔碎，则可以在之后的操作中 重复使用 这枚鸡蛋。
 *
 * 请你计算并返回要确定 f 确切的值 的 最小操作次数 是多少？
 */
public class SuperEggDrop {
    public static void main(String[] args) {
        int i = new SuperEggDrop().superEggDrop(3, 14);
        System.out.println(i);
    }

    Map<Integer, Integer> cache = new HashMap<>();
    public int superEggDrop(int K, int N) {
        if (N == 0) return 0; else if (K == 1) return N; Integer key = N * 1000 + K; // K <= 100
        if (cache.containsKey(key)) return cache.get(key);
        int low = 1, high = N;
        while (low + 1 < high) {
            int middle = (low + high) / 2;
            int lowVal = superEggDrop(K - 1, middle - 1);
            int highVal = superEggDrop(K, N - middle);
            if (lowVal < highVal) low = middle;
            else if (lowVal > highVal) high = middle;
            else low = high = middle;
        }
        int minimum = 1 + Math.min(
                Math.max(superEggDrop(K - 1, low - 1), superEggDrop(K, N - low)),
                Math.max(superEggDrop(K - 1, high - 1), superEggDrop(K, N - high)) );
        cache.put(key, minimum);
        return cache.get(key);
    }


    public int superEggDrop2(int k, int n) {
        int[][] dp = new int[k + 1][n + 1];
        for (int m = 1; m <= n; m++) {
            dp[0][m] = 0; // zero egg
            for (int i = 1; i <= k; i++) {
                dp[i][m] = dp[i][m - 1] + dp[i - 1][m - 1] + 1;
                if (dp[i][m] >= n) {
                    return m;
                }
            }
        }
        return n;
    }
}
