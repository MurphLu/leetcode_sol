package org.ml.leetcode;

/**
 * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 *
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
 *
 * 此外，你可以假设该网格的四条边均被水包围。
 */
public class NumIslands {
    public int numIslands(char[][] grid) {
        int cnt = 0;
        int m = grid.length;
        int n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    cnt ++;
                    updateIsland(grid, i, j);
                }
            }
        }
        return cnt;
    }

    private void updateIsland(char[][] grid, int x, int y) {
        int m = grid.length;
        int n = grid[0].length;
        if (x < 0 || x >= m || y < 0 || y >= n) {
            return;
        }
        if (grid[x][y] == '1') {
            grid[x][y] = '0';
            updateIsland(grid, x + 1, y);
            updateIsland(grid, x - 1, y);
            updateIsland(grid, x, y + 1);
            updateIsland(grid, x, y - 1);
        }
    }
}
