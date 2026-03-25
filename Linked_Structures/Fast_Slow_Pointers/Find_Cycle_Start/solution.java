/**
 * Fast/Slow Pointers - Find Cycle Start Pattern
 * 
 * Related LeetCode Problems:
 * - LC 142: Linked List Cycle II (Medium)
 * - LC 287: Find the Duplicate Number (Medium)
 * 
 * Floyd's Cycle Detection Algorithm
 * Time: O(n), Space: O(1)
 */

class ListNode {
    int val;
    ListNode next;
    ListNode(int val) { this.val = val; }
}

class Solution {
    /**
     * LC 142: Linked List Cycle II
     */
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        
        // Step 1: Detect if cycle exists
        ListNode slow = head;
        ListNode fast = head;
        boolean hasCycle = false;
        
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            
            if (slow == fast) {
                hasCycle = true;
                break;
            }
        }
        
        if (!hasCycle) {
            return null;
        }
        
        // Step 2: Find cycle start
        slow = head;  // Reset slow to head
        
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        
        return slow;  // Cycle start
    }
    
    /**
     * Find cycle start and return cycle length
     */
    public int[] detectCycleWithLength(ListNode head) {
        if (head == null || head.next == null) {
            return new int[]{-1, 0};  // No cycle
        }
        
        // Detect cycle
        ListNode slow = head;
        ListNode fast = head;
        boolean hasCycle = false;
        
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            
            if (slow == fast) {
                hasCycle = true;
                break;
            }
        }
        
        if (!hasCycle) {
            return new int[]{-1, 0};
        }
        
        // Calculate cycle length
        int cycleLength = 1;
        ListNode current = slow.next;
        while (current != slow) {
            cycleLength++;
            current = current.next;
        }
        
        // Find cycle start
        slow = head;
        int position = 0;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
            position++;
        }
        
        return new int[]{position, cycleLength};
    }
    
    /**
     * LC 287: Find the Duplicate Number
     */
    public int findDuplicate(int[] nums) {
        // Phase 1: Detect cycle
        int slow = nums[0];
        int fast = nums[0];
        
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);
        
        // Phase 2: Find entrance to cycle
        slow = nums[0];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        
        return slow;
    }
    
    // Test cases
    public static void main(String[] args) {
        Solution sol = new Solution();
        
        // Test detectCycle
        System.out.println("Testing detectCycle:");
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node3;
        
        ListNode result = sol.detectCycle(node1);
        System.out.println("Cycle starts at: " + 
            (result != null ? result.val : "null"));  // 3
        
        // Test with length
        System.out.println("\nTesting detectCycleWithLength:");
        int[] info = sol.detectCycleWithLength(node1);
        System.out.println("Position: " + info[0] + ", Length: " + info[1]);
        
        // Test findDuplicate
        System.out.println("\nTesting findDuplicate:");
        System.out.println("Duplicate: " + sol.findDuplicate(new int[]{1,3,4,2,2}));  // 2
        System.out.println("Duplicate: " + sol.findDuplicate(new int[]{3,1,3,4,2}));  // 3
    }
}
