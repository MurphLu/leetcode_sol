package org.ml.leetcode.daily;

/**
 * 给你一个浮点数 hour ，表示你到达办公室可用的总通勤时间。
 * 要到达办公室，你必须按给定次序乘坐 n 趟列车。另给你一个长度为 n 的整数数组 dist ，其中 dist[i] 表示第 i 趟列车的行驶距离（单位是千米）。
 *
 * 每趟列车均只能在整点发车，所以你可能需要在两趟列车之间等待一段时间。
 *
 * 例如，第 1 趟列车需要 1.5 小时，那你必须再等待 0.5 小时，搭乘在第 2 小时发车的第 2 趟列车。
 * 返回能满足你准时到达办公室所要求全部列车的 最小正整数 时速（单位：千米每小时），如果无法准时到达，则返回 -1 。
 *
 * 生成的测试用例保证答案不超过 10^7 ，且 hour 的 小数点后最多存在两位数字 。
 *
 *
 * 解题思路
 * 首先判断最大速度能否到达，排除最后一趟车，那么前 n-1 趟车的最大速度为路程长度，也就是能保证前面每趟车都只花一个小时
 * 如果 n-1 趟车所花费的最小时间都大于总时间，那么一定无法到达
 * 最后一趟车计算速度 = 最后一趟车的路程 / 剩余时间，
 * 通过以上计算则可获取到最大速度为多少
 * 最小速度为 1，
 * 然后通过最大速度与最小速度进行二分，则可获取到能够到达的最小速度
 *
 * 由于有 double 参与运算，则还需要考虑到精度问题
 * */
public class MinSpeedOnTime {
    public static void main(String[] args) {
        new MinSpeedOnTime().minSpeedOnTime(new int[]{5,3,4,6,2,2,7}, 10.92);
    }
    public int minSpeedOnTime(int[] dist, double hour) {
        int min = 1;
        int max = 0;
        for (int i = 0; i < dist.length; i++) {
            max = Math.max(max, dist[i]);
        }
        double minCost = dist.length -1;
        if (hour - minCost < 0.0001) {
            return -1;
        }
        int last = (int)Math.ceil(dist[dist.length-1] / (hour - minCost) - 0.0001);
        int ans = Math.max(max, last);
        while (min <= max) {
            int mid = (min+max) / 2;
            if (process(dist, mid, hour)) {
                ans = mid;
                max = mid-1;
            } else {
                min = mid+1;
            }
        }
        return ans;
    }

    private boolean process(int[] dist, int speed, double hour) {
        double spend = 0;
        for (int i = 0; i < dist.length-1; i++) {
            int dis = dist[i];
            int cost = dis/speed;
            spend += (dis % speed == 0) ? cost : (cost + 1);
        }
        spend += (double)dist[dist.length-1] / (double)speed;
        return spend <= hour;
    }
}
