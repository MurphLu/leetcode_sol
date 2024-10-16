package org.ml.leetcode.daily;

/**
 * 给你两个整数 red 和 blue，分别表示红色球和蓝色球的数量。你需要使用这些球来组成一个三角形，满足第 1 行有 1 个球，第 2 行有 2 个球，第 3 行有 3 个球，依此类推。
 *
 * 每一行的球必须是 相同 颜色，且相邻行的颜色必须 不同。
 *
 * 返回可以实现的三角形的 最大 高度。
 */
public class MaxHeightOfTriangle {
    public int maxHeightOfTriangle(int red, int blue) {
        int red1 = red;
        int red2 = red;
        int blue1 = blue;
        int blue2 = blue;
        int level = 1;
        while (true) {
            if (level % 2 == 1) {
                red1-=level;
                blue2-=level;
            } else {
                blue1-=level;
                red2-=level;
            }
            if ((red1 < 0 || blue1 < 0) && (red2 < 0 || blue2 < 0)) {
                break;
            }
            level++;
        }
        return level-1;
    }
}
