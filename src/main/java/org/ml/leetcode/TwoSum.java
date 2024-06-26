package org.ml.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
// 两数之和，数组中两个数组等于 target，返回 index
public class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int next = target - nums[i];
            if (map.containsKey(next)) {
                return new int[]{i, map.get(next)};
            }
            map.put(nums[i], i);
        }
//        for (Map.Entry<Integer, List<Integer>> entry: map.entrySet()) {
//            int cur = entry.getKey();
//            int val = target - cur;
//
//            if (cur != val && map.containsKey(val)) {
//                return new int[]{entry.getValue().get(0), map.get(val).get(0)};
//            }
//            if (cur == val && entry.getValue().size() > 1) {
//                return new int[]{entry.getValue().get(0), entry.getValue().get(0)};
//            }
//        }
        return new int[]{};
    }

    /**
     * 给你一个下标从 1 开始的整数数组 numbers ，该数组已按 非递减顺序排列 ，
     * 请你从数组中找出满足相加之和等于目标数 target 的两个数。如果设这两个数分别是 numbers[index1] 和 numbers[index2] ，
     * 则 1 <= index1 < index2 <= numbers.length 。
     *
     * 以长度为 2 的整数数组 [index1, index2] 的形式返回这两个整数的下标 index1 和 index2。
     *
     * 你可以假设每个输入 只对应唯一的答案 ，而且你 不可以 重复使用相同的元素。
     *
     * 你所设计的解决方案必须只使用常量级的额外空间。
     * @param numbers
     * @param target
     * @return
     */
    public int[] twoSumImprove(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length-1;
        for(;;){
            int res = numbers[left] + numbers[right];
            if (res == target) {
                return new int[]{left+1, right+1};
            } else if (res > target) {
                right--;
            } else {
                left++;
            }
        }
    }
}
