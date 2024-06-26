package org.ml.leetcode;

import java.util.Arrays;

/**
 * 一条包含字母 A-Z 的消息通过以下映射进行了 编码 ：
 *
 * 'A' -> "1"
 * 'B' -> "2"
 * ...
 * 'Z' -> "26"
 * 要 解码 已编码的消息，所有数字必须基于上述映射的方法，反向映射回字母（可能有多种方法）。例如，"11106" 可以映射为：
 *
 * "AAJF" ，将消息分组为 (1 1 10 6)
 * "KJF" ，将消息分组为 (11 10 6)
 * 注意，消息不能分组为 (1 11 06) ，因为 "06" 不能映射为 "F" ，这是由于 "6" 和 "06" 在映射中并不等价。
 *
 * 给你一个只含数字的 非空 字符串 s ，请计算并返回 解码 方法的 总数 。
 *
 * 题目数据保证答案肯定是一个 32 位 的整数。
 */
public class NumDecodings {
    public static void main(String[] args) {
        new NumDecodings().numDecodings("111111111111111111111111111111111111111111111");
    }
    public int numDecodings(String s) {
        if (s.isEmpty()) {
            return 0;
        }
        int[] dp = new int[s.length()+1];
        Arrays.fill(dp, -1);
        return process(s, dp);
    }
    private int process(String s, int[] dp) {
        if (dp[s.length()] != -1) {
            return dp[s.length()];
        }
        if (s.isEmpty()) {
            return 1;
        }
        if (s.charAt(0) == '0') {
            return 0;
        }

        int count = 0;
        count += process(s.substring(1), dp);
        if (s.length() >= 2 && Integer.parseInt(s.substring(0 ,2)) <= 26) {
            count += process(s.substring(2), dp);
        }
        dp[s.length()] = count;
        return count;
    }

}
