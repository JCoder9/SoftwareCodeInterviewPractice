/**
 * Fast/Slow Pointers - Linked List Pattern
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

function hasCycle(head: ListNode | null): boolean {
    if (!head || !head.next) return false;
    
    let slow: ListNode | null = head;
    let fast: ListNode | null = head;
    
    while (fast && fast.next) {
        slow = slow!.next;
        fast = fast.next.next;
        if (slow === fast) return true;
    }
    
    return false;
}

function findMiddle(head: ListNode | null): ListNode | null {
    if (!head) return null;
    
    let slow: ListNode | null = head;
    let fast: ListNode | null = head;
    
    while (fast && fast.next) {
        slow = slow!.next;
        fast = fast.next.next;
    }
    
    return slow;
}

function findKthFromEnd(head: ListNode | null, k: number): ListNode | null {
    let fast: ListNode | null = head;
    
    for (let i = 0; i < k; i++) {
        if (!fast) return null;
        fast = fast.next;
    }
    
    let slow: ListNode | null = head;
    while (fast) {
        slow = slow!.next;
        fast = fast.next;
    }
    
    return slow;
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

// Test cases
if (require.main === module) {
    console.log("Find Middle:");
    const head = createList([1, 2, 3, 4, 5]);
    const middle = findMiddle(head);
    console.log("  Middle: " + (middle?.val ?? null));
    
    console.log("\nFind 2nd from End:");
    const kth = findKthFromEnd(head, 2);
    console.log("  Result: " + (kth?.val ?? null));
}

export { ListNode, hasCycle, findMiddle, findKthFromEnd };
