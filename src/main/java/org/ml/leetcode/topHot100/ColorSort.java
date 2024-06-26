package org.ml.leetcode.topHot100;

/**
 * 给定一个包含红色、白色和蓝色、共 n 个元素的数组 nums ，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 *
 * 我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
 *
 * 必须在不使用库内置的 sort 函数的情况下解决这个问题。
 */
public class ColorSort {
    public static void main(String[] args) {

    }
    public void sortColors(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        int cur = 0;
        while (cur <= right) {
            if (nums[cur] == 0) {
                swap(nums, left++, cur++);
            } else if (nums[cur] == 2) {
                swap(nums, right--, cur);
            }else {
                cur++;
            }
        }

    }
    public void swap(int[] nums, int i , int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
