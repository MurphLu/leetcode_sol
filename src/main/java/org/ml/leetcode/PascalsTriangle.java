package org.ml.leetcode;

import java.util.*;

/**
 * 杨辉三角
 */
public class PascalsTriangle {
    public static void main(String[] args) {
        new PascalsTriangle().generate(5);
        new PascalsTriangle().row(3);
    }
    // 生成
    public List<List<Integer>> generate(int numRows) {
        if (numRows == 1) {
            List<List<Integer>> res = new ArrayList<>();
            List<Integer> i = new ArrayList<>();
            i.add(1);
            res.add(i);
            return res;
        }
        List<List<Integer>> list = generate(numRows-1);
        List<Integer> cl = new ArrayList<>();
        cl.add(1);
        List<Integer> src = list.get(list.size()-1);
        for (int i = 1; i < numRows-1; i++) {
            cl.add(src.get(i-1)+src.get(i));
        }
        cl.add(1);
        list.add(cl);
        return list;
    }

    // 获取第 row 列， 0 开始
    public List<Integer> row(int numRow) {
        List<Integer> row = new ArrayList<>();
        row.add(1);
        int curRow = 1;
        while (curRow <= numRow) {
            curRow++;
            int temp = row.get(0);
            for (int i = 1; i < curRow-1; i++) {
                int newVal = row.get(i) + temp;
                temp = row.get(i);
                row.set(i, newVal);
            }
            row.add(1);
        }
        System.out.println(row);
        return row;
    }

}
