package org.ml.fromInterview;

import java.util.Arrays;

/**
 * 有一组画完成时间的数组，k 个画家，每个画家需负责画相邻的画
 * 求完成所有画最少需要多长时间
 */
public class PaintPic {

    public static int paint(int[] paint, int k) {

        int min = 0;
        int total = 0;
        for (int j : paint) {
            total += j;
        }

        int ans = 0;
        while (min<total) {
            int limit = (min + total) / 2;
            int need = needPainter(paint, limit);
            if(need < k) {
                ans = limit;
                total = limit-1;
            } else {
                min = limit+1;
            }
        }

        return ans;
    }

    private static int needPainter(int[] paint, int limit) {
        int cost = 0;
        int cnt = 1;
        for (int j : paint) {
            if (j > limit) {
                return Integer.MAX_VALUE;
            }
            if (cost + j > limit) {
                cost = j;
                cnt++;
            } else {
                cost += j;
            }
        }
        return cnt;
    }

}
