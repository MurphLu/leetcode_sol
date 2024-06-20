package org.ml.leetcode.daily;

import java.util.HashMap;
import java.util.Map;

/**
 * 给你一个下标从 0 开始的整数数组 nums 。
 * 如果下标对 i、j 满足 0 ≤ i < j < nums.length ，
 * 如果 nums[i] 的 第一个数字 和 nums[j] 的 最后一个数字 互质 ，
 * 则认为 nums[i] 和 nums[j] 是一组 美丽下标对
 */
public class CountBeautifulPairs {
    public int countBeautifulPairs(int[] nums) {
        int cnt = 0;
        for (int i = 0; i < nums.length; i++) {
            int a = nums[i];
            while (a >= 10) {
                a /= 10;
            }
            for (int j = i+1; j < nums.length; j++) {
                int b = nums[j] % 10;
                // 如果两个数有一个是 1，那么则符合
                // 如果都不是 1，且 a == b，那么不符合，最大公约数大于 1
                // 剩下就看他们是不是能够被 2，3，4 整除，都能被整除则不符合，否则符合
                if((a == 1 || b == 1) || a != b && (a%2!=0 || b%2 != 0) && (a%3!=0 || b%3 != 0) && (a%4!=0 || b%4 != 0)) cnt++;
            }
        }
        return cnt;
    }

    // 如果 nums 长的话，该方法时间复杂度会更低
    public int countBeautifulPairs2(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int n = nums.length - 1;
        int cnt = 0;
        for (int i = n; i >=0; i--) {
            if (i < n) {
                int a = nums[i];
                while (a >= 10) {
                    a /= 10;
                }
                for(Map.Entry<Integer, Integer> entry: map.entrySet()) {
                    int b = entry.getKey();
                    if((a == 1 || b == 1) || a != b && (a%2!=0 || b%2 != 0) && (a%3!=0 || b%3 != 0) && (a%4!=0 || b%4 != 0)) cnt+=entry.getValue();
                }
            }
            int b = nums[i] % 10;
            map.put(b, map.getOrDefault(b, 0) + 1);
        }
        return cnt;
    }
}
