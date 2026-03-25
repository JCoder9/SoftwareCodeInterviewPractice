/**
 * In-Place Linked List Reversal - Pointer Manipulation Pattern
 * 
 * Problem: Reverse linked list or portions by manipulating pointers in place.
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
     * Reverse entire linked list.
     */
    public static ListNode reverseLinkedList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        
        while (curr != null) {
            ListNode nextNode = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextNode;
        }
        
        return prev;
    }
    
    /**
     * Reverse portion from left to right (1-indexed).
     */
    public static ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null || left == right) return head;
        
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prevLeft = dummy;
        
        for (int i = 0; i < left - 1; i++) {
            prevLeft = prevLeft.next;
        }
        
        ListNode curr = prevLeft.next;
        for (int i = 0; i < right - left; i++) {
            ListNode nextNode = curr.next;
            curr.next = nextNode.next;
            nextNode.next = prevLeft.next;
            prevLeft.next = nextNode;
        }
        
        return dummy.next;
    }
    
    /**
     * Swap every two adjacent nodes.
     */
    public static ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        
        while (prev.next != null && prev.next.next != null) {
            ListNode first = prev.next;
            ListNode second = prev.next.next;
            
            first.next = second.next;
            second.next = first;
            prev.next = second;
            
            prev = first;
        }
        
        return dummy.next;
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
    
    // Helper to print list
    private static String listToString(ListNode head) {
        StringBuilder sb = new StringBuilder("[");
        while (head != null) {
            sb.append(head.val);
            if (head.next != null) sb.append(",");
            head = head.next;
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println("Reverse Entire List:");
        ListNode head = createList(new int[]{1, 2, 3, 4, 5});
        ListNode reversed = reverseLinkedList(head);
        System.out.println("  " + listToString(reversed));
        
        System.out.println("\nSwap Pairs:");
        head = createList(new int[]{1, 2, 3, 4});
        ListNode swapped = swapPairs(head);
        System.out.println("  " + listToString(swapped));
    }
}
