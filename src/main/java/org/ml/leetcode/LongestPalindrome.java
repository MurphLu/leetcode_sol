package org.ml.leetcode;

import java.util.Arrays;

/**
 * 最长回文字串
 */
public class LongestPalindrome {
    public String longestPalindrome(String s) {
        int maxLength=0;
        int start=0;
        int end=1;
        int idx = 0;
        char[] cs = s.toCharArray();
        while (idx<cs.length) {
            int[] max1 = maxL(idx, idx, cs);
            if (maxLength < max1[1] - max1[0]) {
                maxLength = max1[1] - max1[0];
                start = max1[0];
                end = max1[1];
            }
            int[] max2 = maxL(idx, idx+1, cs);
            if (maxLength < max2[1] - max2[0]) {
                maxLength = max2[1] - max2[0];
                start = max2[0];
                end = max2[1];
            }
            idx++;
        }
        return s.substring(start, end);
    }

    private int[] maxL(int left, int right, char[] cs) {
        while (left>=0 && right < cs.length && cs[left] == cs[right]) {
            left--;
            right++;
        }
        return new int[]{left+1, right};
    }
}
