package org.ml.leetcode;

import org.ml.dataStructer.ListNode;

// 链表中删除值为 val 的数
public class RemoveElements {
    public ListNode removeElements(ListNode head, int val) {
        ListNode current = head;
        while (current != null && current.val == val) {
            current = current.next;
            head.next = null;
            head = current;
        }
        while (current != null && current.next != null) {
            if (current.next.val == val) {
                ListNode temp = current.next;
                current.next = current.next.next;
                temp.next = null;
            }else {
                current = current.next;
            }
        }
        return head;
    }
}
