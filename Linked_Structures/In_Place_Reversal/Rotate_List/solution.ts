/** In-Place Reversal - Rotate List. LC 61. Time: O(n), Space: O(1) */

class ListNode { val: number; next: ListNode | null; constructor(val: number = 0, next: ListNode | null = null) { this.val = val; this.next = next; } }

function rotateRight(head: ListNode | null, k: number): ListNode | null {
    if (!head || !head.next || k === 0) return head;
    
    let length = 1;
    let tail: ListNode = head;
    while (tail.next) {
        tail = tail.next;
        length++;
    }
    
    k = k % length;
    if (k === 0) return head;
    
    tail.next = head;
    
    const steps = length - k;
    let newTail: ListNode = head;
    for (let i = 0; i < steps - 1; i++) {
        newTail = newTail.next!;
    }
    
    const newHead = newTail.next;
    newTail.next = null;
    
    return newHead;
}

export { ListNode, rotateRight };
