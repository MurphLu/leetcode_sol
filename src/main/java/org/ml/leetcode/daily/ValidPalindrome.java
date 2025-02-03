package org.ml.leetcode.daily;

public class ValidPalindrome {
    public static void main(String[] args) {
        boolean b = new ValidPalindrome().validPalindrome("aguokepatgbnvfqmgmlcupuufxoohdfpgjdmysgvhmvffcnqxjjxqncffvmhvgsymdjgpfdhooxfuupuculmgmqfvnbgtapekouga");
        System.out.println(b);
    }
    public boolean validPalindrome(String s) {
        char[] chars = s.toCharArray();
        int left = 0;
        int right = chars.length - 1;
        int skip = 0;
        while (left < right) {
            if (chars[left] == chars[right]) {
                left++;
                right--;
                continue;
            }
            break;
        }
        if (left == right || left == right-1) {
            return true;
        }
        return validate(chars, left, right-1) || validate(chars, left+1, right);
    }
    private boolean validate(char[] chars, int left, int right) {
        while (left < right) {
            if (chars[left] == chars[right]) {
                left++;
                right--;
                continue;
            }
            return false;
        }
        return true;
    }
}