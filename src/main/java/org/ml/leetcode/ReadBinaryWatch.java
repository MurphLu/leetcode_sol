package org.ml.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 二进制手表顶部有 4 个 LED 代表 小时（0-11），底部的 6 个 LED 代表 分钟（0-59）。每个 LED 代表一个 0 或 1，最低位在右侧。
 */
public class ReadBinaryWatch {
    public static void main(String[] args) {
        System.out.println(-1>>>4);
//        new ReadBinaryWatch().readBinaryWatch(10);
    }
    public List<String> readBinaryWatch(int turnedOn) {
        List<String> res = new ArrayList<>();
        for (int hour = 0; hour < 4; hour++) {
            if (hour<=turnedOn) {
                List<String> hour1 = hour(hour);
                List<String> minuit = minuit(turnedOn - hour);
                for(String h: hour1){
                    for (String mi: minuit){
                        res.add(h+":"+mi);
                    }
                }
            }
        }
        return res;
    }

    private List<String> hour(int turnedOn) {
        List<String> res = new ArrayList<>();
        if (turnedOn < 4) {
            hour(turnedOn, 0, res);
        }
        return res;
    }
    private void hour(int turnedOn, int cur, List<String> res) {
        if (turnedOn == 0) {
            if(cur < 12) {
                res.add(""+cur+"");
            }
            return;
        }

        int move = turnedOn-1;
        int i = 1 << move;
        while (i <= 8 && (cur & i) == 0) {
            hour(turnedOn-1, cur ^ i, res);
            i<<=1;
        }
    }
    private List<String> minuit(int turnedOn) {
        List<String> res = new ArrayList<>();
        if (turnedOn < 6) {
            minuit(turnedOn, 0, res);
        }
        return res;
    }
    private void minuit(int turnedOn, int cur, List<String> res) {
        if (turnedOn == 0) {
            if(cur < 60) {
                res.add(String.format("%02d", cur));
            }
            return;
        }

        int move = turnedOn - 1;
        int i = 1 << move;
        while (i <=32 && ((cur & i) ==0)) {
            minuit(turnedOn-1, cur ^ i, res);
            i<<=1;
        }
    }
}
