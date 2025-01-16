package org.ml.leetcode.daily;

public class MyAtoi {
    public static void main(String[] args) {
        String maxString = String.valueOf(Long.MAX_VALUE);
        String minString = String.valueOf(Long.MIN_VALUE);
        System.out.println(maxString);
        System.out.println(minString);
    }
    public int myAtoi(String str) {
        String maxString = String.valueOf(Integer.MAX_VALUE);
        String minString = String.valueOf(Integer.MIN_VALUE).substring(1);
        String temp = str.trim();
        StringBuilder sb = new StringBuilder();
        boolean isNag = false;
        for (int i = 0; i <temp.length(); i++) {
            char c = temp.charAt(i);
            if (i == 0 && (c == '+' || c == '-')) {
                if (c == '-') {
                    isNag = true;
                }
                continue;
            }
            if (c-'0' <= 9 && c-'0' >= 0) {
                sb.append(c);
            } else {
                break;
            }
        }
        String compStr = isNag ? minString : maxString;
        int res = 0;
        boolean needCut = false;
        boolean noNeedCut = false;
        int n = compStr.length();

        while (sb.length() > 0 && sb.charAt(0) == '0') {
            sb.deleteCharAt(0);
        }
        System.out.println(sb);
        if (sb.length() > compStr.length()) {
            return isNag ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        }
        for (int i = 0; i<sb.length(); i++) {
            char c = compStr.charAt(i);
            char sbc = sb.charAt(i);
            if (c<sbc && !noNeedCut && !needCut) {
                needCut = true;
                n -=1;
            }
            if (c > sbc) {
                noNeedCut = true;
            }
            if (needCut && i == n){
                return isNag ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }
            res = res * 10 + sbc - '0';
        }
        return isNag ? -res : res;
    }
}
