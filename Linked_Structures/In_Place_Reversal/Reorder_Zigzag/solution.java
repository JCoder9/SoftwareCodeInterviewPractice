/** In-Place Reversal - Reorder Zigzag. LC 143. Time: O(n), Space: O(1) */

class ListNode { int val; ListNode next; ListNode(int val) { this.val = val; } }

class Solution {
    public void reorderList(ListNode head) {
        if (head == null || head.next == null) return;
        
        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        
        ListNode secondHalf = slow.next;
        slow.next = null;
        
        ListNode prev = null, current = secondHalf;
        while (current != null) {
            ListNode nextTemp = current.next;
            current.next = prev;
            prev = current;
            current = nextTemp;
        }
        secondHalf = prev;
        
        ListNode firstHalf = head;
        while (secondHalf != null) {
            ListNode temp1 = firstHalf.next;
            ListNode temp2 = secondHalf.next;
            
            firstHalf.next = secondHalf;
            secondHalf.next = temp1;
            
            firstHalf = temp1;
            secondHalf = temp2;
        }
    }
}
