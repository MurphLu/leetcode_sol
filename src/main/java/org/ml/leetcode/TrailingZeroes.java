package org.ml.leetcode;

/**
 * 给定一个整数 n ，返回 n! 结果中尾随零的数量。
 *
 * 提示 n! = n * (n - 1) * (n - 2) * ... * 3 * 2 * 1
 *
 * 数字末尾有 5 或者 10 则会产生 0
 * 5 的话需要 * 2，所以一个数的末尾有 5 那么乘 2 后继续判断，由于阶乘中 偶数是远远够用的，
 * 所以一个数字可以一直 * 2，直到不再有 5 出现为止
 */
public class TrailingZeroes {
    public int trailingZeroes(int n) {
        int num = 5;
        int cnt = 0;
        while (num <= n) {
            cnt += produce(num);
            num += 5;
        }
        return cnt;
    }

    private int produce(int num) {
        int cnt = 0;
        if (num == 0) {
            return 0;
        }
        if (num == 5) {
            return 1;
        }
        if(num % 10 == 0) {
            cnt +=1;
            cnt += produce(num / 10);
        }
        if (num % 10 == 5) {
            cnt += produce(num * 2);
        }
        return cnt;
    }
}
