package org.ml.leetcode;

/**
 * 给你两个字符串 word1 和 word2 。
 *
 * 如果一个字符串 x 重新排列后，word2 是重排字符串的 前缀 ，那么我们称字符串 x 是 合法的 。
 *
 * 请你返回 word1 中 合法 子字符串 的数目。
 */
public class ValidSubstringCount {
    public static void main(String[] args) {
        new ValidSubstringCount().validSubstringCount("ddccdddccdddccccdddccdcdcd", "ddc");
    }
    public long validSubstringCount(String word1, String word2) {
        char[] source = word1.toCharArray();
        char[] target = word2.toCharArray();

        // 维护所需的每个字符的个数，>0 则说明当前子串未满足该字符个数，
        // <0 则说明当前子串中该字符个数已超出
        int[] charMap = new int[26];
        // 还需要多少符合要求的字符才能满足要求，与 counts 配合食用
        int total = target.length;

        for (char c: target) {
            charMap[c-'a']++;
        }

        int left = 0;
        int right = 0;
        long res = 0;

        // 双指针只要有一个指针没有便利完，那么就进入循环
        while (left < source.length || right < source.length) {
            // 收缩左侧指针，当需要的字符等于 0 的时候说明 [left, right) 包含了所有字符，
            // 那么可以一直收缩，
            // 每次收缩都是一个新的起点，那么以该起点为开始位置，
            // 以 [right, sourceLen) 之间的每一个字符为重点都是一个符合要求的子串
            while (left < source.length && total == 0){
                res+=source.length-right+1;
                int charIdx = source[left]-'a';
                charMap[charIdx]+=1;
                if (charMap[charIdx] > 0) {
                    total+=1;
                }
                left++;
            }
            // 当然 right 已经移动到 source 之外那么直接结束循环即可
            // 因为我们在这之前已经将 left 收缩到不能再收缩的位置了
            // 此时 left 之后不可能再有符合要求的子串
            if (right>=source.length) {
                break;
            }
            // 扣减右指针字符并扩张右指针
            // 扣减后如果 >=0 说明当前 right 位置的字符为必须包含的字符，
            // 此时需要 total-1
            int idx = source[right]-'a';
            charMap[idx]--;
            if (charMap[idx] >= 0) {
                total-=1;
            }
            right++;
        }
        return res;
    }
}
