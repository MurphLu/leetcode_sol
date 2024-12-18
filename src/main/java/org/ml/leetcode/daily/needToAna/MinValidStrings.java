package org.ml.leetcode.daily.needToAna;

import java.util.Arrays;

/**
 * 给你一个字符串数组 words 和一个字符串 target。
 *
 * 如果字符串 x 是 words 中 任意 字符串的 前缀，则认为 x 是一个 有效 字符串。
 *
 * 现计划通过 连接 有效字符串形成 target ，请你计算并返回需要连接的 最少 字符串数量。
 * 如果无法通过这种方式形成 target，则返回 -1。
 */
public class MinValidStrings {
    public int minValidStrings(String[] words, String target) {
        int n = target.length();
        int[] back = new int[n];
        for (String word : words) {
            int[] pi = prefixFunction(word, target);
            int m = word.length();
            for (int i = 0; i < n; i++) {
                back[i] = Math.max(back[i], pi[m + 1 + i]);
            }
        }
        int[] dp = new int[n + 1];
        Arrays.fill(dp, 1, n + 1, (int) 1e9);
        for (int i = 0; i < n; i++) {
            dp[i + 1] = dp[i + 1 - back[i]] + 1;
            if (dp[i + 1] > n) {
                return -1;
            }
        }
        return dp[n];
    }

    private int[] prefixFunction(String word, String target) {
        String s = word + "#" + target;
        int n = s.length();
        int[] pi = new int[n];
        for (int i = 1; i < n; i++) {
            int j = pi[i - 1];
            while (j > 0 && s.charAt(i) != s.charAt(j)) {
                j = pi[j - 1];
            }
            if (s.charAt(i) == s.charAt(j)) {
                j++;
            }
            pi[i] = j;
        }
        return pi;
    }
}
