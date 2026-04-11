/**
 * Fast/Slow Pointers - Find Middle Pattern
 * 
 * Problem: Find the middle node of a linked list. For even length, return the second middle node.
 * 
 * Pattern: Fast pointer moves 2 steps, slow moves 1 step. When fast reaches end, slow is at middle.
 * 
 * Related LeetCode Problems:
 * - LC 876: Middle of the Linked List (Easy)
 * - LC 2095: Delete the Middle Node of a Linked List (Medium)
 * - LC 143: Reorder List (Medium)
 * 
 * Time Complexity: O(n) - single pass through list
 * Space Complexity: O(1) - only two pointers
 */

// ─────────────────────────────────────────────────────────────────────────
// NAIVE APPROACH (Brute Force) - O(n) time | O(1) space
// ─────────────────────────────────────────────────────────────────────────
// INTERVIEW SCRIPT:
//   1. Describe:   "Brute force counts the length first, then traverses to length/2
//                   — O(n) time but requires two passes"
//   2. Problem:    "Two passes through the list; can we do it in one pass?"
//   3. Transition: "With fast/slow pointers, fast moves 2x speed. When fast reaches
//                   end, slow is at middle — same O(n) but single pass, more elegant"
//
// public ListNode middleNodeNaive(ListNode head) {
//     // First pass: count length
//     int length = 0;
//     ListNode curr = head;
//     while (curr != null) {
//         length++;
//         curr = curr.next;
//     }
//     
//     // Second pass: go to middle
//     int mid = length / 2;
//     curr = head;
//     for (int i = 0; i < mid; i++) {
//         curr = curr.next;
//     }
//     return curr;
// }
// ─────────────────────────────────────────────────────────────────────────

class ListNode {
    int val;
    ListNode next;
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

class Solution {
    /**
     * LC 876: Middle of the Linked List
     * Returns second middle for even-length lists
     */
    public ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        
        return slow;  // Slow is at middle
    }
    
    /**
     * Return first middle for even-length lists
     */
    public ListNode middleNodeFirst(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        
        ListNode slow = head;
        ListNode fast = head.next.next;
        
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        
        return slow;
    }
    
    /**
     * LC 2095: Delete the Middle Node
     */
    public ListNode deleteMiddle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        
        ListNode slow = head;
        ListNode fast = head;
        ListNode prev = null;
        
        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        
        // Delete middle node
        if (prev != null) {
            prev.next = slow.next;
        }
        
        return head;
    }
    
    /**
     * Split linked list into two halves
     */
    public ListNode[] splitList(ListNode head) {
        if (head == null || head.next == null) {
            return new ListNode[]{head, null};
        }
        
        ListNode slow = head;
        ListNode fast = head;
        ListNode prev = null;
        
        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        
        // Split the list
        if (prev != null) {
            prev.next = null;
        }
        
        return new ListNode[]{head, slow};
    }
    
    // Helper to print list
    private String printList(ListNode head) {
        StringBuilder sb = new StringBuilder();
        while (head != null) {
            sb.append(head.val);
            if (head.next != null) sb.append(" -> ");
            head = head.next;
        }
        return sb.toString();
    }
    
    // Test cases
    public static void main(String[] args) {
        Solution sol = new Solution();
        
        // Test middleNode (odd length)
        System.out.println("Testing middleNode (odd length):");
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, 
            new ListNode(4, new ListNode(5)))));
        
        ListNode result = sol.middleNode(head);
        System.out.println("Middle node value: " + result.val);  // 3
        System.out.println("List from middle: " + sol.printList(result));
        
        // Test deleteMiddle
        System.out.println("\nTesting deleteMiddle:");
        head = new ListNode(1, new ListNode(2, new ListNode(3, 
            new ListNode(4, new ListNode(5)))));
        System.out.println("Before: " + sol.printList(head));
        result = sol.deleteMiddle(head);
        System.out.println("After:  " + sol.printList(result));
        
        // Test splitList
        System.out.println("\nTesting splitList:");
        head = new ListNode(1, new ListNode(2, new ListNode(3, 
            new ListNode(4, new ListNode(5, new ListNode(6))))));
        ListNode[] parts = sol.splitList(head);
        System.out.println("First half:  " + sol.printList(parts[0]));
        System.out.println("Second half: " + sol.printList(parts[1]));
    }
}
