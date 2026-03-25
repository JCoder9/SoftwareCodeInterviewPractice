/** In-Place Reversal - Reverse Between. LC 92. Time: O(n), Space: O(1) */

class ListNode {
    val: number;
    next: ListNode | null;
    constructor(val: number = 0, next: ListNode | null = null) { this.val = val; this.next = next; }
}

function reverseBetween(head: ListNode | null, m: number, n: number): ListNode | null {
    if (!head || m === n) return head;
    
    const dummy = new ListNode(0);
    dummy.next = head;
    let prevM: ListNode = dummy;
    
    for (let i = 0; i < m - 1; i++) {
        prevM = prevM.next!;
    }
    
    let prev: ListNode | null = null;
    let current: ListNode | null = prevM.next;
    
    for (let i = 0; i < n - m + 1; i++) {
        const nextTemp = current!.next;
        current!.next = prev;
        prev = current;
        current = nextTemp;
    }
    
    prevM.next!.next = current;
    prevM.next = prev;
    
    return dummy.next;
}

export { ListNode, reverseBetween };
