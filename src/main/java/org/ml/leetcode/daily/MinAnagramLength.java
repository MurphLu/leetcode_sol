package org.ml.leetcode.daily;

import java.util.HashMap;
import java.util.Map;

/**
 * 给你一个字符串 s ，它由某个字符串 t 和若干 t 的 同位字符串 连接而成。
 *
 * 请你返回字符串 t 的 最小 可能长度。
 *
 * 同位字符串 指的是重新排列一个单词得到的另外一个字符串，原来字符串中的每个字符在新字符串中都恰好只使用一次。
 *
 * 示例 1：
 *
 * 输入：s = "abba"
 *
 * 输出：2
 *
 * 解释：
 *
 * 一个可能的字符串 t 为 "ba" 。
 *
 * 示例 2：
 *
 * 输入：s = "cdef"
 *
 * 输出：4
 *
 * 解释：
 *
 * 一个可能的字符串 t 为 "cdef" ，注意 t 可能等于 s 。
 */
public class MinAnagramLength {
    public static void main(String[] args) {
        new MinAnagramLength().minAnagramLength("aabbabab");
    }
    public int minAnagramLength(String s) {
        for (int i = 1; i < s.length(); i++) {
            boolean res = tryLength(i, s);
            if (res) {
                return i;
            }
        }
        return s.length();
    }

    private boolean tryLength(int length, String s) {
        if (s.length() % length != 0) {
            return false;
        }
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        int idx = length;
        Map<Character, Integer> tempMap = new HashMap<>(map);

        while (idx < s.length()) {
            for (int i = idx; i < idx+length; i++) {
                char c = s.charAt(i);
                if (map.get(c) == null || map.get(c) == 0) {
                    return false;
                }
                map.put(c, map.getOrDefault(c, 0) - 1);
                tempMap.put(c, tempMap.getOrDefault(c, 0) + 1);
            }
            Map<Character, Integer> temp = tempMap;
            tempMap = map;
            map = temp;
            idx = idx + length;
        }
        return true;
    }
}
