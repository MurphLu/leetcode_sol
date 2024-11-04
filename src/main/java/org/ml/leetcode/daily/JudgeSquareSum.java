package org.ml.leetcode.daily;

public class JudgeSquareSum {
    public static void main(String[] args) {
        new JudgeSquareSum().judgeSquareSum(2);
    }
    public boolean judgeSquareSum(int c) {
        int l = 0;
        int h = (c-l);
        while (l*l<=h){
            int r = find(l, h, h);
            if (r*r == h){
                return true;
            }
            l++;
            h=c-l*l;
        }
        return false;
    }
    private int find(int l, int h, int num){
        if (num == 1 || num == 0) {
            return num;
        }
        if (l+1==h){
            return l;
        }
        int mid = l+(h-l)/2;
        if (num/mid>mid) {
            return find(mid, h, num);
        } else if (num / mid < mid) {
            return find(l, mid, num);
        } else{
            return mid;
        }
    }
}
