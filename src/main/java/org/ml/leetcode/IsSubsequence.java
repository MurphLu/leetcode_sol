package org.ml.leetcode;

/**
 * 给定字符串 s 和 t ，判断 s 是否为 t 的子序列。
 *
 * 字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。（例如，"ace"是"abcde"的一个子序列，而"aec"不是）。
 *
 *
 * 如果有大量输入的 S，称作 S1, S2, ... , Sk 其中 k >= 10亿，你需要依次检查它们是否为 T 的子序列。在这种情况下，你会怎样改变代码？
 * 前缀树？？？？
 * 比如 t[0] 后边可以出现哪些字符,然后再根据每个字符出现的位置来构建之后可以出现的字符，这样，输入的 s 从根开始遍历，只需要在树上遍历一遍 s 即可得到结果
 */
public class IsSubsequence {
    public boolean isSubsequence(String s, String t) {
        int sIdx = 0;
        int tIdx = 0;
        while (sIdx < s.length() && tIdx < t.length()) {
            char sa = s.charAt(sIdx);
            char ta = t.charAt(tIdx);
            if (sa == ta) {
                sIdx++;
            }
            tIdx++;
        }
        return sIdx == s.length();
    }
}
