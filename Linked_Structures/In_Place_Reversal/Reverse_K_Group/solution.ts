/** In-Place Reversal - Reverse K-Group. LC 25. Time: O(n), Space: O(n/k) */

class ListNode { val: number; next: ListNode | null; constructor(val: number = 0, next: ListNode | null = null) { this.val = val; this.next = next; } }

function reverseKGroup(head: ListNode | null, k: number): ListNode | null {
    if (!head || k <= 1) return head;
    
    let count = 0;
    let current: ListNode | null = head;
    while (current && count < k) {
        current = current.next;
        count++;
    }
    
    if (count === k) {
        let prev: ListNode | null = null;
        current = head;
        for (let i = 0; i < k; i++) {
            const nextTemp = current!.next;
            current!.next = prev;
            prev = current;
            current = nextTemp;
        }
        
        head.next = reverseKGroup(current, k);
        return prev;
    }
    
    return head;
}

export { ListNode, reverseKGroup };
