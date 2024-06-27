package org.ml.leetcode.daily;

public class SmallestString {
    public static void main(String[] args) {
        System.out.println(new SmallestString().smallestString("zzaaaaa"));
    }
    // 找出字符串中第一段不含 "a" 的子串进行替换即可
    // 如果字符串中只包含 a，那么替换掉最后一个 a 为 z 即得操作后的最小字典序
    public String smallestString(String s) {
        int startIdx = 0;
        int endIdx = 0;
        while (endIdx < s.length()){
            if(startIdx == endIdx && s.charAt(endIdx) == 'a') {
                startIdx++;
                endIdx++;
            } else {
                if (s.charAt(endIdx) == 'a') {
                    break;
                } else {
                    if (s.charAt(startIdx) == 'a') {
                        startIdx++;
                    }
                    endIdx++;
                }
            }
        }
        if (startIdx == endIdx) {
            return s.substring(0, startIdx - 1) + "z";
        }
        String pre = s.substring(0, startIdx);
        String mid = s.substring(startIdx, endIdx);
        char[] midChar = mid.toCharArray();
        for (int i = 0; i < midChar.length; i++) {
            midChar[i] = (char)(midChar[i] - 1);
        }
        String end = s.substring(endIdx);
        return pre + new String(midChar) + end;
    }
}
