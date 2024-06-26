package org.ml.leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 桶排序
 */
public class MaximumGap {
    public static void main(String[] args) {
        new MaximumGap().maximumGap(new int[]{
                494767408,862126209,213511142,768240025,631263193,839199222,990848796,214568815,540853864,760724418,980162605,976743981,168965760,680875496,256709197,970953816,948680062,347254784,732201399,786249847,782805044,40906641,674241381,784330934,175502610,731105392,424650824,549764101,986090032,710542549,249208107,448419816,527708664,158499246,223421723,114552457,466978424,821491411,19614107,115389497,902211438,2644108,744489871,897895073,372311214,551142753,933294894,426217662,217504874,983488406,516890023,426853110,971124103
        });
    }
    Queue<Integer>[] bucket = new Queue[10];


    public int maximumGap(int[] nums) {
        for (int i = 0; i < 10; i++) {
            bucket[i] = new LinkedList<>();
        }
        sort(nums);
        int max = 0;
        for (int i = 1; i < nums.length; i++) {
            max = Math.max(max, nums[i] - nums[i-1]);
        }
        return max;
    }

    private void sort(int[] nums) {
        long digit = 10;
        for (;;) {
            boolean finish = true;
            for (int num : nums) {
                int buckIdx = (int)((num % digit) / (digit / 10));
                if (num / (digit/10) > 0) {
                    finish = false;
                }
                bucket[buckIdx].add(num);
            }
            if (finish) {
                break;
            }
            int idx = 0;
            for (int i = 0; i < 10; i++) {
                Queue<Integer> integers = bucket[i];
                while (!integers.isEmpty()) {
                    nums[idx++] = integers.poll();
                }
            }
            digit *= 10;
        }
    }
}
