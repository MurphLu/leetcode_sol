package org.ml.leetcode.InterviewClassical150;

import java.util.*;

/**
 * 给定两个以 非递减顺序排列 的整数数组 nums1 和 nums2 , 以及一个整数 k 。
 *
 * 定义一对值 (u,v)，其中第一个元素来自 nums1，第二个元素来自 nums2 。
 *
 * 请找到和最小的 k 个数对 (u1,v1),  (u2,v2)  ...  (uk,vk) 。
 */
public class KSmallestPairs {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        //优先队列按照数组中两数和升序排列，小根堆
        PriorityQueue<int[]> pq = new PriorityQueue<>(k, (o1, o2) -> nums1[o1[0]] + nums2[o1[1]] - nums1[o2[0]] - nums2[o2[1]]);
        List<List<Integer>> ans = new ArrayList<>();
        int m = nums1.length;
        int n = nums2.length;
        // 先将 nums1 中的所有或者前 k 个 与 nums2 index0 入堆
        for (int i = 0; i < Math.min(m, k); i++) {
            pq.offer(new int[]{i, 0});
        }
        while (k-- > 0 && !pq.isEmpty()) {
            // 此时拿出来的一定是最小对
            int[] idxPair = pq.poll();
            List<Integer> list = new ArrayList<>();
            list.add(nums1[idxPair[0]]);
            list.add(nums2[idxPair[1]]);
            ans.add(list);
            // 将当前 nums1的 index 与nums2的下一个 index 组合再入堆，进行下一轮判断
            if (idxPair[1] + 1 < n) {
                pq.offer(new int[]{idxPair[0], idxPair[1] + 1});
            }
        }

        return ans;
    }
}
