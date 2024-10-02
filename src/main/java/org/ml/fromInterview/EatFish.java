package org.ml.fromInterview;

import java.util.ArrayList;
import java.util.List;

/**
 * 现在有N条鱼，每条鱼的体积为Ai，从左到右排列，数组arr给出每一轮，
 * 左边的大鱼一定会吃掉右边比自己小的第一条鱼,并且每条鱼吃比自己小的鱼的事件是同时发生的。返回多少轮之后，鱼的数量会稳定
 * 注意:6 6 3 3
 * 第一轮过后 :对于两个6来说，右边比自己小的第一条鱼都是第1个3，所以只有这个3被吃掉数组变成 :6 6 3
 * (第 2 个 3)
 * 第二轮过后 :6 6
 * 返回 2
 */
public class EatFish {
    public static void main(String[] args) {
        EatFish.eatFish(new int[]{1,2,3,5,4,3,5,2,3,2,1,5});
    }
    public static int eatFish(int[] fish) {
        int round = 0;
        int[] res = process(fish);
        while (fish.length != res.length) {
            fish = res;
            res = process(fish);
            round++;
        }
        return round;
    }

    private static int[] process(int[] fish) {
        List<Integer> arr = new ArrayList<>();
        int idx = 0;
        arr.add(fish[idx++]);
        while (idx < fish.length) {
            if (arr.get(arr.size()-1) <= fish[idx]) {
                arr.add(fish[idx++]);
            } else {
                int f = fish[idx++];
                while (idx < fish.length && f > fish[idx]) {
                    f = fish[idx];
                    idx++;
                }
                if (idx < fish.length) {
                    arr.add(fish[idx++]);
                }
            }
        }
        int[] res = new int[arr.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = arr.get(i);
        }
        return res;
    }
}
