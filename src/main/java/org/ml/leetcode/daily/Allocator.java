package org.ml.leetcode.daily;

import java.util.*;

public class Allocator {
    public static void main(String[] args) {
        Allocator allocator = new Allocator(50);
        System.out.println(allocator.allocate(12,6));
        System.out.println(allocator.allocate(28,16));
        System.out.println(allocator.allocate(17,23));
        System.out.println(allocator.freeMemory(6));
        System.out.println(allocator.allocate(12,45));
        System.out.println(allocator.allocate(1,1));
        System.out.println(allocator.allocate(1,1));
        System.out.println(allocator.freeMemory(1));
    }
    static class Node {
        int start;
        int end;

        Node next;
        Node pre;

        public Node(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    Map<Integer, List<Node>> map;
    Node head;
    private final int n;
    int remain;

    public Allocator(int n) {
        this.map = new HashMap<>();
        this.n = n;
        remain = n;
        this.head = null;
    }

    public int allocate(int size, int mID) {
        Node node = null;
        try{
            Node preNode = getPreNode(size);
            if (preNode == null) {
                node = new Node(0, size - 1);
                node.next = head;
                if (head != null) {
                    head.pre = node;
                }
                head = node;
            } else {
                Node next = preNode.next;
                node = new Node(preNode.end + 1, preNode.end + size);
                preNode.next = node;
                node.next = next;
                node.pre = preNode;
                if (next!= null) {
                    next.pre = node;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(node == null) {
            return -1;
        }
        List<Node> orDefault = map.getOrDefault(mID, new ArrayList<>());
        orDefault.add(node);
        map.put(mID, orDefault);
        return node.start;
    }

    private Node getPreNode(int size) throws RuntimeException{

        if (head == null) {
            return null;
        }
        Node preNode = head;
        if (preNode.start >= size) {
            return null;
        }
        for (;;) {

            Node nextNode = preNode.next;
            if (nextNode != null) {
                if ((nextNode.start - preNode.end - 1) >= size) {
                    return preNode;
                } else {
                    preNode = nextNode;
                }
            } else {
                if(n-preNode.end-1 >= size) {
                    return preNode;
                }
                throw new RuntimeException("can not allocate");
            }
        }
    }

    private int getStartIdx(int size) {
        int startIdx = 0;
        if (size > remain) {
            return n;
        } else {
            if (head == null) {
                return 0;
            } else {
                Node node = head;
                while (node != null) {
                    if(node.start - startIdx >= size) {
                        return startIdx;
                    }
                    startIdx = node.end+1;
                    node = node.next;
                }
                if (n-startIdx >= size) {
                    return startIdx;
                }
                return n;
            }
        }
    }

    public int freeMemory(int mID) {
        int res = 0;
        List<Node> nodes = map.remove(mID);
        for(Node node : nodes) {
            res += node.end - node.start + 1;
            Node pre = node.pre;
            Node next = node.next;
            if (pre != null) {
                pre.next = next;
            } else {
                head = next;
            }
            if (next != null) {
                next.pre = pre;
            }
        }
        return res;
    }

}
