package org.ml.leetcode.daily;


/**
 * 给你一个 m x n 的矩阵 board ，由若干字符 'X' 和 'O' 组成，捕获 所有 被围绕的区域：
 *
 * 连接：一个单元格与水平或垂直方向上相邻的单元格连接。
 * 区域：连接所有 'O' 的单元格来形成一个区域。
 * 围绕：如果您可以用 'X' 单元格 连接这个区域，并且区域中没有任何单元格位于 board 边缘，则该区域被 'X' 单元格围绕。
 * 通过将输入矩阵 board 中的所有 'O' 替换为 'X' 来 捕获被围绕的区域。
 */
public class ChangeSurroundArea {
    public void solve(char[][] board) {
        int m = board.length;
        int n = board[0].length;
        int[] mb = {0, m-1};
        int[] nb = {0, n-1};
        for (int mm: mb) {
            for (int i = 0; i < n; i++) {
                if (board[mm][i] == 'O') {
                    dfs(board, mm, i);
                }
            }
        }

        for (int nn: nb) {
            for (int i = 0; i < m; i++) {
                if (board[i][nn] == 'O') {
                    dfs(board, i, nn);
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O'){
                    board[i][j] = 'X';
                } else if (board[i][j] == 'o'){
                    board[i][j] = 'O';
                }
            }
        }
    }

    private void dfs(char[][] board, int m, int n) {
        if (m >=  board.length || m < 0 || n >= board[0].length || n < 0) {
            return;
        }
        if(board[m][n] == 'O') {
            board[m][n] = 'o';
        } else {
            return;
        }
        dfs(board, m+1, n);
        dfs(board, m-1, n);
        dfs(board, m, n+1);
        dfs(board, m, n-1);
    }
}
