package org.ml.leetcode;

/**
 * 给你一个正整数 num 。如果 num 是一个完全平方数，则返回 true ，否则返回 false 。
 *
 * 完全平方数 是一个可以写成某个整数的平方的整数。换句话说，它可以写成某个整数和自身的乘积。
 *
 * 不能使用任何内置的库函数，如  sqrt 。
 */
public class IsPerfectSquare {
    public static void main(String[] args) {
        System.out.println(15/4);
        System.out.println(new IsPerfectSquare().isPerfectSquare(25));
    }
    public boolean isPerfectSquare(int num) {
        if (num == 1) {return true;}
        int low = 1;
        int high = num;
        while (low < high) {
            int mid = (high - low) / 2 + low;
            if(num / mid > mid) {
                low = mid+1;
            } else if(num/mid < mid) {
                high = mid;
            } else {
                if (num % mid == 0) {
                    return true;
                }
                low = mid+1;
            }
        }
        return false;


    }
}
