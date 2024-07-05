package org.ml.leetcode.daily;

import java.util.LinkedList;

/**
 * 给你一个下标从 0 开始、大小为 m x n 的整数矩阵 matrix ，新建一个下标从 0 开始、名为 answer 的矩阵。
 * 使 answer 与 matrix 相等，接着将其中每个值为 -1 的元素替换为所在列的 最大 元素。
 *
 * 返回矩阵 answer 。
 */
public class ModifiedMatrix {
    public int[][] modifiedMatrix(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] res = new int[m][n];
        LinkedList<Integer> nagIdx = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            int max = -2;
            for (int j = 0; j < m; j++) {
                int num = matrix[j][i];
                res[j][i] = num;
                if (num == -1) {
                    nagIdx.add(j);
                }
                max = Math.max(num, max);
            }
            while (!nagIdx.isEmpty()) {
                int idx = nagIdx.removeFirst();
                res[idx][i] = max;
            }
        }
        return res;
    }
}
