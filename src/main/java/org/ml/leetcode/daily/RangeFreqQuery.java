package org.ml.leetcode.daily;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 子数组中一个值的 频率 指的是这个子数组中这个值的出现次数。
 *
 * 请你实现 RangeFreqQuery 类：
 *
 * RangeFreqQuery(int[] arr) 用下标从 0 开始的整数数组 arr 构造一个类的实例。
 * int query(int left, int right, int value) 返回子数组 arr[left...right] 中 value 的 频率 。
 * 一个 子数组 指的是数组中一段连续的元素。arr[left...right] 指的是 nums 中包含下标 left 和 right 在内 的中间一段连续元素。
 *
 * 示例 1：
 *
 * 输入：
 * ["RangeFreqQuery", "query", "query"]
 * [[[12, 33, 4, 56, 22, 2, 34, 33, 22, 12, 34, 56]], [1, 2, 4], [0, 11, 33]]
 * 输出：
 * [null, 1, 2]
 *
 * 解释：
 * RangeFreqQuery rangeFreqQuery = new RangeFreqQuery([12, 33, 4, 56, 22, 2, 34, 33, 22, 12, 34, 56]);
 * rangeFreqQuery.query(1, 2, 4); // 返回 1 。4 在子数组 [33, 4] 中出现 1 次。
 * rangeFreqQuery.query(0, 11, 33); // 返回 2 。33 在整个子数组中出现 2 次。
 * 提示：
 *
 * 1 <= arr.length <= 10^5
 * 1 <= arr[i], value <= 10^4
 * 0 <= left <= right < arr.length
 * 调用 query 不超过 10^5 次。
 */
public class RangeFreqQuery {
    Map<Integer, List<Integer>> map = new HashMap<>();

    public RangeFreqQuery(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            List<Integer> list = map.getOrDefault(arr[i], new ArrayList<>());
            list.add(i);
            map.put(arr[i], list);
        }
    }

    public int query(int left, int right, int value) {
        List<Integer> list = map.get(value);
        if(list == null || list.isEmpty() || right < list.get(0) || left > list.get(list.size()-1)) {
            return 0;
        }
        int leftIdx = leftIdx(list, 0, list.size(), left);
        int rightIdx = rightIdx(list, 0, list.size(), right);
        return rightIdx-leftIdx;
    }

    private int leftIdx(List<Integer> list, int left, int right, int num) {
        if (left == right) {
            return left;
        }
        int mid = (right-left)/2 + left;
        int idx = list.get(mid);
        if (idx < num) {
            return leftIdx(list, mid+1, right, num);
        } else {
            return leftIdx(list, left, mid, num);
        }
    }
    private int rightIdx(List<Integer> list, int left, int right, int num) {
        if (left == right) {
            return left;
        }
        int mid = (right-left)/2 + left;
        int idx = list.get(mid);
        if (idx > num) {
            return rightIdx(list, left, mid, num);
        } else {
            return rightIdx(list, mid+1, right, num);
        }
    }
}
