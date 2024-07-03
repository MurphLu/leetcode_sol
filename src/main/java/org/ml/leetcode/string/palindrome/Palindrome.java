package org.ml.leetcode.string.palindrome;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Palindrome {
    /**
     * 计算字符串中一共有多少回文子串
     * @param s 原始字符串
     * @return 返回回文子串数量
     */
    public int countSubstrings(String s) {
        char[] ca = s.toCharArray();
        int idx = 0;
        int cnt = 0;
        while (idx < ca.length) {
            int start = idx;
            int end = idx;
            while (start>=0 && end < ca.length && ca[start] == ca[end]) {
                start--;
                end++;
                cnt++;
            }
            start = idx;
            end = idx+1;
            while (start>=0 && end < ca.length && ca[start] == ca[end]) {
                start--;
                end++;
                cnt++;
            }
            idx++;
        }
        return cnt;
    }

    public static void main(String[] args) {
        new Palindrome().partition("google");
    }

    public String[][] partition(String s) {
        List<List<String>> resArr = processPartition(s);
        String[][] res = new String[resArr.size()][];
        for (int i = 0; i < resArr.size(); i++) {
            res[i] = resArr.get(i).toArray(new String[0]);
        }

        return res;
    }

    private List<List<String>> processPartition(String s) {
        List<List<String>> res = new ArrayList<>();
        if (s.isEmpty()) {
            List<String> r = new ArrayList<String>();
            res.add(r);
            return res;
        }
        for (int i = 1; i <= s.length(); i++) {
            String sub = s.substring(0,i);
            if(isPalindrome(sub)){
                List<List<String>> lists = processPartition(s.substring(i));
                for (List<String> list: lists) {
                   list.add(0, sub);
                }
                res.addAll(lists);
            }
        }
        return res;
    }

    private boolean isPalindrome(String s) {
        char[] ca = s.toCharArray();
        int start = 0;
        int end = s.length()-1;
        while (start < end) {
            if(ca[start] != ca[end]) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }



}
