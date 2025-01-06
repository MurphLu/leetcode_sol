package org.ml.leetcode.daily;

import java.util.Arrays;
import java.util.TreeMap;

public class MyCalendarThree {
    // 用来记录每个插入的时间被预定的次数
    TreeMap<Integer, Integer> map ;
    int k = 1;
    public MyCalendarThree() {
        this.map = new TreeMap<>();
    }

    public int book(int startTime, int endTime) {
        //首先插入起点
        if(map.containsKey(startTime)){
            //如果treeMap中已经包含了startTime，那么由于区间是左闭右开，所以直接起始点+1即可
            int cnt = map.get(startTime) + 1;
            k = Math.max(k, cnt);
            map.put(startTime, map.get(startTime) + 1);
        }else {
            //如果不包含则需要查看map中startTime前一个时间，设以前一个时间被预定的次数为n，又因为startTime在所有以上一个数为起点的区间内，则以startTime为起点的区间就被预定了n+1次，没有前一个时间的话那么就直接等于1
            Integer floorKey = map.floorKey(startTime);
            if (floorKey == null) {
                map.put(startTime, 1);
            } else {
                int cnt = map.get(floorKey) + 1;
                k = Math.max(k, cnt);
                map.put(startTime, cnt);
            }
        }

        //然后插入终点，如果map中已经包含终点，由于是左闭右开区间，那么不用动，否则的话进行插入操作
        if(!map.containsKey(endTime)){
            //同样也要参照endTime前一个时间点的预定情况来确定endTime的预定情况
            //如果前一个时间点就是startTime的话，那endTime一定是startTime的预定次数减一
            //如果不是，那么endTime一定是在以上一个预定时间开始到与 上一个预定时间匹配的结束时间 之间，那么endTime被预定的次数也就同上一个预定时间的预定次数相同
            Integer floorKey = map.floorKey(endTime);
            map.put(endTime, map.get(floorKey)-(floorKey==startTime?1:0));
        }

        //由于插入了一个新的区间，那么该区间内的所有时间点被预定的次数一定都要+1，接下来则进行该操作
        Integer ceil = startTime;
        while ((ceil=map.ceilingKey(ceil+1))!= null && ceil < endTime) {
            int cnt = map.get(ceil) + 1;
            k = Math.max(k, cnt);
            map.put(ceil, cnt);
        }
        //在所有预定次数增加的时候都获取一下最大值，最后返回即可
        return k;
    }

}
