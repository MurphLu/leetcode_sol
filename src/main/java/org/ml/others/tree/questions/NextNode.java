package org.ml.others.tree.questions;

/**
 * 给定一个 完美二叉树 ，其所有叶子节点都在同一层，每个父节点都有两个子节点。二叉树定义如下：
 *
 * struct Node {
 *   int val;
 *   Node *left;
 *   Node *right;
 *   Node *next;
 * }
 * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
 *
 * 初始状态下，所有 next 指针都被设置为 NULL。
 */
public class NextNode {
    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    public Node connect(Node node) {
        process(node);
       return node;
    }
    public void process(Node node) {
        while (node != null) {
            Node curr = node;
            while (curr.next != null) {
                if (curr.left != null) {
                    curr.left.next = curr.right;
                    curr.right.next = curr.next.left;
                }
                curr = curr.next;
            }
            if (curr.left != null) curr.left.next = curr.right;
            node = node.left;
        }
    }
}
