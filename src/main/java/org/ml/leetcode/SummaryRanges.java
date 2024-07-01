package org.ml.leetcode;

import java.util.ArrayList;
import java.util.List;

public class SummaryRanges {
    public static void main(String[] args) {
        System.out.println(Integer.bitCount(-(int)Math.pow(2, 29)));
    }
    public List<String> summaryRanges(int[] nums) {

        int idx = 0;
        List<String> res = new ArrayList<>();
        while (idx < nums.length) {
            int last = nums[idx];
            int start = nums[idx++];
            while (idx < nums.length && nums[idx] == last +1) {
                last = nums[idx];
                idx++;
            }
            if (start == last) {
                res.add(""+ start +"");
            } else {
                res.add(start + "->" + last);
            }
        }
        return res;
    }
}
