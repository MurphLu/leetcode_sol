package org.ml.leetcode.daily;

public class MaximumPrimeDifference {
    public static void main(String[] args) {
        System.out.println(new MaximumPrimeDifference().isPrime(5));
    }
    public int maximumPrimeDifference(int[] nums) {
        int idx = -1;
        int maxDis = 0;
        for (int i = 0; i < nums.length; i++) {
            if(isPrime(nums[i])) {
                if(idx == -1) {
                    idx = i;
                } else {
                    maxDis = Math.max(i - idx, maxDis);
                    idx = i;
                }
            }
        }
        return maxDis;
    }

    private boolean isPrime(int num) {
        int top = num/2;
        int i = 2;
        while (num % i != 0) {
            i++;
        }
        return i > top;
    }
}
