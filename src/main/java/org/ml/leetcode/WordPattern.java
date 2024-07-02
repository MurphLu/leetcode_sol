package org.ml.leetcode;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

// pattern 中的字符需要与 s 种的单词一一对应
public class WordPattern {
    public boolean wordPattern(String pattern, String s) {
        String[] sp = s.split(" ");
        if(pattern.length() != sp.length) {
            return false;
        }
        Map<Character, String> map = new HashMap<>();
        Map<String, Character> map2 = new HashMap<>();
        for(int i = 0; i < sp.length; i++ ) {
            char c = pattern.charAt(i);
            String st = sp[i];
            if(map.containsKey(c)|| map2.containsKey(st)) {
                if(!map2.containsKey(st) || !map.containsKey(c)) {
                    return false;
                }
                if(!map.get(c).equals(st) || c != map2.get(st)) {
                    return false;
                }
            } else {
                map.put(c, st);
                map2.put(st, c);
            }
        }
        return true;
    }
}
