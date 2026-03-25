/** In-Place Reversal - Reverse Between. LC 92. Time: O(n), Space: O(1) */

class ListNode { int val; ListNode next; ListNode(int val) { this.val = val; } ListNode(int val, ListNode next) { this.val = val; this.next = next; } }

class Solution {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null || m == n) return head;
        
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prevM = dummy;
        
        for (int i = 0; i < m - 1; i++) {
            prevM = prevM.next;
        }
        
        ListNode prev = null, current = prevM.next;
        
        for (int i = 0; i < n - m + 1; i++) {
            ListNode nextTemp = current.next;
            current.next = prev;
            prev = current;
            current = nextTemp;
        }
        
        prevM.next.next = current;
        prevM.next = prev;
        
        return dummy.next;
    }
    
    public static void main(String[] args) {
        Solution sol = new Solution();
        // Test code here
    }
}
