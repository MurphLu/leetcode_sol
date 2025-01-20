package org.ml.leetcode.daily;

import java.util.TreeMap;

public class LengthOfLIS {
    public static void main(String[] args) {
        new LengthOfLIS().lengthOfLIS(new int[]{
                10,9,2,5,3,7,101,18
        });
    }
    public int lengthOfLIS(int[] nums) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int res = 0;
        for (int i = nums.length-1; i >=0 ; i--) {
            int val = nums[i];
            Integer key = map.higherKey(val);
            int maxCnt = 0;
            while (key!= null) {
                if (key == val){
                    key = map.higherKey(key);
                    continue;
                }
                int cnt = map.get(key);
                maxCnt = Math.max(cnt, maxCnt);
                key = map.higherKey(key);
            }
            int valMaxCnt = map.getOrDefault(val, 1);
            int valCnt = Math.max(valMaxCnt, 1+maxCnt);
            res = Math.max(valCnt, res);
            map.put(val, valCnt);
        }
        return res;
    }
}
