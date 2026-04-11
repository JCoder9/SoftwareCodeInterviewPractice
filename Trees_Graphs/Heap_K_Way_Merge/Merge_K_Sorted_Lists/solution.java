/**
 * Heap/K-Way Merge - Merge K Sorted Lists
 * 
 * Problem: Merge k sorted linked lists into one sorted linked list.
 *          Example: [[1,4,5], [1,3,4], [2,6]] → [1,1,2,3,4,4,5,6]
 * 
 * Pattern: Use min-heap to always pick the smallest element across all k lists.
 *          Add first node from each list to heap, then repeatedly extract min and add its next.
 * 
 * Related LeetCode Problems:
 * - LC 23: Merge k Sorted Lists (Hard) ⭐⭐⭐
 * - LC 378: Kth Smallest Element in Sorted Matrix (Medium)
 * - LC 632: Smallest Range Covering Elements from K Lists (Hard)
 * 
 * Time Complexity: O(N log k) where N = total nodes, k = number of lists
 * Space Complexity: O(k) - heap stores at most k nodes
 */

// ─────────────────────────────────────────────────────────────────────────────
// NAIVE APPROACH (Brute Force) — O(N k) time | O(1) space
// ─────────────────────────────────────────────────────────────────────────────
// INTERVIEW SCRIPT:
//   1. Describe:   "Brute force merges lists one by one: merge(list1, list2), then
//                  merge(result, list3), etc. — O(N k) time"
//   2. Problem:    "For k=1000 lists with 100 nodes each: 100M operations; early
//                  merges reprocess same nodes multiple times"
//   3. Transition: "Min-heap processes each node exactly once; O(log k) per pick
//                  — total O(N log k)"
//
// public ListNode mergeKListsNaive(ListNode[] lists) {
//     if (lists == null || lists.length == 0) return null;
//     ListNode result = lists[0];
//     for (int i = 1; i < lists.length; i++) {
//         result = mergeTwoLists(result, lists[i]);
//     }
//     return result;
// }
// 
// private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
//     ListNode dummy = new ListNode(0);
//     ListNode curr = dummy;
//     while (l1 != null && l2 != null) {
//         if (l1.val < l2.val) {
//             curr.next = l1;
//             l1 = l1.next;
//         } else {
//             curr.next = l2;
//             l2 = l2.next;
//         }
//         curr = curr.next;
//     }
//     curr.next = (l1 != null) ? l1 : l2;
//     return dummy.next;
// }
// ─────────────────────────────────────────────────────────────────────────────

import java.util.PriorityQueue;

class ListNode {
    int val;
    ListNode next;
    ListNode(int val) { this.val = val; }
}

public class Solution {
    /**
     * Heap/K-Way Merge - Merge K Sorted Lists
     * Time: O(N log k), Space: O(k)
     */
    public ListNode mergeKLists(ListNode[] lists) {
        // Min heap based on node values
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>(
            (a, b) -> a.val - b.val
        );
        
        // Add first node from each list
        for (ListNode node : lists) {
            if (node != null) {
                minHeap.offer(node);
            }
        }
        
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        
        while (!minHeap.isEmpty()) {
            ListNode node = minHeap.poll();
            current.next = node;
            current = current.next;
            
            // Add next node from same list
            if (node.next != null) {
                minHeap.offer(node.next);
            }
        }
        
        return dummy.next;
    }
    
    public static void main(String[] args) {
        Solution sol = new Solution();
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(5);
        
        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(3);
        l2.next.next = new ListNode(4);
        
        ListNode l3 = new ListNode(2);
        l3.next = new ListNode(6);
        
        ListNode result = sol.mergeKLists(new ListNode[]{l1, l2, l3});
        
        while (result != null) {
            System.out.print(result.val + (result.next != null ? " -> " : "\n"));
            result = result.next;
        }
    }
}
