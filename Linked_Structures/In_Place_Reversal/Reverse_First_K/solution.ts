/** In-Place Reversal - Reverse First K Pattern. LC 206, 92, 24. Time: O(n), Space: O(1) */

class ListNode {
    val: number;
    next: ListNode | null;
    constructor(val: number = 0, next: ListNode | null = null) {
        this.val = val;
        this.next = next;
    }
}

function reverseFirstK(head: ListNode | null, k: number): ListNode | null {
    if (!head || k <= 1) return head;
    
    let prev: ListNode | null = null;
    let current: ListNode | null = head;
    let count = 0;
    
    while (current && count < k) {
        const nextTemp = current.next;
        current.next = prev;
        prev = current;
        current = nextTemp;
        count++;
    }
    
    if (head) head.next = current;
    return prev;
}

function swapPairs(head: ListNode | null): ListNode | null {
    const dummy = new ListNode(0);
    dummy.next = head;
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

function createList(arr: number[]): ListNode | null {
    if (arr.length === 0) return null;
    const head = new ListNode(arr[0]);
    let current = head;
    for (let i = 1; i < arr.length; i++) {
        current.next = new ListNode(arr[i]);
        current = current.next;
    }
    return head;
}

function printList(head: ListNode | null): string {
    const values: string[] = [];
    while (head) {
        values.push(String(head.val));
        head = head.next;
    }
    return values.join(" -> ");
}

if (require.main === module) {
    console.log("Testing reverseFirstK:");
    let head = createList([1, 2, 3, 4, 5]);
    console.log(`Original: ${printList(head)}`);
    let result = reverseFirstK(head, 3);
    console.log(`After reversing first 3: ${printList(result)}`);
    
    console.log("\nTesting swapPairs:");
    head = createList([1, 2, 3, 4]);
    console.log(`Original: ${printList(head)}`);
    result = swapPairs(head);
    console.log(`After swapping pairs: ${printList(result)}`);
}

export { ListNode, reverseFirstK, swapPairs, createList, printList };
