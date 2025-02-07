package org.ml.leetcode.daily;

/**
 * 给你一个正整数 n ，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix 。
 */
public class GenerateMatrix {
    public int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];
        left(0,0,1,n,res);
        return res;
    }

    private void left(int i, int j, int num, int n, int[][] res) {
        while (j < n && res[i][j] == 0) {
            res[i][j] = num;
            if (num == n*n) {
                return;
            }
            j++;
            num++;
        }
        down(i+1, j-1, num, n, res);
    }
    private void right(int i, int j, int num, int n, int[][] res) {
        while (j >=0 && res[i][j] == 0) {
            res[i][j] = num;
            if (num == n*n) {
                return;
            }
            j--;
            num++;
        }
        up(i-1, j+1, num, n, res);
    }
    private void down(int i, int j, int num, int n, int[][] res) {
        while (i < n && res[i][j] == 0) {
            res[i][j] = num;
            if (num == n*n) {
                return;
            }
            i++;
            num++;
        }
        right(i-1, j-1, num, n, res);
    }
    private void up(int i, int j, int num, int n, int[][] res) {
        while (i >=0 && res[i][j] == 0) {
            res[i][j] = num;
            if (num == n*n) {
                return;
            }
            i--;
            num++;
        }
        left(i+1, j+1, num, n, res);
    }
}
