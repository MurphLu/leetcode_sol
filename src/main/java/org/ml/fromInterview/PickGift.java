package org.ml.fromInterview;

import java.util.Arrays;

/**
 * 从礼物中挑选 k 件，价值范围 1～10^9，
 * 保证挑选出礼物中价值最接近的两个差值最大
 *
 * 差值二分
 */
public class PickGift {
    public static int pickGift(int[] arr, int k) {
        if (arr.length < k) {
            return -1;
        }
        int n = arr.length;
        Arrays.sort(arr);
        int l = 0;
        int r = arr[n-1] - arr[0];
        int m = 0;
        int ans = 0;
        while (l<=r) {
            m = (l+r)/2;
            if (can(arr, k, m)) {
                ans = m;
                l = m+1;
            } else {
                r = m-1;
            }
        }
        return ans;
    }

    private static boolean can(int[] arr, int k, int m) {
        int last = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (last + m <= arr[i]) {
                last = arr[i];
                k--;
            }
            if (k == 0) {
                break;
            }
        }
        return k <= 0;
    }
}
