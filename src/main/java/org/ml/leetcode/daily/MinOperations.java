package org.ml.leetcode.daily;

public class MinOperations {
    public static void main(String[] args) {
        new MinOperations().minOperations(
                new int[]{0,1,1,1,0,0}
        );
    }
    public int minOperations(int[] nums) {
        int end = 3;
        int step = 0;
        int start = 0;
        while (end < nums.length) {
            if (nums[start] == 1) {
                start++;
                end++;
            } else {
                step += 1;
                int temp = start;
                while (temp<end){
                    if (nums[temp] == 1) {
                        nums[temp] = 0;
                    } else {
                        nums[temp] = 1;
                    }
                    temp++;
                }
            }
        }
        int cnt = 0;
        while (start < end) {
            cnt += nums[start++];
        }
        if (cnt == 3) {
            return step+1;
        } else if (cnt == 0) {
            return step;
        }
        return -1;

    }

}
