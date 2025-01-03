package org.ml.leetcode.daily;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

/**
 * 实现一个程序来存放你的日程安排。如果要添加的时间内不会导致三重预订时，则可以存储这个新的日程安排。
 *
 * 当三个日程安排有一些时间上的交叉时（例如三个日程安排都在同一时间内），就会产生 三重预订。
 *
 * 事件能够用一对整数 startTime 和 endTime 表示，在一个半开区间的时间 [startTime, endTime) 上预定。实数 x 的范围为 startTime <= x < endTime。
 *
 * 实现 MyCalendarTwo 类：
 *
 * MyCalendarTwo() 初始化日历对象。
 * boolean book(int startTime, int endTime) 如果可以将日程安排成功添加到日历中而不会导致三重预订，返回 true。否则，返回 false 并且不要将该日程安排添加到日历中。
 */
public class MyCalendarTwo {
    public static void main(String[] args) {
        MyCalendarTwo myCalendarTwo = new MyCalendarTwo();
        myCalendarTwo.book(24,40);
        myCalendarTwo.book(43,50);
        myCalendarTwo.book(27,43);
        myCalendarTwo.book(5,21);
        myCalendarTwo.book(30,40);
        myCalendarTwo.book(14,29);
        myCalendarTwo.book(3,19);
        myCalendarTwo.book(3,14);
        myCalendarTwo.book(25,39);
    }
    List<int[]> booked;
    List<int[]> overlaps;

    public MyCalendarTwo() {
        booked = new ArrayList<int[]>();
        overlaps = new ArrayList<int[]>();
    }

    public boolean book(int start, int end) {
        for (int[] arr : overlaps) {
            int l = arr[0], r = arr[1];
            if (l < end && start < r) {
                return false;
            }
        }
        for (int[] arr : booked) {
            int l = arr[0], r = arr[1];
            if (l < end && start < r) {
                overlaps.add(new int[]{Math.max(l, start), Math.min(r, end)});
            }
        }
        booked.add(new int[]{start, end});
        return true;
    }
}
