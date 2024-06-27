package org.ml.leetcode.daily;

public class SmallestString {
    public static void main(String[] args) {
        System.out.println(new SmallestString().smallestString("aaaaaaa"));
    }
    // 找出字符串中第一段不含 "a" 的子串进行替换即可
    // 如果字符串中只包含 a，那么替换掉最后一个 a 为 z 即得操作后的最小字典序
    public String smallestString(String s) {
        int startIdx = 0;
        int endIdx = 0;
        char[] arr = s.toCharArray();
        while (endIdx < s.length()){
            if(startIdx == endIdx && arr[endIdx] == 'a') {
                startIdx++;
                endIdx++;
            } else {
                if (arr[endIdx] == 'a') {
                    break;
                } else {
                    arr[endIdx] = (char)(arr[endIdx] - 1);
                    endIdx++;
                }
            }
        }
        if (startIdx == endIdx) {
            arr[endIdx-1] = 'z';
        }
        return new String(arr);
    }
}
