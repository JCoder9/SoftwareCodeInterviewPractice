/**
 * Fast/Slow Pointers - Reorder List Pattern
 * 
 * Related LeetCode Problems:
 * - LC 143: Reorder List (Medium)
 * - LC 2074: Reverse Nodes in Even Length Groups (Medium)
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
     * LC 143: Reorder List
     */
    public void reorderList(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }
        
        // Find middle
        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        
        // Reverse second half
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
        
        // Merge
        ListNode firstHalf = head;
        while (secondHalf != null) {
            ListNode temp1 = firstHalf.next;
            ListNode temp2 = secondHalf.next;
            
            firstHalf.next = secondHalf;
            secondHalf.next = temp1;
            
            firstHalf = temp1;
            secondHalf = temp2;
        }
    }
    
    /**
     * LC 2074: Reverse Nodes in Even Length Groups
     */
    public ListNode reverseEvenLengthGroups(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prevGroupEnd = dummy;
        int groupLen = 1;
        
        while (prevGroupEnd.next != null) {
            ListNode groupStart = prevGroupEnd.next;
            ListNode groupEnd = groupStart;
            int actualLen = 1;
            
            for (int i = 0; i < groupLen - 1; i++) {
                if (groupEnd.next == null) break;
                groupEnd = groupEnd.next;
                actualLen++;
            }
            
            ListNode nextGroupStart = groupEnd.next;
            
            if (actualLen % 2 == 0) {
                ListNode prev = nextGroupStart;
                ListNode current = groupStart;
                
                for (int i = 0; i < actualLen; i++) {
                    ListNode nextTemp = current.next;
                    current.next = prev;
                    prev = current;
                    current = nextTemp;
                }
                
                prevGroupEnd.next = prev;
                prevGroupEnd = groupStart;
            } else {
                prevGroupEnd = groupEnd;
            }
            
            groupLen++;
        }
        
        return dummy.next;
    }
    
    // Test
    public static void main(String[] args) {
        Solution sol = new Solution();
        
        // Test reorderList
        System.out.println("Testing reorderList:");
        ListNode head = new ListNode(1, new ListNode(2, 
            new ListNode(3, new ListNode(4, new ListNode(5)))));
        sol.reorderList(head);
        
        // Test reverseEvenLengthGroups
        System.out.println("\nTesting reverseEvenLengthGroups:");
        head = new ListNode(1, new ListNode(2, new ListNode(3, 
            new ListNode(4, new ListNode(5, new ListNode(6, new ListNode(7)))))));
        sol.reverseEvenLengthGroups(head);
    }
}
