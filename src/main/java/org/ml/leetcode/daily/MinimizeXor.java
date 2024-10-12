package org.ml.leetcode.daily;

public class MinimizeXor {
    public int minimizeXor(int num1, int num2) {
        int cnt = 0;
        // 获取 num2的置位数
        while (num2 > 0) {
            cnt += (num2 & 1);
            num2 >>= 1;
        }
        byte[] bit = new byte[32];
        int idx = 0;
        // 将 num1 的位放到数组中，因为 num1, num2 在 0-10^9之间，一个长度位 32的 byte 数组就可以存储置位情况
        // 数组从左到右位 num1 的低位到高位
        while (num1 > 0) {
            bit[idx++] = (byte) (num1 & 1);
            num1 >>= 1;
        }
        // 为了使最终的异或结果最小，所以为们要从高位开始遍历，在置位数小于 cnt 的前提下，
        //将 res 中 对应的 num1高位的 1，都置为 1，这样异或之后就可以使高位变为 0
        int res = 0;
        for (int i = 31; i >=0 && cnt >0; i--) {
            if (bit[i] == 1) {
                res += (1<<i);
                cnt--;
            }
        }
        // 如果 num1 中的 1遍历完仍有剩余的置位
        // 那么从低位向高位遍历 0，将 0 对应的位置再置为 1，这样能保证最终结果最小
        for (int i = 0; i < 31 && cnt > 0; i++) {
            if (bit[i] == 0) {
                res += (1<<i);
                cnt--;
            }
        }
        return res;
    }
}
