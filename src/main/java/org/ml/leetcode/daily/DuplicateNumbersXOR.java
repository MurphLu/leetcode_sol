package org.ml.leetcode.daily;

import java.util.HashMap;
import java.util.Map;

public class DuplicateNumbersXOR {
    public int duplicateNumbersXOR(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int val = 0;
        for(int n: nums) {
            if (map.containsKey(n)){
                val ^= n;
            }
            map.put(n, 0);
        }
        return val;
    }
}
