/** In-Place Reversal - Palindrome Using Reversal. LC 234. Time: O(n), Space: O(1) */

class ListNode { int val; ListNode next; ListNode(int val) { this.val = val; } }

class Solution {
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) return true;
        
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
        boolean result = true;
        while (secondHalf != null) {
            if (firstHalf.val != secondHalf.val) {
                result = false;
                break;
            }
            firstHalf = firstHalf.next;
            secondHalf = secondHalf.next;
        }
        
        return result;
    }
}
