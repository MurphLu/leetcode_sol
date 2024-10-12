package org.ml.leetcode.daily;

public class MinimizeXor {
    public int minimizeXor(int num1, int num2) {
        int cnt = 0;
        while (num2 > 0) {
            cnt += (num2 & 1);
            num2 >>= 1;
        }
        int[] bit = new int[32];
        int idx = 0;
        while (num1 > 0) {
            bit[idx++] = num1 & 1;
            num1 >>= 1;
        }
        int res = 0;
        for (int i = 31; i >=0 && cnt >0; i--) {
            if (bit[i] == 1) {
                res += (1<<i);
                cnt--;
            }
        }
        for (int i = 0; i < 31 && cnt > 0; i++) {
            if (bit[i] == 0) {
                res += (1<<i);
                cnt--;
            }
        }
        return res;
    }
}
