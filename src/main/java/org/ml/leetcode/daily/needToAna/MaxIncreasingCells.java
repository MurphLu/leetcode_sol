package org.ml.leetcode.daily.needToAna;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * 给你一个下标从 1 开始、大小为 m x n 的整数矩阵 mat，你可以选择任一单元格作为 起始单元格 。
 *
 * 从起始单元格出发，你可以移动到 同一行或同一列 中的任何其他单元格，但前提是目标单元格的值 严格大于 当前单元格的值。
 *
 * 你可以多次重复这一过程，从一个单元格移动到另一个单元格，直到无法再进行任何移动。
 *
 * 请你找出从某个单元开始访问矩阵所能访问的 单元格的最大数量 。
 *
 * 返回一个表示可访问单元格最大数量的整数。
 */
public class MaxIncreasingCells {
    public static void main(String[] args) {
        int[][] ints = {
                new int[]{3, 1,6},
                new int[]{-9,5,7}};
        new MaxIncreasingCells().maxIncreasingCells(ints);
    }

    public int maxIncreasingCells(int[][] mat) {
        // 将矩阵中 key: val, val: List<int[]{col, row}> 加入红黑树
        var g = new TreeMap<Integer, List<int[]>>();
        int m = mat.length, n = mat[0].length;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                g.computeIfAbsent(mat[i][j], k -> new ArrayList<>()).add(new int[]{i, j});
            }
        }
        int ans = 0;
        int[] rowMax = new int[m], colMax = new int[n];
        for(var pos : g.values()){
            var mx = new int[pos.size()];
            // 遍历红黑树中的坐标，并计算每个数所在的坐标位置能到达的最大值
            for(int i = 0; i < pos.size(); i++){
                // 当前坐标所在 col，和 row 分别能得到的最大值是多少 +1 为当前点
                mx[i] = Math.max(rowMax[pos.get(i)[0]], colMax[pos.get(i)[1]]) + 1;
                // 结果为 mx 中最大的一个值
                ans = Math.max(ans, mx[i]);
            }
            for(int k = 0; k < pos.size(); k++){
                // 遍历当前值的所有点位，并更新到当前数字位置每个点所在的行与列分别能够到达的最大值
                int i = pos.get(k)[0], j = pos.get(k)[1];
                rowMax[i] = Math.max(rowMax[i], mx[k]);
                colMax[j] = Math.max(colMax[j], mx[k]);
            }
        }
        return ans;
    }
}
