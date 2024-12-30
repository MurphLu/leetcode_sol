package org.ml.leetcode.daily;

public class IsSubPath {
     public static class TreeNode {
         int val;
         TreeNode left;
         TreeNode right;
         TreeNode() {}
         TreeNode(int val) { this.val = val; }
         TreeNode(int val, TreeNode left, TreeNode right) {
             this.val = val;
             this.left = left;
             this.right = right;
         }
     }

    public static class ListNode {
         int val;
         ListNode next;
         ListNode() {}

        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }

    public boolean isSubPath(ListNode head, TreeNode root) {
        return subMatch(head, true, root);
    }

    private boolean subMatch(ListNode head, boolean isHead, TreeNode root) {
        if (head == null) {
            return true;
        }
        if (root == null) {
            return false;
        }
        if (head.val == root.val) {
            if(subMatch(head.next, false, root.left)) {
                return true;
            }
            if (subMatch(head.next, false, root.right)){
                return true;
            }
        }
        if (!isHead) {
            return false;
        }
        if(subMatch(head, true, root.left)) {
            return true;
        }
        return subMatch(head, true, root.right);
    }
}
