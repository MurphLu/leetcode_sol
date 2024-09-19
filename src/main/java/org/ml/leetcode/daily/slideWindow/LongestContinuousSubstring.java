package org.ml.leetcode.daily.slideWindow;

/**
 * 字母序连续字符串 是由字母表中连续字母组成的字符串。换句话说，字符串 "abcdefghijklmnopqrstuvwxyz" 的任意子字符串都是 字母序连续字符串 。
 *
 * 例如，"abc" 是一个字母序连续字符串，而 "acb" 和 "za" 不是。
 * 给你一个仅由小写英文字母组成的字符串 s ，返回其 最长 的 字母序连续子字符串 的长度。
 *
 * 示例 1：
 *
 * 输入：s = "abacaba"
 * 输出：2
 * 解释：共有 4 个不同的字母序连续子字符串 "a"、"b"、"c" 和 "ab" 。
 * "ab" 是最长的字母序连续子字符串。
 * 示例 2：
 *
 * 输入：s = "abcde"
 * 输出：5
 * 解释："abcde" 是最长的字母序连续子字符串。
 * 提示：
 *
 * 1 <= s.length <= 105
 * s 由小写英文字母组成
 */
public class LongestContinuousSubstring {
    public static void main(String[] args) {
        new LongestContinuousSubstring().longestContinuousSubstring("abcde");
    }
    public int longestContinuousSubstring(String s) {
        char[] chars = s.toCharArray();
        int start = 0;
        int end = 0;
        int maxLength = 1;
        while (end < chars.length-1) {
            if (chars[end]==chars[end+1]-1){
                end++;
            } else {
                maxLength = Math.max(maxLength, end - start + 1);
                end+=1;
                start = end;
            }
        }
        return Math.max(maxLength, end - start + 1);
    }
}
