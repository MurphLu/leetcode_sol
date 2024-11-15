package org.ml.leetcode.daily;

/**
 * 给你一个 m x n 的二进制矩阵 grid 。
 *
 * 如果矩阵中一行或者一列从前往后与从后往前读是一样的，那么我们称这一行或者这一列是 回文 的。
 *
 * 你可以将 grid 中任意格子的值 翻转 ，也就是将格子里的值从 0 变成 1 ，或者从 1 变成 0 。
 *
 * 请你返回 最少 翻转次数，使得矩阵 要么 所有行是 回文的 ，要么所有列是 回文的 。
 */
public class MinFlips {
    public int minFlips(int[][] grid) {
        int cnt1 = 0;
        for(int[] arr: grid) {
            int l = 0;
            int r = arr.length - 1;
            while (l < r) {
                if (arr[l] != arr[r]) {
                    cnt1++;
                }
                l++;
                r--;
            }
        }
        int cnt2 = 0;
        int height = grid.length;
        int length = grid[0].length;
        for (int i = 0; i < length; i++) {
            int t = 0;
            int b = height - 1;
            while (t < b) {
                if (grid[t][i] != grid[b][i]) {
                    cnt2++;
                }
                t++;
                b--;
            }
        }
        return Math.min(cnt1, cnt2);
    }
}
