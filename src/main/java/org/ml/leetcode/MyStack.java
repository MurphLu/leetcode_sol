package org.ml.leetcode;


import java.util.LinkedList;
import java.util.Queue;

// 用队列实现栈，只能用队列的标准操作
// push to back, peek/pop from front, size
public class MyStack {
    Queue<Integer> queue = new LinkedList<>();
    boolean lastPush = false;
    int size = 0;
    public MyStack() {

    }

    private void needRevert(boolean push) {
        if (lastPush != push && !queue.isEmpty()) {
            int i = queue.poll();
            needRevert(push);
            queue.add(i);
        }
    }

    public void push(int x) {
        needRevert(true);
        lastPush = true;
        size++;
        queue.add(x);
    }

    public int pop() {
        needRevert(false);
        lastPush = false;
        size--;
        return queue.poll();
    }

    public int top() {
        needRevert(false);
        lastPush = false;
        return queue.peek();
    }

    public boolean empty() {
        return size == 0;
    }
}
