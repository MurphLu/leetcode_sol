package org.ml.leetcode.daily;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 给你两个下标从 0 开始的整数数组 nums1 和 nums2 ，它们分别含有 n 和 m 个元素。
 *
 * 请你计算以下两个数值：
 *
 * 统计 0 <= i < n 中的下标 i ，满足 nums1[i] 在 nums2 中 至少 出现了一次。
 * 统计 0 <= i < m 中的下标 i ，满足 nums2[i] 在 nums1 中 至少 出现了一次。
 * 请你返回一个长度为 2 的整数数组 answer ，按顺序 分别为以上两个数值。
 */
public class FindIntersectionValues {
    public int[] findIntersectionValues(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int num: nums1) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        int cnt1 = 0;
        Set<Integer> set = new HashSet<>();
        int cnt2 = 0;
        for(int num: nums2) {
            if(map.containsKey(num)) {
                if (set.add(num)){
                    cnt1+=map.get(num);
                }
                cnt2+=1;
            }
        }
        return new int[]{cnt1, cnt2};
    }
}
