/**
 * Fast/Slow Pointers - Detect Cycle Pattern
 * 
 * Related LeetCode Problems:
 * - LC 141: Linked List Cycle (Easy)
 * - LC 287: Find the Duplicate Number (Medium)
 * - LC 457: Circular Array Loop (Medium)
 * 
 * Pattern:
 * - Slow pointer moves 1 step, fast moves 2 steps
 * - If they meet → cycle exists
 * - If fast reaches null → no cycle
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */

class ListNode {
    int val;
    ListNode next;
    ListNode(int val) { this.val = val; }
}

class Solution {
    /**
     * LC 141: Linked List Cycle
     */
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        
        ListNode slow = head;
        ListNode fast = head;
        
        while (fast != null && fast.next != null) {
            slow = slow.next;           // Move 1 step
            fast = fast.next.next;      // Move 2 steps
            
            if (slow == fast) {         // They met
                return true;
            }
        }
        
        return false;  // Fast reached the end
    }
    
    /**
     * LC 287: Find the Duplicate Number
     * Treat array as implicit linked list
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
    
    /**
     * LC 457: Circular Array Loop
     */
    public boolean circularArrayLoop(int[] nums) {
        int n = nums.length;
        
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                continue;
            }
            
            int slow = i, fast = i;
            boolean forward = nums[i] > 0;
            
            while (true) {
                // Move slow one step
                slow = nextIndex(slow, nums);
                if (nums[slow] == 0 || (nums[slow] > 0) != forward) {
                    break;
                }
                
                // Move fast two steps
                fast = nextIndex(fast, nums);
                if (nums[fast] == 0 || (nums[fast] > 0) != forward) {
                    break;
                }
                fast = nextIndex(fast, nums);
                if (nums[fast] == 0 || (nums[fast] > 0) != forward) {
                    break;
                }
                
                // Check single element loop
                if (slow == nextIndex(slow, nums)) {
                    break;
                }
                
                if (slow == fast) {
                    return true;
                }
            }
            
            // Mark visited elements
            slow = i;
            int val = nums[i];
            while (nums[slow] != 0 && (nums[slow] > 0) == forward) {
                int nextI = nextIndex(slow, nums);
                nums[slow] = 0;
                slow = nextI;
            }
        }
        
        return false;
    }
    
    private int nextIndex(int i, int[] nums) {
        int n = nums.length;
        return ((i + nums[i]) % n + n) % n;  // Handle negative modulo
    }
    
    // Test cases
    public static void main(String[] args) {
        Solution sol = new Solution();
        
        // Test hasCycle
        System.out.println("Testing hasCycle:");
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node2;
        System.out.println("Has cycle: " + sol.hasCycle(node1));  // true
        
        // Test findDuplicate
        System.out.println("\nTesting findDuplicate:");
        System.out.println("Duplicate in [1,3,4,2,2]: " + 
            sol.findDuplicate(new int[]{1,3,4,2,2}));  // 2
        
        // Test circularArrayLoop
        System.out.println("\nTesting circularArrayLoop:");
        System.out.println("Loop in [2,-1,1,2,2]: " + 
            sol.circularArrayLoop(new int[]{2,-1,1,2,2}));  // true
    }
}
