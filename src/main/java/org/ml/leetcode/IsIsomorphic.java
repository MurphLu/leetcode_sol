package org.ml.leetcode;

import java.util.HashMap;
import java.util.Map;

// 字符串1 中的字符可以映射到字符串 2 种的字符
public class IsIsomorphic {
    public static void main(String[] args) {
        new IsIsomorphic().isIsomorphic("egg", "odd");
    }
    public boolean isIsomorphic(String s, String t) {
        Map<Character, Character> map1 = new HashMap<>();
        Map<Character, Character> map2 = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char a = s.charAt(i);
            char b = t.charAt(i);
            if (!map1.containsKey(a) && !map2.containsKey(b)) {
                map1.put(a,b);
                map2.put(b,a);
            } else {
                if(!map1.containsKey(a) || !map2.containsKey(b)) {
                    return false;
                }
                if(b!=map1.get(a) || a!=map2.get(b)) {
                    return false;
                }
            }
        }
        return true;
    }
}
