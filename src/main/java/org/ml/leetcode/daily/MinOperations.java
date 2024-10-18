package org.ml.leetcode.daily;

public class MinOperations {
    public static void main(String[] args) {

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
                    nums[temp] = nums[temp] == 1 ? 0 : 1;
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
