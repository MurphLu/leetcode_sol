package org.ml.leetcode.daily;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 给你一个下标从 0 开始大小为 m x n 的二进制矩阵 grid 。
 *
 * 从原矩阵中选出若干行构成一个行的 非空 子集，如果子集中任何一列的和至多为子集大小的一半，那么我们称这个子集是 好子集。
 *
 * 更正式的，如果选出来的行子集大小（即行的数量）为 k，那么每一列的和至多为 floor(k / 2) 。
 *
 * 请你返回一个整数数组，它包含好子集的行下标，请你将子集中的元素 升序 返回。
 *
 * 如果有多个好子集，你可以返回任意一个。如果没有好子集，请你返回一个空数组。
 *
 * 一个矩阵 grid 的行 子集 ，是删除 grid 中某些（也可能不删除）行后，剩余行构成的元素集合。
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m <= 10^4
 * 1 <= n <= 5
 * grid[i][j] 要么是 0 ，要么是 1 。
 *
 *
 * <img src="GoodSubsetofBinaryMatrix.png" />
 */

public class GoodSubsetofBinaryMatrix {
    public static void main(String[] args) {
        int[][] grid= new int[][]{
                new int[]{1,1,1,1},
                new int[]{1,1,1,1}
//                new int[]{1,1,1,1}
        };
        new GoodSubsetofBinaryMatrix().goodSubsetofBinaryMatrix(grid);
    }
    public List<Integer> goodSubsetofBinaryMatrix(int[][] grid) {
        List<Integer> ans = new ArrayList<Integer>();
        Map<Integer, Integer> mp = new HashMap<Integer, Integer>();
        int m = grid.length;
        int n = grid[0].length;
        // 将每一行拼成一个二进制数字
        for (int j = 0; j < m; j++) {
            int st = 0;
            for (int i = 0; i < n; i++) {
                st |= (grid[j][i] << i);
            }
            mp.put(st, j);
        }
        // 如果有 0 存在，那么直接返回 0 的行
        if (mp.containsKey(0)) {
            ans.add(mp.get(0));
            return ans;
        }
        // 如果没有 0 存在，那么当 两个数 & 不为 0时，则没有满足的两行的组合。
        for (Map.Entry<Integer, Integer> entry1 : mp.entrySet()) {
            int x = entry1.getKey(), i = entry1.getValue();
            for (Map.Entry<Integer, Integer> entry2 : mp.entrySet()) {
                int y = entry2.getKey(), j = entry2.getValue();
                if ((x & y) == 0) {
                    List<Integer> list = new ArrayList<Integer>();
                    list.add(Math.min(i, j));
                    list.add(Math.max(i, j));
                    return list;
                }
            }
        }
        return ans;
    }

}
