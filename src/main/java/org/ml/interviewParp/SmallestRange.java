package org.ml.interviewParp;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class SmallestRange {
    static class Info {
        int idx;
        int num;

        public Info(int idx, int num) {
            this.idx = idx;
            this.num = num;
        }
    }
    public int[] smallestRange(List<List<Integer>> nums) {
        List<Info> list = new ArrayList<>();
        merge(nums, 0, nums.size());
        return new int[0];
    }

    private List<Info> merge(List<List<Integer>> nums, int left, int right) {
        if (left == right) {
            return nums.get(left).stream().map((o)->new Info(left, o)).collect(Collectors.toList());
        }
        int mid = (right-left)/2+left;
        List<Info> l = merge(nums, left, mid);
        List<Info> r = merge(nums, mid + 1, right);
        List<Info> merged = new ArrayList<>();
        int lIdx = 0;
        int rIdx = 0;
        while (lIdx < l.size() && rIdx < r.size()) {

        }
        return new ArrayList<>();
    }
}
