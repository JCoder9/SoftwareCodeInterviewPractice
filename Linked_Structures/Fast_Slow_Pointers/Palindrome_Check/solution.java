/**
 * Fast/Slow Pointers - Palindrome Check Pattern
 * 
 * Related LeetCode Problems:
 * - LC 234: Palindrome Linked List (Easy)
 * - LC 2130: Maximum Twin Sum of a Linked List (Medium)
 * - LC 1721: Swapping Nodes in a Linked List (Medium)
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
     * LC 234: Palindrome Linked List
     */
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        
        // Find middle
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        
        // Reverse second half
        ListNode prev = null, current = slow;
        while (current != null) {
            ListNode nextNode = current.next;
            current.next = prev;
            prev = current;
            current = nextNode;
        }
        
        // Compare
        ListNode left = head, right = prev;
        while (right != null) {
            if (left.val != right.val) {
                return false;
            }
            left = left.next;
            right = right.next;
        }
        
        return true;
    }
    
    /**
     * LC 2130: Maximum Twin Sum
     */
    public int pairSum(ListNode head) {
        // Find middle
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        
        // Reverse second half
        ListNode prev = null, current = slow;
        while (current != null) {
            ListNode nextNode = current.next;
            current.next = prev;
            prev = current;
            current = nextNode;
        }
        
        // Calculate max sum
        int maxSum = 0;
        ListNode first = head, second = prev;
        while (second != null) {
            maxSum = Math.max(maxSum, first.val + second.val);
            first = first.next;
            second = second.next;
        }
        
        return maxSum;
    }
    
    /**
     * LC 1721: Swapping Nodes in a Linked List
     */
    public ListNode swapNodes(ListNode head, int k) {
        // Find kth node from beginning
        ListNode first = head;
        for (int i = 0; i < k - 1; i++) {
            first = first.next;
        }
        
        // Find kth from end
        ListNode slow = head, fast = first;
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }
        
        // Swap values
        int temp = first.val;
        first.val = slow.val;
        slow.val = temp;
        
        return head;
    }
    
    // Test
    public static void main(String[] args) {
        Solution sol = new Solution();
        
        // Test isPalindrome
        System.out.println("Testing isPalindrome:");
        ListNode head = new ListNode(1, new ListNode(2, 
            new ListNode(3, new ListNode(2, new ListNode(1)))));
        System.out.println("[1,2,3,2,1] is palindrome: " + sol.isPalindrome(head));
        
        // Test pairSum
        System.out.println("\nTesting pairSum:");
        head = new ListNode(5, new ListNode(4, new ListNode(2, new ListNode(1))));
        System.out.println("Max twin sum: " + sol.pairSum(head));  // 6
        
        // Test swapNodes
        System.out.println("\nTesting swapNodes:");
        head = new ListNode(1, new ListNode(2, 
            new ListNode(3, new ListNode(4, new ListNode(5)))));
        sol.swapNodes(head, 2);
    }
}
