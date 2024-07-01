package org.ml.leetcode;

import java.util.HashSet;
import java.util.Set;

public class IsHappy {
    public boolean isHappy(int n) {
        Set<Integer> set = new HashSet<>();
        set.add(n);
        for(;;) {
            int newVal = 0;
            while (n != 0) {
                int t = n % 10;
                newVal += t*t;
                n /= 10;
            }

            if (newVal == 1) {
                return true;
            }
            if(!set.add(newVal)){
                return false;
            }
            n = newVal;
        }

    }
}
