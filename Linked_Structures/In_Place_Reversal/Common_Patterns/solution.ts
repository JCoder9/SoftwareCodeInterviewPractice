/**
 * In-Place Linked List Reversal - Pointer Manipulation Pattern
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */

class ListNode {
    val: number;
    next: ListNode | null;
    constructor(val: number = 0, next: ListNode | null = null) {
        this.val = val;
        this.next = next;
    }
}

function reverseLinkedList(head: ListNode | null): ListNode | null {
    let prev: ListNode | null = null;
    let curr: ListNode | null = head;
    
    while (curr) {
        const nextNode = curr.next;
        curr.next = prev;
        prev = curr;
        curr = nextNode;
    }
    
    return prev;
}

function reverseBetween(head: ListNode | null, left: number, right: number): ListNode | null {
    if (!head || left === right) return head;
    
    const dummy = new ListNode(0, head);
    let prevLeft: ListNode = dummy;
    
    for (let i = 0; i < left - 1; i++) {
        prevLeft = prevLeft.next!;
    }
    
    let curr = prevLeft.next!;
    for (let i = 0; i < right - left; i++) {
        const nextNode = curr.next!;
        curr.next = nextNode.next;
        nextNode.next = prevLeft.next;
        prevLeft.next = nextNode;
    }
    
    return dummy.next;
}

function swapPairs(head: ListNode | null): ListNode | null {
    const dummy = new ListNode(0, head);
    let prev: ListNode = dummy;
    
    while (prev.next && prev.next.next) {
        const first = prev.next;
        const second = prev.next.next;
        
        first.next = second.next;
        second.next = first;
        prev.next = second;
        
        prev = first;
    }
    
    return dummy.next;
}

function createList(values: number[]): ListNode | null {
    if (values.length === 0) return null;
    const head = new ListNode(values[0]);
    let curr = head;
    for (let i = 1; i < values.length; i++) {
        curr.next = new ListNode(values[i]);
        curr = curr.next;
    }
    return head;
}

function listToString(head: ListNode | null): string {
    const values: number[] = [];
    while (head) {
        values.push(head.val);
        head = head.next;
    }
    return `[${values.join(",")}]`;
}

// Test cases
if (require.main === module) {
    console.log("Reverse Entire List:");
    let head = createList([1, 2, 3, 4, 5]);
    let reversed = reverseLinkedList(head);
    console.log("  " + listToString(reversed));
    
    console.log("\nSwap Pairs:");
    head = createList([1, 2, 3, 4]);
    let swapped = swapPairs(head);
    console.log("  " + listToString(swapped));
}

export { ListNode, reverseLinkedList, reverseBetween, swapPairs };
