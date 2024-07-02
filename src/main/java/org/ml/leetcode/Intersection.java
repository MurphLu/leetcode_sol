package org.ml.leetcode;

import java.util.*;

// 交集
public class Intersection {
    // 给你两个整数数组 nums1 和 nums2 ，请你以数组形式返回两数组的交集。
    // 返回结果中每个元素出现一次。可以不考虑输出结果的顺序。
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> nums1Set = new HashSet<>();
        for(int num : nums1) {
            nums1Set.add(num);
        }
        Set<Integer> nums2Set = new HashSet<>();
        for(int num : nums2) {
            nums2Set.add(num);
        }
        Set<Integer> result = new HashSet<>();
        for(int num: nums1Set) {
            if (!nums2Set.add(num)) {
                result.add(num);
            }
        }
        int[] resultArr = new int[result.size()];
        Iterator<Integer> iterator = result.iterator();
        int i = 0;
        while (iterator.hasNext()){
            resultArr[i++] = iterator.next();
        }
        return resultArr;
//        return result.toArray(new int[0]);
    }

    /**
     * 给你两个整数数组 nums1 和 nums2 ，请你以数组形式返回两数组的交集。
     * 返回结果中每个元素出现的次数，应与元素在两个数组中都出现的次数一致（如果出现次数不一致，则考虑取较小值）。可以不考虑输出结果的顺序。
     *
     * 如果给定的数组已经排好序呢？你将如何优化你的算法？ // 双指针
     * 如果 nums1 的大小比 nums2 小，哪种方法更优？ //
     * 如果 nums2 的元素存储在磁盘上，内存是有限的，并且你不能一次加载所有的元素到内存中，你该怎么办？
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersect(int[] nums1, int[] nums2) {
        int[] collect1 = new int[1001];
        int[] collect2 = new int[1001];
        for (int value : nums1) {
            collect1[value]++;
        }
        for (int k : nums2) {
            collect2[k]++;
        }
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < collect1.length; i++) {
            if (collect1[i] > 0 && collect2[i] > 0) {
                int cnt = Math.min(collect1[i], collect2[i]);
                for (int j = 0; j < cnt; j++) {
                    result.add(i);
                }
            }
        }
        int[] resultArr = new int[result.size()];
        int idx = 0;
        for (int num: result) {
            resultArr[idx++] = num;
        }
        return resultArr;
    }
}
