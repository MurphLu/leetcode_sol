package org.ml.leetcode;

/**
 * 给定三个字符串 s1、s2、s3，请你帮忙验证 s3 是否是由 s1 和 s2 交错 组成的。
 *
 * 两个字符串 s 和 t 交错 的定义与过程如下，其中每个字符串都会被分割成若干 非空 子字符串：
 *
 * s = s1 + s2 + ... + sn
 * t = t1 + t2 + ... + tm
 * |n - m| <= 1
 * 交错 是 s1 + t1 + s2 + t2 + s3 + t3 + ... 或者 t1 + s1 + t2 + s2 + t3 + s3 + ...
 * 注意：a + b 意味着字符串 a 和 b 连接。
 *
 * 输入：s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
 * 输出：true
 * 示例 2：
 *
 * 输入：s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
 * 输出：false
 * 示例 3：
 *
 * 输入：s1 = "", s2 = "", s3 = ""
 * 输出：true
 *
 */
public class IsInterleave {
    public static void main(String[] args) {
        boolean interleave = new IsInterleave().isInterleave("aabcc", "dbbca", "aadbbcbcac");
        System.out.println(interleave);
    }
    public boolean isInterleave(String s1, String s2, String s3) {
        return process(s1, s2, s3, new Boolean[s1.length()+1][s2.length()+1]);
    }

    private boolean process(String s1, String s2, String s3, Boolean[][] dp) {
        if (s1.isEmpty() && s2.isEmpty() && s3.isEmpty()) {
            return true;
        }
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }
        char a = s1.isEmpty() ? '?' : s1.charAt(0);
        char b = s2.isEmpty() ? '?' : s2.charAt(0);
        char c = s3.charAt(0);
        if (dp[s1.length()][s2.length()] != null) {
            return dp[s1.length()][s2.length()];
        }
        boolean res = (a == c && process(s1.substring(1), s2, s3.substring(1), dp)) || (b == c && process(s1, s2.substring(1), s3.substring(1), dp));
        dp[s1.length()][s2.length()] = res;
        return res;
    }
}
