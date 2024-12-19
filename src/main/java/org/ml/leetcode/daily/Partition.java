package org.ml.leetcode.daily;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是 回文串 。返回 s 所有可能的分割方案。
 *
 * 示例 1：
 *
 * 输入：s = "aab"
 * 输出：[["a","a","b"],["aa","b"]]
 * 示例 2：
 *
 * 输入：s = "a"
 * 输出：[["a"]]
 */
public class Partition {
    public static void main(String[] args) {
        List<List<String>> aab = new Partition().partition("aab");
        System.out.println(aab);
    }
    public List<List<String>> partition(String s) {
        if (s.isEmpty()) {
            return new ArrayList<>();
        }
        List<List<String>> res = new ArrayList<List<String>>();
        for (int i = 1; i <= s.length(); i++) {
            String sub = s.substring(0, i);
            if (isPa(sub)) {
                List<List<String>> parts = partition(s.substring(i));
                if (parts.isEmpty()) {
                    List<String> list = new ArrayList<>();
                    list.add(sub);
                    parts.add(list);
                } else {
                    for (List<String> part: parts) {
                        part.add(0,sub);
                    }
                }

                res.addAll(parts);
            }
        }
        return res;
    }

    private boolean isPa(String s) {
        if (s.length() == 1) {
            return true;
        }
        int idx = 0;
        while (idx < s.length() / 2) {
            if (s.charAt(idx) != s.charAt(s.length() - 1 - idx)) {
                return false;
            }
            idx++;
        }
        return true;
    }
}
