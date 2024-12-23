package org.ml.leetcode.daily;

import com.sun.source.util.Trees;

import java.util.*;

/**
 * 在考场里，一排有 N 个座位，分别编号为 0, 1, 2, ..., N-1 。
 *
 * 当学生进入考场后，他必须坐在能够使他与离他最近的人之间的距离达到最大化的座位上。如果有多个这样的座位，他会坐在编号最小的座位上。(另外，如果考场里没有人，那么学生就坐在 0 号座位上。)
 *
 * 返回 ExamRoom(int N) 类，它有两个公开的函数：其中，函数 ExamRoom.seat() 会返回一个 int （整型数据），代表学生坐的位置；
 * 函数 ExamRoom.leave(int p) 代表坐在座位 p 上的学生现在离开了考场。每次调用 ExamRoom.leave(p) 时都保证有学生坐在座位 p 上。
 */
public class ExamRoom {
    public static void main(String[] args) {
        ExamRoom examRoom = new ExamRoom(10);
        System.out.println(examRoom.seat());
        System.out.println(examRoom.seat());
        System.out.println(examRoom.seat());
        System.out.println(examRoom.seat());

        examRoom.leave(4);
        System.out.println(examRoom.seat());
    }
    int n;
    PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> {
        int dis1;
        int dis2;
        if (o1[0] == -1 && o1[1] == n) {
            dis1 = Integer.MAX_VALUE;
        }else if (o1[0]== -1) {
            dis1 = o1[1];
        } else if(o1[1] == n){
            dis1 = n-1-o1[0];
        } else {
            dis1 = (o1[1]-o1[0])/2;
        }

        if (o2[0] == -1 && o2[1] == n) {
            dis2 = Integer.MAX_VALUE;
        }else if (o2[0]== -1) {
            dis2 = o2[1];
        } else if(o2[1] == n){
            dis2 = n-1-o2[0];
        } else {
            dis2 = (o2[1]-o2[0])/2;
        }
        if(dis2 - dis1 == 0) {
            return o1[0]-o2[0];
        }
        return dis2 - dis1;
    });
    TreeSet<Integer> set = new TreeSet<>();

    public ExamRoom(int n) {
        this.n = n;
        int[] ints = {-1, n};
        set.add(-1);
        set.add(n);
        queue.add(ints);

    }

    public int seat() {
        int[] poll = queue.poll();
        while (set.higher(poll[0])!=poll[1]){
            poll = queue.poll();
        }
        int seat;
        if (poll[0] == -1) {
            int[] ints = {0, poll[1]};
            queue.add(ints);
            set.remove(-1);
            set.add(0);
            seat = 0;
        } else if (poll[1] == n) {
            int[] ints = {poll[0], n-1};
            queue.add(ints);
            set.remove(n);
            set.add(n-1);
            seat = n-1;
        } else {
            int mid = poll[0] + (poll[1] - poll[0])/2;
            int[] low = {poll[0], mid};
            int[] high = {mid, poll[1]};
            queue.add(low);
            queue.add(high);
            set.add(mid);
            seat = mid;
        }
        return seat;
    }

    public void leave(int p) {
        int low;
        int high;
        if (p == 0) {
            high = set.higher(p);
            set.remove(0);
            set.add(-1);
            low = -1;
        } else if (p == n-1) {
            low = set.lower(p);
            set.remove(n-1);
            set.add(n);
            high = n;
        } else {
            low = set.lower(p);
            high = set.higher(p);
            set.remove(p);
            set.add(low);
            set.add(high);
        }
        queue.add(new int[]{low, high});
    }
}
