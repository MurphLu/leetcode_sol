package org.ml.leetcode;

import java.util.*;

public class IsScramble {
    Map<String, Boolean> map = new HashMap<>();

    public static void main(String[] args) {
        boolean scramble = new IsScramble().isScramble("eebaacbcbcadaaedceaaacadccd", "eadcaacabaddaceacbceaabeccd");
        System.out.println(scramble);
    }
    // 递归 + 记忆化搜索
    public boolean isScramble(String s1, String s2) {
        if (map.containsKey(s1+s2)) {
            return map.get(s1+s2);
        }
        int n = s1.length();
        if (s1.equals(s2)) {
            map.put(s1+s2, true);
            return true;
        }
        Map<Character, Integer> charMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            char a = s1.charAt(i);
            char b = s2.charAt(i);
            charMap.put(a, charMap.getOrDefault(a, 0) + 1);
            charMap.put(b, charMap.getOrDefault(b, 0) - 1);
        }
        for (int v: charMap.values()) {
            if (v != 0) {
                map.put(s1+s2, false);
                return false;
            }
        }
        if (n == 3) {
            return true;
        }
        for (int i = 1; i < s1.length(); i++) {
            String s1Sb1 = s1.substring(0, i);
            String s1Sb2 = s1.substring(i);

            if ((isScramble(s1Sb1, s2.substring(0, i)) && isScramble(s1Sb2, s2.substring(i)))
                    || (isScramble(s1Sb1, s2.substring(s2.length() - i)) && isScramble(s1Sb2, s2.substring(0, s2.length() - i)))) {
                map.put(s1+s2, true);
                return true;
            }
        }
        map.put(s1+s2, false);
        return false;
    }

    // ？？？？？
    public boolean isScramble2(String s1, String s2) {
        int length = s1.length();
        boolean[][][] dp = new boolean[length][length][length + 1];
        //i和j分别是s1和s2开始计算的位置，k是计算的字符串长度
        for (int k = 1; k <= length; ++k) {
            int end = length - k;
            for (int i = 0; i <= end; ++i)
                for (int j = 0; j <= end; ++j) {
                    //如果比较的长度是1，也就是一个字符，直接判断他们是否相等即可
                    if (k == 1) {
                        dp[i][j][k] = s1.charAt(i) == s2.charAt(j);
                        continue;
                    }
                    for (int m = 1; m < k; ++m) {
                        //s1左边--->s2左边 s1右边--->s2右边
                        boolean leftToLeft = dp[i][j][m] && dp[i + m][j + m][k - m];
                        //s1左边--->s2右边 s1右边--->s2左边
                        boolean leftToRight = dp[i][j + k - m][m] && dp[i + m][j][k - m];
                        //扰乱字符串，上面只要有一个匹配即可
                        dp[i][j][k] = leftToLeft || leftToRight;
                        if (dp[i][j][k]) break;
                    }
                }
        }
        return dp[0][0][length];
    }

}
