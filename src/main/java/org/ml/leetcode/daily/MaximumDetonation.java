package org.ml.leetcode.daily;

import java.util.*;

/**
 * 给你一个炸弹列表。一个炸弹的 爆炸范围 定义为以炸弹为圆心的一个圆。
 *
 * 炸弹用一个下标从 0 开始的二维整数数组 bombs 表示，其中 bombs[i] = [xi, yi, ri] 。xi 和 yi 表示第 i 个炸弹的 X 和 Y 坐标，ri 表示爆炸范围的 半径 。
 *
 * 你需要选择引爆 一个 炸弹。当这个炸弹被引爆时，所有 在它爆炸范围内的炸弹都会被引爆，这些炸弹会进一步将它们爆炸范围内的其他炸弹引爆。
 *
 * 给你数组 bombs ，请你返回在引爆 一个 炸弹的前提下，最多 能引爆的炸弹数目。
 *
 *
 * 1 <= bombs.length <= 100
 * bombs[i].length == 3
 * 1 <= xi, yi, ri <= 10^5
 */
public class MaximumDetonation {
    public static void main(String[] args) {
        new MaximumDetonation().maximumDetonation(
                new int[][]{
                        new int[]{1,1,100000},
                        new int[]{100000,100000,1}
                }
        );
    }
    public int maximumDetonation(int[][] bombs) {
        int n = bombs.length;
        List<Integer>[] contains = new List[n];
        for (int i = 0; i < n; i++) {
            contains[i] = new ArrayList<>();
        }
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                int[] pointI = bombs[i];
                int[] pointJ = bombs[j];
                long x = Math.abs(pointI[0] - pointJ[0]);
                long y = Math.abs(pointI[1] - pointJ[1]);
                long res = x*x + y*y;
                if((long)pointI[2] * pointI[2] >= res ) {
                    contains[i].add(j);
                }
                if ((long)pointJ[2] * pointJ[2] >= res) {
                    contains[j].add(i);
                }
            }
        }
//        System.out.println(Arrays.toString(contains));
        int max = 0;
        for (int i = 0; i < n; i++) {
            Set<Integer> set = new HashSet<>();
            process(contains, i, set);
            max = Math.max(max, set.size());
        }
        return max;
    }

    private void process(List<Integer>[] contains, int idx, Set<Integer> set){
        if (set.contains(idx)) {
            return;
        }
        set.add(idx);
        List<Integer> nexts = contains[idx];
        for(int i: nexts) {
            process(contains, i, set);
        }
    }

}
