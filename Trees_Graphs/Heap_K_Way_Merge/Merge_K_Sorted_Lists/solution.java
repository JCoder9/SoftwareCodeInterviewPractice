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
