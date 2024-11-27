package org.ml.leetcode.daily;

/**
 * 给你一个整数数组 colors ，它表示一个由红色和蓝色瓷砖组成的环，第 i 块瓷砖的颜色为 colors[i] ：
 *
 * colors[i] == 0 表示第 i 块瓷砖的颜色是 红色 。
 * colors[i] == 1 表示第 i 块瓷砖的颜色是 蓝色 。
 * 环中连续 3 块瓷砖的颜色如果是 交替 颜色（也就是说中间瓷砖的颜色与它 左边 和 右边 的颜色都不同），那么它被称为一个 交替 组。
 *
 * 请你返回 交替 组的数目。
 *
 * 注意 ，由于 colors 表示一个 环 ，第一块 瓷砖和 最后一块 瓷砖是相邻的。
 */
public class NumberOfAlternatingGroups {
    public int numberOfAlternatingGroups(int[] colors) {
        int cnt = 0;
        for(int i=0; i<colors.length; i++) {
            int next = (i+1) % colors.length;
            int pre = i == 0 ? colors.length-1 : i-1;
            if(colors[i] != colors[next] && colors[i] != colors[pre]) {
                cnt++;
            }
        }
        return cnt;
    }

    /**
     * 给你一个整数数组 colors 和一个整数 k ，colors表示一个由红色和蓝色瓷砖组成的环，第 i 块瓷砖的颜色为 colors[i] ：
     *
     * colors[i] == 0 表示第 i 块瓷砖的颜色是 红色 。
     * colors[i] == 1 表示第 i 块瓷砖的颜色是 蓝色 。
     * 环中连续 k 块瓷砖的颜色如果是 交替 颜色（也就是说除了第一块和最后一块瓷砖以外，中间瓷砖的颜色与它 左边 和 右边 的颜色都不同），那么它被称为一个 交替 组。
     *
     * 请你返回 交替 组的数目。
     *
     * 注意 ，由于 colors 表示一个 环 ，第一块 瓷砖和 最后一块 瓷砖是相邻的。
     * @param colors 数组
     * @param k 满足连续 k 个交替
     * @return 返回共有多少组
     */
    public int numberOfAlternatingGroups(int[] colors, int k) {
        int n = colors.length;

        // 记录符合要求的条目
        int cnt = 0;
        // 当前遍历左边界
        int left = 0;
        //当前遍历右边界
        int right = 0;
        // 当前遍历的最后一个数
        int last = colors[0];

        // 因为是一个环，所以，当左边界从 0 开始到数组末尾结束
        while (left < n) {
            // 当左右边界范围包含 k 个数时，则当前范围满足，cnt++
            // 同时左边界右一一位判断下一组
            if (right - left + 1 == k) {
                cnt++;
                left++;
            }
            // 右边界右移一位
            right++;
            // 如果右边界的值同上一轮最后一个值相同，那么则[left,right] 范围内的值不满足
            // left 直接跳到 right 继续遍历
            if (colors[right%n]==last) {
                left = right;
            }
            // 更新最后一位的值
            last=colors[right%n];
        }
        return cnt;
    }
}
