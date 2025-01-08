package org.ml.leetcode;

public class LongestGoodInteger {
    public static void main(String[] args) {
        new LongestGoodInteger().largestGoodInteger("6777133339");
    }
    public String largestGoodInteger(String num) {
        String res="";
        int idx = 0;
        while (idx < num.length()-2) {
            int l = 0;
            while (idx<num.length()-1 && num.charAt(idx+l)==num.charAt(idx+1+l)){
                l++;
                if (l == 2){
                    break;
                }
            }

            if (l == 2) {
                if(res.compareTo(num.substring(idx, idx+3))<0){
                    res = num.substring(idx, idx+3);
                }
                idx += 3;
            } else {
                idx+=1;
            }
        }
        return res;
    }
}
