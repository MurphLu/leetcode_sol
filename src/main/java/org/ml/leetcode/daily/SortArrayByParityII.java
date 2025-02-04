package org.ml.leetcode.daily;

import java.util.Arrays;

public class SortArrayByParityII {
    public static void main(String[] args) {
        int[] ints = {4, 2, 5, 7};
        new SortArrayByParityII().sortArrayByParityII(ints);
    }

    public int[] sortArrayByParityII(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        // 将数组分成 左侧偶数，右侧奇数
        while (left < right) {
            if (nums[left] % 2 == 0) {
                left++;
            } else if (nums[right] % 2 == 1) {
                right--;
            } else {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
                left++;
                right--;
            }
        }
        int idx = 1;
        int mid = nums.length / 2;
        // 检查数组一半的元素个数为奇数还是偶数来判断右侧开始位置
        mid = mid % 2 == 0 ? mid : mid + 1;
        while (mid < nums.length) {
            if (idx % 2 == 1 ) {
                int temp = nums[idx];
                nums[idx] = nums[mid];
                nums[mid] = temp;
            }
            idx++;
            mid++;
        }
        return nums;
    }
}
