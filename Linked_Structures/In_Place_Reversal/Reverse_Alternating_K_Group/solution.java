/** In-Place Reversal - Reverse Alternating K-Group. Time: O(n), Space: O(1) */

class ListNode { int val; ListNode next; ListNode(int val) { this.val = val; } }

class Solution {
    public ListNode reverseAlternatingKGroup(ListNode head, int k) {
        if (head == null || k <= 1) return head;
        
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prevGroup = dummy;
        
        while (true) {
            ListNode kthNode = prevGroup;
            for (int i = 0; i < k; i++) {
                kthNode = kthNode.next;
                if (kthNode == null) return dummy.next;
            }
            
            ListNode prev = kthNode.next;
            ListNode current = prevGroup.next;
            
            for (int i = 0; i < k; i++) {
                ListNode nextTemp = current.next;
                current.next = prev;
                prev = current;
                current = nextTemp;
            }
            
            ListNode temp = prevGroup.next;
            prevGroup.next = prev;
            prevGroup = temp;
            
            for (int i = 0; i < k; i++) {
                if (prevGroup == null) return dummy.next;
                prevGroup = prevGroup.next;
            }
            
            if (prevGroup == null) return dummy.next;
        }
    }
}
