/** In-Place Reversal - Reorder Zigzag. LC 143. Time: O(n), Space: O(1) */

class ListNode { val: number; next: ListNode | null; constructor(val: number = 0, next: ListNode | null = null) { this.val = val; this.next = next; } }

function reorderList(head: ListNode | null): void {
    if (!head || !head.next) return;
    
    let slow: ListNode | null = head;
    let fast: ListNode | null = head;
    
    while (fast.next && fast.next.next) {
        slow = slow!.next;
        fast = fast.next.next;
    }
    
    let secondHalf: ListNode | null = slow!.next;
    slow!.next = null;
    
    let prev: ListNode | null = null;
    let current: ListNode | null = secondHalf;
    
    while (current) {
        const nextTemp = current.next;
        current.next = prev;
        prev = current;
        current = nextTemp;
    }
    secondHalf = prev;
    
    let firstHalf: ListNode | null = head;
    while (secondHalf) {
        const temp1 = firstHalf!.next;
        const temp2 = secondHalf.next;
        
        firstHalf!.next = secondHalf;
        secondHalf.next = temp1;
        
        firstHalf = temp1;
        secondHalf = temp2;
    }
}

export { ListNode, reorderList };
