package org.ml.leetcode.daily;

import java.util.Arrays;

public class MaximumSubsequenceCount {
    public static void main(String[] args) {
        char[] arr = new char[100000];
        Arrays.fill(arr, 'a');
        new MaximumSubsequenceCount().maximumSubsequenceCount(new String(arr), "aa");
    }
    public long maximumSubsequenceCount(String text, String pattern) {
        char[] patternChar = pattern.toCharArray();
        char[] textChar = text.toCharArray();
        boolean isSame = patternChar[0] == patternChar[1];
        long count = 0;
        long fc = 0;
        long lc = 0;
        for (int i = textChar.length - 1; i >= 0; i--) {
            if (textChar[i] == patternChar[1]) {
                lc++;
            }
            if (textChar[i] == patternChar[0]) {
                fc++;
                count += lc;
            }
        }
        if (isSame) {
            count = (fc - 1) * fc / 2;
        }
        count += Math.max(fc, lc);
        return count;
    }
}
