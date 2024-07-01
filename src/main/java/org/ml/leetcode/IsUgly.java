package org.ml.leetcode;

//丑数 就是只包含质因数 2、3 和 5 的正整数。
public class IsUgly {
    public boolean isUgly(int n) {
        if (n <=0 ) {
            return false;
        }

        while (n % 2 == 0) {
            n /=2;
        }
        while (n % 3 == 0) {
            n /=3;
        }
        while (n % 5 == 0) {
            n /=5;
        }
        return n == 1;
    }
}
