package org.ml.leetcode;

// 2 的幂
public class IsPowerOfTwo {
    public boolean isPowerOfTwo(int n) {

        return n >= 0 && Integer.bitCount(n) == 1;
    }
}
