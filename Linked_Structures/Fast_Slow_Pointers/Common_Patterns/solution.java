/**
 * Fast/Slow Pointers - Linked List Pattern
 * 
 * Problem: Use two pointers moving at different speeds.
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */

class ListNode {
    int val;
    ListNode next;
    ListNode(int val) { this.val = val; }
}

public class Solution {
    
    /**
     * Detect if linked list has a cycle.
     */
    public static boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) return false;
        
        ListNode slow = head, fast = head;
        
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) return true;
        }
        
        return false;
    }
    
    /**
     * Find the middle node.
     */
    public static ListNode findMiddle(ListNode head) {
        if (head == null) return null;
        
        ListNode slow = head, fast = head;
        
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        
        return slow;
    }
    
    /**
     * Find kth node from end.
     */
    public static ListNode findKthFromEnd(ListNode head, int k) {
        ListNode fast = head;
        
        for (int i = 0; i < k; i++) {
            if (fast == null) return null;
            fast = fast.next;
        }
        
        ListNode slow = head;
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        
        return slow;
    }
    
    /**
     * Check if linked list is palindrome.
     */
    public static boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) return true;
        
        // Find middle
        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        
        // Reverse second half
        ListNode secondHalf = reverseLinkedList(slow.next);
        
        // Compare
        ListNode firstHalf = head;
        while (secondHalf != null) {
            if (firstHalf.val != secondHalf.val) return false;
            firstHalf = firstHalf.next;
            secondHalf = secondHalf.next;
        }
        
        return true;
    }
    
    private static ListNode reverseLinkedList(ListNode head) {
        ListNode prev = null, curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }
    
    // Helper to create list
    private static ListNode createList(int[] values) {
        if (values.length == 0) return null;
        ListNode head = new ListNode(values[0]);
        ListNode curr = head;
        for (int i = 1; i < values.length; i++) {
            curr.next = new ListNode(values[i]);
            curr = curr.next;
        }
        return head;
    }

    public static void main(String[] args) {
        System.out.println("Find Middle:");
        ListNode head = createList(new int[]{1, 2, 3, 4, 5});
        ListNode middle = findMiddle(head);
        System.out.println("  Middle: " + (middle != null ? middle.val : null));
        
        System.out.println("\nFind 2nd from End:");
        ListNode kth = findKthFromEnd(head, 2);
        System.out.println("  Result: " + (kth != null ? kth.val : null));
    }
}
