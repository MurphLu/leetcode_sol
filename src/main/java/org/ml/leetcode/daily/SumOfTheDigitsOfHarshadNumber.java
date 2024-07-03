package org.ml.leetcode.daily;

/**
 * 如果一个整数能够被其各个数位上的数字之和整除，则称之为 哈沙德数（Harshad number）。
 * 给你一个整数 x 。如果 x 是 哈沙德数 ，则返回 x 各个数位上的数字之和，否则，返回 -1 。
 */
public class SumOfTheDigitsOfHarshadNumber {
    public int sumOfTheDigitsOfHarshadNumber(int x) {
        int sum = 0;
        int temp = x;
        while (temp>0) {
            sum +=temp%10;
            temp/=10;
        }
        return x%sum==0 ? sum : -1;
    }
}
