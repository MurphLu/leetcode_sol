package org.ml.leetcode.daily;

/**
 * 桌子上有 n 个球，每个球的颜色不是黑色，就是白色。
 *
 * 给你一个长度为 n 、下标从 0 开始的二进制字符串 s，其中 1 和 0 分别代表黑色和白色的球。
 *
 * 在每一步中，你可以选择两个相邻的球并交换它们。
 *
 * 返回「将所有黑色球都移到右侧，所有白色球都移到左侧所需的 最小步数」。
 *
 *
 * 其实就是将 0 都移到左侧即可，遍历获取每个 0 左侧 1 的个数，就是当前位置的 0 在字符串左侧所需移动的最小步数
 * 每个 0 左侧 1 的个数相加即得结果
 */
public class MinimumSteps {
    public long minimumSteps(String s) {
        long cnt = 0;
        long cntOne = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '1') {
                cntOne++;
            }
            if (c == '0') {
                cnt += cntOne;
            }
        }
        return cnt;
    }
}
