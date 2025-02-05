package org.ml.leetcode.daily;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubsetsWithDup {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int cnt = map.getOrDefault(nums[i], 0);
            map.put(nums[i], cnt + 1);
        }
        res.add(new ArrayList<>());
        for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
            List<List<Integer>> temp = new ArrayList<>();
            for (int i = 1; i <= entry.getValue(); i++) {
                for (List<Integer> list: res) {
                    List<Integer> newList = new ArrayList<>(list);
                    for (int j = 0; j < i; j++) {
                        newList.add(entry.getKey());
                    }
                    temp.add(newList);
                }
            }
            res.addAll(temp);
        }
        return res;
    }
}
