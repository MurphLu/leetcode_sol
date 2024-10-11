package org.ml.leetcode.daily;

import java.util.*;

public class NumberOfPairs {
    public static void main(String[] args) {
        new NumberOfPairs().numberOfPairs(
                new int[]{1,3,4},
                new int[]{1,3,4},
                1
        );
    }
    public long numberOfPairs(int[] nums1, int[] nums2, int k) {
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int x : nums1) {
            if (x % k != 0) {
                continue;
            }
            x /= k;
            for (int d = 1; d * d <= x; d++) { // 枚举因子
                if (x % d > 0) { continue; }
                cnt.merge(d, 1, Integer::sum); // cnt[d]++
                if (d * d < x) {
                    cnt.merge(x / d, 1, Integer::sum); // cnt[x/d]++
                }
            }
        }
        long ans = 0;
        for (int x : nums2) {
            ans += cnt.getOrDefault(x, 0);
        }
        return ans;
    }
}
