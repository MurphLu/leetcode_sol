package org.ml.leetcode.daily;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NQueen {
    public static void main(String[] args) {
        List<List<String>> lists = new NQueen().solveNQueens(4);
        System.out.println(lists);
    }
    public List<List<String>> solveNQueens(int n) {

        return process(0, 0, 0, 0, n);
    }

    private List<List<String>> process(int l, int r, int q, int level, int n){
        if (level == n) {
            return new ArrayList<>();
        }
        l<<=1;
        r>>=1;
        int position = l|r|q;

        List<List<String>> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int trySet = 1<<i;
            if ((position&trySet) == 0) {
                String queenStr = getQueenStr(n, i);
                if (level == n-1) {
                    ArrayList<String> lastQ = new ArrayList<>();
                    lastQ.add(queenStr);
                    res.add(lastQ);
                } else {
                    List<List<String>> process = process(l | trySet, r | trySet, q | trySet, level + 1, n);
                    for(List<String> np: process) {
                        List<String> list = new ArrayList<>();
                        list.add(queenStr);
                        list.addAll(np);
                        res.add(list);
                    }
                }
            }
        }
        return res;
    }

    private String getQueenStr(int n, int i) {
        char[] cs = new char[n];
        Arrays.fill(cs, '.');
        cs[i] = 'Q';
        return new String(cs);
    }
}
