package org.ml.leetcode.InterviewClassical150;

import java.util.PriorityQueue;

/**
 * 数据流中的中位数
 */
public class MedianFinder {
    private PriorityQueue<Integer> bigRoot;
    private PriorityQueue<Integer> minRoot;

    public static void main(String[] args) {
        MedianFinder medianFinder = new MedianFinder();
        medianFinder.bigRoot.add(1);
        medianFinder.bigRoot.add(2);
        System.out.println(medianFinder.bigRoot.peek());
    }

    public MedianFinder() {
        bigRoot = new PriorityQueue<>((o1, o2) -> o2-o1);
        minRoot = new PriorityQueue<>();
    }

    public void addNum(int num) {
        if (bigRoot.isEmpty()) {
            bigRoot.add(num);
        }else if (num <= bigRoot.peek()) {
            bigRoot.add(num);
        }else {
            minRoot.add(num);
        }

        while (bigRoot.size()-minRoot.size() > 1) {
            minRoot.add(bigRoot.poll());
        }
        while (minRoot.size() > bigRoot.size()) {
            bigRoot.add(minRoot.poll());
        }
    }

    public double findMedian() {
        if (bigRoot.size() == minRoot.size()) {
            return (double) (bigRoot.peek() + minRoot.peek()) / 2;
        }
        return bigRoot.peek();
    }
}
