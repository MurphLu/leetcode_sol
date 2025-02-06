package org.ml.leetcode.daily;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
 *
 * 示例 1：
 *
 * 输入：nums = [1,1,2]
 * 输出：
 * [[1,1,2],
 *  [1,2,1],
 *  [2,1,1]]
 * 示例 2：
 *
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 * 提示：
 *
 * 1 <= nums.length <= 8
 * -10 <= nums[i] <= 10
 */
public class PermuteUnique {
    public List<List<Integer>> permuteUnique(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int num: nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        return process(map);
    }
    private List<List<Integer>> process(Map<Integer, Integer> map) {
        List<List<Integer>> res = new ArrayList<>();
        for(int num: map.keySet()) {
            if(map.get(num) > 0) {
                map.put(num, map.get(num) - 1);
                List<List<Integer>> nextRoundList = process(map);
                for(List<Integer> nextRound: nextRoundList) {
                    List<Integer> list = new ArrayList<>(nextRound);
                    list.add(num);
                    res.add(list);
                }
                map.put(num, map.get(num) + 1);
            }
        }
        if (res.isEmpty()) {
            res.add(new ArrayList<>());
        }
        return res;
    }
}
