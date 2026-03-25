/**
 * Linked List Cycle Detection - Fast/Slow Pointer Pattern (Floyd's Algorithm)
 * 
 * Problem: Detect if a linked list has a cycle.
 * 
 * Pattern: Slow pointer moves 1 step, fast pointer moves 2 steps.
 *          If there's a cycle, they will eventually meet.
 * 
 * Time Complexity: O(n) - worst case visit each node once
 * Space Complexity: O(1) - only two pointers
 */

class ListNode {
    int val;
    ListNode next;
    
    ListNode(int v) { 
        val = v; 
    }
}

public class Solution {
    
    /**
     * Detect if a linked list has a cycle using Floyd's algorithm.
     * 
     * @param head Head of the linked list
     * @return true if cycle exists, false otherwise
     */
    public static boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        
        ListNode slow = head;
        ListNode fast = head;
        
        while (fast != null && fast.next != null) {
            slow = slow.next;        // Move 1 step
            fast = fast.next.next;   // Move 2 steps
            
            if (slow == fast) {      // They met - cycle exists
                return true;
            }
        }
        
        return false;  // fast reached end - no cycle
    }

    /**
     * Helper function to create a linked list with an optional cycle.
     */
    private static ListNode createListWithCycle(int[] values, int cyclePos) {
        if (values.length == 0) return null;
        
        ListNode[] nodes = new ListNode[values.length];
        for (int i = 0; i < values.length; i++) {
            nodes[i] = new ListNode(values[i]);
        }
        
        // Link nodes
        for (int i = 0; i < values.length - 1; i++) {
            nodes[i].next = nodes[i + 1];
        }
        
        // Create cycle if cyclePos is valid
        if (cyclePos >= 0 && cyclePos < values.length) {
            nodes[values.length - 1].next = nodes[cyclePos];
        }
        
        return nodes[0];
    }

    // Test cases
    public static void main(String[] args) {
        Object[][] testCases = {
            {new int[]{3, 2, 0, -4}, 1, true},   // Cycle at position 1
            {new int[]{1, 2}, 0, true},          // Cycle at position 0
            {new int[]{1}, -1, false},           // No cycle
            {new int[]{1, 2, 3}, -1, false}      // No cycle
        };
        
        for (Object[] test : testCases) {
            int[] values = (int[]) test[0];
            int cyclePos = (int) test[1];
            boolean expected = (boolean) test[2];
            
            ListNode head = createListWithCycle(values, cyclePos);
            boolean result = hasCycle(head);
            
            String status = (result == expected) ? "✓" : "✗";
            String cycleDesc = (cyclePos >= 0) ? "cycle at pos " + cyclePos : "no cycle";
            System.out.println(status + " List with " + cycleDesc + ": hasCycle = " + result);
        }
    }
}
