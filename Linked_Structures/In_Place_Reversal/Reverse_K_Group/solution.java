/**
 * In-Place Reversal - Reverse K-Group (LC 25)
 * 
 * Problem: Reverse nodes of linked list k at a time.
 *          Example: 1→2→3→4→5, k=3 → 3→2→1→4→5
 * 
 * Pattern: Check if k nodes available, reverse them, recurse on rest
 * 
 * Related LeetCode Problems:
 * - LC 25: Reverse Nodes in k-Group (Hard) ⭐⭐⭐
 * - LC 206: Reverse Linked List (Easy)
 * - LC 92: Reverse Linked List II (Medium)
 * 
 * Time Complexity: O(n) - visit each node once
 * Space Complexity: O(1) iterative or O(n/k) recursive
 */

// ─────────────────────────────────────────────────────────────────────────────
// NAIVE APPROACH (Brute Force) — O(n) time | O(n) space
// ─────────────────────────────────────────────────────────────────────────────
// INTERVIEW SCRIPT:
//   1. Describe:   "Brute force converts to array, reverses k-groups, rebuilds —
//                  O(n) but extra O(n) space"
//   2. Problem:    "For n=10,000: allocates 10K-element array; not in-place"
//   3. Transition: "Reverse in-place with pointer manipulation — O(n), O(1) space"
//
// // Conceptual - would convert to array and back
// public ListNode reverseKGroupNaive(ListNode head, int k) {
//     // Convert linked list to array
//     // Reverse k-groups in array
//     // Rebuild linked list from array
//     return newHead;
// }
// ─────────────────────────────────────────────────────────────────────────────

/** In-Place Reversal - Reverse K-Group. LC 25. Time: O(n), Space: O(n/k) recursive */

class ListNode { int val; ListNode next; ListNode(int val) { this.val = val; } }

class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k <= 1) return head;
        
        int count = 0;
        ListNode current = head;
        while (current != null && count < k) {
            current = current.next;
            count++;
        }
        
        if (count == k) {
            ListNode prev = null;
            current = head;
            for (int i = 0; i < k; i++) {
                ListNode nextTemp = current.next;
                current.next = prev;
                prev = current;
                current = nextTemp;
            }
            
            head.next = reverseKGroup(current, k);
            return prev;
        }
        
        return head;
    }
}
