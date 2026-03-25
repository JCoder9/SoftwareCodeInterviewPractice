/** In-Place Reversal - Rotate List. LC 61. Time: O(n), Space: O(1) */

class ListNode { int val; ListNode next; ListNode(int val) { this.val = val; } }

class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) return head;
        
        int length = 1;
        ListNode tail = head;
        while (tail.next != null) {
            tail = tail.next;
            length++;
        }
        
        k = k % length;
        if (k == 0) return head;
        
        tail.next = head;
        
        int steps = length - k;
        ListNode newTail = head;
        for (int i = 0; i < steps - 1; i++) {
            newTail = newTail.next;
        }
        
        ListNode newHead = newTail.next;
        newTail.next = null;
        
        return newHead;
    }
}
