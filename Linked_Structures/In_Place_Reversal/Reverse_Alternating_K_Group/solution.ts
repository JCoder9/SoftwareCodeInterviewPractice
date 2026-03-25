/** In-Place Reversal - Reverse Alternating K-Group. Time: O(n), Space: O(1) */

class ListNode { val: number; next: ListNode | null; constructor(val: number = 0, next: ListNode | null = null) { this.val = val; this.next = next; } }

function reverseAlternatingKGroup(head: ListNode | null, k: number): ListNode | null {
    if (!head || k <= 1) return head;
    
    const dummy = new ListNode(0);
    dummy.next = head;
    let prevGroup: ListNode | null = dummy;
    
    while (true) {
        let kthNode: ListNode | null = prevGroup;
        for (let i = 0; i < k; i++) {
            kthNode = kthNode!.next;
            if (!kthNode) return dummy.next;
        }
        
        let prev: ListNode | null = kthNode.next;
        let current: ListNode | null = prevGroup!.next;
        
        for (let i = 0; i < k; i++) {
            const nextTemp = current!.next;
            current!.next = prev;
            prev = current;
            current = nextTemp;
        }
        
        const temp = prevGroup!.next;
        prevGroup!.next = prev;
        prevGroup = temp;
        
        for (let i = 0; i < k; i++) {
            if (!prevGroup) return dummy.next;
            prevGroup = prevGroup.next;
        }
        
        if (!prevGroup) return dummy.next;
    }
}

export { ListNode, reverseAlternatingKGroup };
