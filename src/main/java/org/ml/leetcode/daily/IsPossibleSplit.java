package org.ml.leetcode.daily;

import java.util.Arrays;

public class IsPossibleSplit {
    public static void main(String[] args) {
        System.out.println(new IsPossibleSplit().isPossibleToSplit(new int[]{1,1,2,2,3,4}));
    }
    public boolean isPossibleToSplit(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int hn = n/2;
        int[] s1 = new int[hn];
        int[] s2 = new int[hn];
        int idxSource = 0;
        int idxS1 = 0;
        int idxS2 = 0;
        while (idxSource<n) {
            if (idxS1>0 && s1[idxS1-1] == nums[idxSource]) {
                return false;
            }
            if (idxS2>0 && s2[idxS2-1] == nums[idxSource]) {
                return false;
            }
            if (idxSource < n-1 && nums[idxSource] == nums[idxSource+1]) {
                s1[idxS1++] = nums[idxSource++];
                s2[idxS2++] = nums[idxSource++];
                continue;
            }
            if(idxS1 <= idxS2) {
                s1[idxS1++] = nums[idxSource++];
            } else {
                s2[idxS2++] = nums[idxSource++];
            }
        }
        return true;
    }
}
