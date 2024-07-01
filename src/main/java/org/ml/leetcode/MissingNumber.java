package org.ml.leetcode;

public class MissingNumber {
    public static void main(String[] args) {
        System.out.println(new MissingNumber().missingNumber(new int[]{9,6,4,2,3,5,7,0,1}));
    }
    public int missingNumber(int[] nums) {
        int[] arr = new int[nums.length+1];
        for (int i = 0; i < nums.length; i++) {
            arr[nums[i]] = nums[i];
        }
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] != i) {
                return i;
            }
        }
        return 0;
    }
}
