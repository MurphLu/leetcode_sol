package org.ml.leetcode.daily;

import java.util.HashMap;
import java.util.Map;

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
