package org.ml.leetcode.daily.monotonicStack;

import java.util.LinkedList;

/**
 * 给定一个循环数组 nums （ nums[nums.length - 1] 的下一个元素是 nums[0] ），返回 nums 中每个元素的 下一个更大元素 。
 *
 * 数字 x 的 下一个更大的元素 是按数组遍历顺序，这个数字之后的第一个比它更大的数，这意味着你应该循环地搜索它的下一个更大的数。如果不存在，则输出 -1 。
 */
public class NextGreaterElements {
    public static void main(String[] args) {
        new NextGreaterElements().nextGreaterElements(new int[]{
                1,2,3,2,1
        });
    }
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        LinkedList<Integer> list = new LinkedList<>();
        // 第一轮，找出数组中下一个比自己大的数
        for (int i = 0; i < n; i++) {
            while (!list.isEmpty() && nums[list.getLast()] < nums[i]) {
                int idx = list.removeLast();
                res[idx] = nums[i];
            }
            list.add(i);
        }
        // 第二轮，剩下的没有找到的进行下一轮遍历，从 0 开始
        // 因为 单调栈中为单调递减，则从 0 开始找下一个比栈顶更大的数字，更新 res
        // 第二轮遍历到栈低时结束
        int nextRoundBigIdx = 0;
        while (!list.isEmpty()) {
            int idx = list.getLast();
            while (nums[nextRoundBigIdx] <= nums[idx] && nextRoundBigIdx < idx) {
                nextRoundBigIdx++;
            }
            if(nextRoundBigIdx ==  idx) {
                break;
            }
            res[list.removeLast()] = nums[nextRoundBigIdx];
        }
        // 栈中最后剩下的为数组中的最大值，没有更大的值
        while (!list.isEmpty()) {
            res[list.removeLast()] = -1;
        }
        return res;
    }
}
