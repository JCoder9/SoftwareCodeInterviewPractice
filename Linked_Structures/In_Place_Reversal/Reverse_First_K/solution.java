/** In-Place Reversal - Reverse First K Pattern. LC 206, 92, 24. Time: O(n), Space: O(1) */

class ListNode { int val; ListNode next; ListNode(int val) { this.val = val; } ListNode(int val, ListNode next) { this.val = val; this.next = next; } }

class Solution {
    public ListNode reverseFirstK(ListNode head, int k) {
        if (head == null || k <= 1) return head;
        
        ListNode prev = null, current = head;
        int count = 0;
        
        while (current != null && count < k) {
            ListNode nextTemp = current.next;
            current.next = prev;
            prev = current;
            current = nextTemp;
            count++;
        }
        
        if (head != null) head.next = current;
        return prev;
    }
    
    public ListNode swapPairs(ListNode head) {
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
    
    private String printList(ListNode head) {
        StringBuilder sb = new StringBuilder();
        while (head != null) {
            sb.append(head.val);
            if (head.next != null) sb.append(" -> ");
            head = head.next;
        }
        return sb.toString();
    }
    
    public static void main(String[] args) {
        Solution sol = new Solution();
        
        System.out.println("Testing reverseFirstK:");
        ListNode head = sol.createList(new int[]{1, 2, 3, 4, 5});
        System.out.println("Original: " + sol.printList(head));
        ListNode result = sol.reverseFirstK(head, 3);
        System.out.println("After reversing first 3: " + sol.printList(result));
        
        System.out.println("\nTesting swapPairs:");
        head = sol.createList(new int[]{1, 2, 3, 4});
        System.out.println("Original: " + sol.printList(head));
        result = sol.swapPairs(head);
        System.out.println("After swapping pairs: " + sol.printList(result));
    }
}
