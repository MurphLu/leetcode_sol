package org.ml.leetcode.daily;

import java.util.Arrays;

public class MyAtoi {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new MyAtoi().findRange(new int[]{1,2,2,2,2,2,2}, 2)));

    }
    public int myAtoi(String str) {
        String maxString = String.valueOf(Integer.MAX_VALUE);
        String minString = String.valueOf(Integer.MIN_VALUE).substring(1);
        String temp = str.trim();
        StringBuilder sb = new StringBuilder();
        boolean isNag = false;
        for (int i = 0; i <temp.length(); i++) {
            char c = temp.charAt(i);
            if (i == 0 && (c == '+' || c == '-')) {
                if (c == '-') {
                    isNag = true;
                }
                continue;
            }
            if (c-'0' <= 9 && c-'0' >= 0) {
                sb.append(c);
            } else {
                break;
            }
        }
        String compStr = isNag ? minString : maxString;
        int res = 0;
        boolean needCut = false;
        boolean noNeedCut = false;
        int n = compStr.length();

        while (sb.length() > 0 && sb.charAt(0) == '0') {
            sb.deleteCharAt(0);
        }
        System.out.println(sb);
        if (sb.length() > compStr.length()) {
            return isNag ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        }
        for (int i = 0; i<sb.length(); i++) {
            char c = compStr.charAt(i);
            char sbc = sb.charAt(i);
            if (c<sbc && !noNeedCut && !needCut) {
                needCut = true;
                n -=1;
            }
            if (c > sbc) {
                noNeedCut = true;
            }
            if (needCut && i == n){
                return isNag ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }
            res = res * 10 + sbc - '0';
        }
        return isNag ? -res : res;
    }




    public int[] findRange(int[] nums, int val) {
        int left = findLeft(nums, val, 0, nums.length);
        int right = findRight(nums, val, 0, nums.length);
        return new int[]{left, right};
    }

    private int findLeft(int[] nums, int val, int left, int right) {
        if (left == right) {
            return left;
        }
        int mid = left + (right - left) / 2;
        if (nums[mid] < val) {
            return findLeft(nums, val, mid+1, right);
        } else {
            return findLeft(nums, val, left, mid);
        }
    }
    private int findRight(int[] nums, int val, int left, int right) {
        if (left == right || left == right-1) {
            return left;
        }
        int mid = left + (right - left) / 2;
        if (nums[mid] <= val) {
            return findRight(nums,val, mid, right);
        } else {
            return findRight(nums, val, left, mid);
        }
    }
}
