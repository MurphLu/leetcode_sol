package org.ml.leetcode.daily;

import java.util.*;

/**
 * 给你一个长度为 n 的二维整数数组 items 和一个整数 k 。
 *
 * items[i] = [profiti, categoryi]，其中 profiti 和 categoryi 分别表示第 i 个项目的利润和类别。
 *
 * 现定义 items 的 子序列 的 优雅度 可以用 total_profit + distinct_categories2 计算，
 * 其中 total_profit 是子序列中所有项目的利润总和，distinct_categories 是所选子序列所含的所有类别中不同类别的数量。
 *
 * 你的任务是从 items 所有长度为 k 的子序列中，找出 最大优雅度 。
 *
 * 用整数形式表示并返回 items 中所有长度恰好为 k 的子序列的最大优雅度。
 *
 * 注意：数组的子序列是经由原数组删除一些元素（可能不删除）而产生的新数组，且删除不改变其余元素相对顺序。
 */
public class FindMaximumElegance {

    public long findMaximumElegance(int[][] items, int k) {
        Arrays.sort(items, (item0, item1) -> item1[0] - item0[0]);
        Set<Integer> categorySet = new HashSet<>();
        long profit = 0, res = 0;
        ArrayDeque<Integer> st = new ArrayDeque<>();
        for (int i = 0; i < items.length; i++) {
            if (i < k) {  // 到 k 个时，此时 total profit 已经是最大，接下来就看是否会有 item 会影响 distinct_categories 的大小，
                profit += items[i][0];
                if (!categorySet.add(items[i][1])) {
                    st.push(items[i][0]);
                }
            } else if (!st.isEmpty() && categorySet.add(items[i][1])) {
                // 如果没有此时没有重复的种类，或者当前 item 的 cat 已经有重复，那么当前 item 一定不会使结果最大
                // 如果对应的替换项目的类别出现两次以上，取利润最小的项目进行替换，那么替换后，distinct_categories 会增加，总体优雅度有可能增加，所以可以选择该项目。
                //（如果现在不选择该项目，后续出现类似的情况时，因为利润是从大到小排序的，总体优雅度不会更大
                profit += items[i][0] - st.pop();
            }
            res = Math.max(res, profit + (long)categorySet.size() * categorySet.size());
        }
        return res;

    }
}
