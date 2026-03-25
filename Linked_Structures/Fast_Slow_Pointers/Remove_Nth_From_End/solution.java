/**
 * Fast/Slow Pointers - Remove Nth From End Pattern
 * 
 * Related LeetCode Problems:
 * - LC 19: Remove Nth Node From End of List (Medium)
 * - LC 61: Rotate List (Medium)  
 * - LC 83: Remove Duplicates from Sorted List (Easy)
 * - LC 82: Remove Duplicates from Sorted List II (Medium)
 * - LC 203: Remove Linked List Elements (Easy)
 * 
 * Time: O(n), Space: O(1)
 */

class ListNode {
    int val;
    ListNode next;
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

class Solution {
    /**
     * LC 19: Remove Nth Node From End
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        ListNode slow = dummy;
        ListNode fast = dummy;
        
        // Move fast n+1 steps ahead
        for (int i = 0; i <= n; i++) {
            fast = fast.next;
        }
        
        // Move both until fast reaches end
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        
        // Remove the node
        slow.next = slow.next.next;
        
        return dummy.next;
    }
    
    /**
     * LC 61: Rotate List
     */
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) {
            return head;
        }
        
        // Find length
        int length = 1;
        ListNode tail = head;
        while (tail.next != null) {
            tail = tail.next;
            length++;
        }
        
        // Normalize k
        k = k % length;
        if (k == 0) {
            return head;
        }
        
        // Find (length - k)th node
        ListNode fast = head;
        for (int i = 0; i < k; i++) {
            fast = fast.next;
        }
        
        ListNode slow = head;
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }
        
        // Rotate
        ListNode newHead = slow.next;
        slow.next = null;
        fast.next = head;
        
        return newHead;
    }
    
    /**
     * LC 83: Remove Duplicates from Sorted List
     */
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return head;
        }
        
        ListNode current = head;
        
        while (current != null && current.next != null) {
            if (current.val == current.next.val) {
                current.next = current.next.next;
            } else {
                current = current.next;
            }
        }
        
        return head;
    }
    
    /**
     * LC 82: Remove Duplicates from Sorted List II
     */
    public ListNode deleteDuplicatesII(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        ListNode prev = dummy;
        ListNode current = head;
        
        while (current != null) {
            if (current.next != null && current.val == current.next.val) {
                while (current.next != null && current.val == current.next.val) {
                    current = current.next;
                }
                prev.next = current.next;
            } else {
                prev = prev.next;
            }
            current = current.next;
        }
        
        return dummy.next;
    }
    
    /**
     * LC 203: Remove Linked List Elements
     */
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        ListNode current = dummy;
        
        while (current.next != null) {
            if (current.next.val == val) {
                current.next = current.next.next;
            } else {
                current = current.next;
            }
        }
        
        return dummy.next;
    }
    
    // Test
    public static void main(String[] args) {
        Solution sol = new Solution();
        
        // Test removeNthFromEnd
        System.out.println("Testing removeNthFromEnd:");
        ListNode head = new ListNode(1, new ListNode(2, 
            new ListNode(3, new ListNode(4, new ListNode(5)))));
        sol.removeNthFromEnd(head, 2);
        
        // Test rotateRight
        System.out.println("\nTesting rotateRight:");
        head = new ListNode(1, new ListNode(2, 
            new ListNode(3, new ListNode(4, new ListNode(5)))));
        sol.rotateRight(head, 2);
        
        // Test deleteDuplicates
        System.out.println("\nTesting deleteDuplicates:");
        head = new ListNode(1, new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(3)))));
        sol.deleteDuplicates(head);
    }
}
