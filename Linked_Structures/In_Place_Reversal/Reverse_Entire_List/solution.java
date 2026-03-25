/**
 * In-Place Reversal - Reverse Entire List Pattern
 * 
 * Related LeetCode Problems:
 * - LC 206: Reverse Linked List (Easy)
 * - LC 92, 25: Use this as building block
 * 
 * Time: O(n), Space: O(1) iterative, O(n) recursive
 */

class ListNode {
    int val;
    ListNode next;
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

class Solution {
    /**
     * LC 206: Reverse Linked List (Iterative)
     */
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode current = head;
        
        while (current != null) {
            // Save next node
            ListNode nextTemp = current.next;
            
            // Reverse pointer
            current.next = prev;
            
            // Move forward
            prev = current;
            current = nextTemp;
        }
        
        return prev;
    }
    
    /**
     * LC 206: Reverse Linked List (Recursive)
     */
    public ListNode reverseListRecursive(List Node head) {
        // Base case
        if (head == null || head.next == null) {
            return head;
        }
        
        // Reverse rest
        ListNode newHead = reverseListRecursive(head.next);
        
        // Fix pointers
        head.next.next = head;
        head.next = null;
        
        return newHead;
    }
    
    /**
     * LC 206: Reverse Linked List (Tail Recursion)
     */
    public ListNode reverseListRecursiveCleaner(ListNode head) {
        return reverseHelper(head, null);
    }
    
    private ListNode reverseHelper(ListNode head, ListNode prev) {
        if (head == null) {
            return prev;
        }
        
        ListNode nextTemp = head.next;
        head.next = prev;
        
        return reverseHelper(nextTemp, head);
    }
    
    // Helper to create list
    private ListNode createList(int[] arr) {
        if (arr.length == 0) return null;
        
        ListNode head = new ListNode(arr[0]);
        ListNode current = head;
        for (int i = 1; i < arr.length; i++) {
            current.next = new ListNode(arr[i]);
            current = current.next;
        }
        
        return head;
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
    
    // Test
    public static void main(String[] args) {
        Solution sol = new Solution();
        
        // Test reverseList (iterative)
        System.out.println("Testing reverseList (iterative):");
        ListNode head = sol.createList(new int[]{1, 2, 3, 4, 5});
        System.out.println("Original: " + sol.printList(head));
        ListNode reversed = sol.reverseList(head);
        System.out.println("Reversed: " + sol.printList(reversed));
        
        // Test reverseListRecursive
        System.out.println("\nTesting reverseList (recursive):");
        head = sol.createList(new int[]{1, 2, 3, 4, 5});
        System.out.println("Original: " + sol.printList(head));
        reversed = sol.reverseListRecursive(head);
        System.out.println("Reversed: " + sol.printList(reversed));
        
        // Test reverseListRecursiveCleaner
        System.out.println("\nTesting reverseList (tail recursive):");
        head = sol.createList(new int[]{1, 2, 3, 4, 5});
        System.out.println("Original: " + sol.printList(head));
        reversed = sol.reverseListRecursiveCleaner(head);
        System.out.println("Reversed: " + sol.printList(reversed));
    }
}
