package org.ml.leetcode;

import java.util.PriorityQueue;

/**
 * 请你设计一个管理 n 个座位预约的系统，座位编号从 1 到 n 。
 *
 * 请你实现 SeatManager 类：
 *
 * SeatManager(int n) 初始化一个 SeatManager 对象，它管理从 1 到 n 编号的 n 个座位。所有座位初始都是可预约的。
 * int reserve() 返回可以预约座位的 最小编号 ，此座位变为不可预约。
 * void unreserve(int seatNumber) 将给定编号 seatNumber 对应的座位变成可以预约。
 *
 * 1 <= n <= 10^5
 * 1 <= seatNumber <= n
 * 每一次对 reserve 的调用，题目保证至少存在一个可以预约的座位。
 * 每一次对 unreserve 的调用，题目保证 seatNumber 在调用函数前都是被预约状态。
 * 对 reserve 和 unreserve 的调用 总共 不超过 10^5 次。
 */
public class SeatManager {

    SeatTree seatTree;

    public SeatManager(int n) {
        this.seatTree = new SeatTree(n);
    }

    public int reserve() {
        return seatTree.findAvailable();
    }

    public void unreserve(int seatNumber) {
        seatTree.releaseSeat(seatNumber-1);
    }



    static class SeatTree {
        Seat seat;
        Seat[] list;

        public SeatTree(int num) {
            list = new Seat[num];
            this.seat = buildSeatTree(null, 1, num);
        }

        private Seat buildSeatTree(Seat parent, int left, int right) {
            if (left > right) {
                return null;
            }

            int num = (left+right) / 2;
            int leftAvailable = num - left;
            int rightAvailable = right - num;
            Seat seat = new Seat(num, parent, leftAvailable, rightAvailable, false);
            Seat seatL = buildSeatTree(seat, left, num - 1);
            Seat seatR = buildSeatTree(seat, num + 1, right);

            seat.left = seatL;
            seat.right = seatR;
            list[num-1] = seat;
            return seat;
        }

        public void releaseSeat(int num) {
            Seat s = list[num];
            s.reserved = false;
            Seat parent = s.parent;
            while (parent != null) {
                if(parent.left == s) {
                    parent.leftAvailable++;
                } else {
                    parent.rightAvailable++;
                }
                s = parent;
                parent = parent.parent;
            }
        }

        public int findAvailable(){
            return findAvailableMin(seat);
        }

        private int findAvailableMin(Seat seat) {
            if(seat.leftAvailable>0) {
                seat.leftAvailable--;
                return findAvailableMin(seat.left);
            }
            if (!seat.reserved) {
                seat.reserved = true;
                return seat.num;
            }
            if (seat.rightAvailable>0) {
                seat.rightAvailable--;
                return findAvailableMin(seat.right);
            }
            return -1;
        }
    }

    static class Seat {
        int num;
        Seat parent;
        Seat left;
        Seat right;
        int leftAvailable;
        int rightAvailable;
        boolean reserved;

        public Seat(int num) {
            this.num = num;
        }

        public Seat(int num, Seat parent, int leftAvailable, int rightAvailable, boolean reserved) {
            this.num = num;
            this.parent = parent;
            this.leftAvailable = leftAvailable;
            this.rightAvailable = rightAvailable;
            this.reserved = reserved;
        }
    }
}

