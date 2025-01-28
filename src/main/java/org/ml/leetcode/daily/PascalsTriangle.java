package org.ml.leetcode.daily;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个非负索引 rowIndex，返回「杨辉三角」的第 rowIndex 行。
 *
 * 在「杨辉三角」中，每个数是它左上方和右上方的数的和。
 *
 *          1
 *         1 1
 *        1 2 1
 *       1 3 3 1
 *      1 4 6 4 1
 *
 * 示例 1:
 *
 * 输入: rowIndex = 3
 * 输出: [1,3,3,1]
 * 示例 2:
 *
 * 输入: rowIndex = 0
 * 输出: [1]
 * 示例 3:
 *
 * 输入: rowIndex = 1
 * 输出: [1,1]
 * 提示:
 */
public class PascalsTriangle {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> res = new ArrayList<Integer>();
        int level = 0;
        res.add(1);
        while (level < rowIndex){
            level++;
            int idx = 0;
            int temp = res.get(idx);
            while (++idx < level) {
                int newVal = res.get(idx) + temp;
                temp = res.get(idx);
                res.set(idx, newVal);
            }
            res.add(1);
        }
        return res;
    }
}
