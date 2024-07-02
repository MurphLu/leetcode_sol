package org.ml.leetcode;

import java.util.Arrays;

public class FirstUniqChar {
    public int firstUniqChar(String s) {
        int[] idx = new int[26];
        Arrays.fill(idx, -1);
        for (int i = 0; i < s.length(); i++) {
            int c = s.charAt(i) - 'a';
            if(idx[c] ==-1 ){
                idx[c] = i;
            } else if(idx[c] >=0) {
                idx[c] = -2;
            }
        }
        int min = Integer.MAX_VALUE;
        for (int j : idx) {
            if (j >= 0) {
                min = Math.min(min, j);
            }
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }
}
