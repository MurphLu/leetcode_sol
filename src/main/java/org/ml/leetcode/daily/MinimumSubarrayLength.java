package org.ml.leetcode.daily;

public class MinimumSubarrayLength {
    public static void main(String[] args) {
        new MinimumSubarrayLength().minimumSubarrayLength(new int[]{
                1,2,3
        }, 2);

    }
    public int minimumSubarrayLength(int[] nums, int k) {
        int length = 1;
        while (length <= nums.length) {
            int left = 0;
            int right = 1;
            int[] res = new int[10];
            add(res, nums[left]);

            while (right - left < length) {
                int num = nums[right++];
                int idx = 0;
                add(res, num);
            }
            if (calc(res) >= k) return length;
            while (right < nums.length) {
                remove(res, nums[left++]);
                add(res, nums[right++]);
                if (calc(res) >= k) return length;
            }
            length++;
        }
        return -1;
    }
    private void add(int[] bit, int num) {
        int idx = 0;
        do{
            bit[idx] += (num & 1);
            idx++;
        } while((num>>=1) > 0);

    }
    private void remove(int[] bit, int num) {
        int idx = 0;
        do{
            bit[idx] -= (num & 1);
            idx++;
        } while((num>>=1) > 0);
    }
    private int calc(int[] bit){
        int idx = 0;
        int res = 0;
        while (idx < bit.length){
            res += (bit[idx] > 0 ? 1 : 0) << idx++;
        }
        return res;
    }
}
