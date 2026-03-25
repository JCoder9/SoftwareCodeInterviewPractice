/** In-Place Reversal - Reverse K-Group. LC 25. Time: O(n), Space: O(n/k) recursive */

class ListNode { int val; ListNode next; ListNode(int val) { this.val = val; } }

class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k <= 1) return head;
        
        int count = 0;
        ListNode current = head;
        while (current != null && count < k) {
            current = current.next;
            count++;
        }
        
        if (count == k) {
            ListNode prev = null;
            current = head;
            for (int i = 0; i < k; i++) {
                ListNode nextTemp = current.next;
                current.next = prev;
                prev = current;
                current = nextTemp;
            }
            
            head.next = reverseKGroup(current, k);
            return prev;
        }
        
        return head;
    }
}
