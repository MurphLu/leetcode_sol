package org.ml.leetcode;

/**
 * 给定一个非负整数 num，反复将各个位上的数字相加，直到结果为一位数。返回这个结果。
 */
public class AddDigits {
    public int addDigits(int num) {
        int res = 0;
        while (num!=0) {
            res += num%10;
            num /= 10;
            if (num == 0) {
                if(res < 10) {
                    return res;
                }
                num = res;
                res = 0;
            }
        }
        return res;
    }
}
