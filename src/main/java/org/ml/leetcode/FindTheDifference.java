package org.ml.leetcode;

public class FindTheDifference {
    public char findTheDifference(String s, String t) {
        int[] ca = new int[26];
        for(char c: s.toCharArray()) {
            ca[c-'a']--;
        }
        for(char c: t.toCharArray()) {
            ca[c-'a']++;
        }
        for (int i = 0; i < ca.length; i++) {
            if (ca[i] == 1) {
                return (char)('a'+i);
            }
        }
        return 'a';
    }
}
