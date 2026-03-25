/**
 * Fast/Slow Pointers - Remove Nth From End Pattern
 * 
 * Related LeetCode Problems:
 * - LC 19: Remove Nth Node From End of List (Medium)
 * - LC 61: Rotate List (Medium)
 * - LC 83, 82, 203: Remove Duplicates/Elements
 * 
 * Time: O(n), Space: O(1)
 */

class ListNode {
    val: number;
    next: ListNode | null;
    
    constructor(val: number = 0, next: ListNode | null = null) {
        this.val = val;
        this.next = next;
    }
}

/**
 * LC 19: Remove Nth Node From End
 */
function removeNthFromEnd(head: ListNode | null, n: number): ListNode | null {
    const dummy = new ListNode(0);
    dummy.next = head;
    
    let slow: ListNode | null = dummy;
    let fast: ListNode | null = dummy;
    
    // Move fast n+1 steps ahead
    for (let i = 0; i <= n; i++) {
        fast = fast!.next;
    }
    
    // Move both until fast reaches end
    while (fast) {
        slow = slow!.next;
        fast = fast.next;
    }
    
    // Remove the node
    slow!.next = slow!.next!.next;
    
    return dummy.next;
}

/**
 * LC 61: Rotate List
 */
function rotateRight(head: ListNode | null, k: number): ListNode | null {
    if (!head || !head.next || k === 0) {
        return head;
    }
    
    // Find length
    let length = 1;
    let tail: ListNode = head;
    while (tail.next) {
        tail = tail.next;
        length++;
    }
    
    // Normalize k
    k = k % length;
    if (k === 0) {
        return head;
    }
    
    // Find (length - k)th node
    let fast: ListNode | null = head;
    for (let i = 0; i < k; i++) {
        fast = fast!.next;
    }
    
    let slow: ListNode = head;
    while (fast!.next) {
        slow = slow.next!;
        fast = fast!.next;
    }
    
    // Rotate
    const newHead = slow.next;
    slow.next = null;
    fast!.next = head;
    
    return newHead;
}

/**
 * LC 83: Remove Duplicates from Sorted List
 */
function deleteDuplicates(head: ListNode | null): ListNode | null {
    if (!head) {
        return head;
    }
    
    let current: ListNode | null = head;
    
    while (current && current.next) {
        if (current.val === current.next.val) {
            current.next = current.next.next;
        } else {
            current = current.next;
        }
    }
    
    return head;
}

/**
 * LC 82: Remove Duplicates from Sorted List II
 */
function deleteDuplicatesII(head: ListNode | null): ListNode | null {
    const dummy = new ListNode(0);
    dummy.next = head;
    
    let prev: ListNode = dummy;
    let current: ListNode | null = head;
    
    while (current) {
        if (current.next && current.val === current.next.val) {
            while (current.next && current.val === current.next.val) {
                current = current.next;
            }
            prev.next = current.next;
        } else {
            prev = prev.next!;
        }
        current = current.next;
    }
    
    return dummy.next;
}

/**
 * LC 203: Remove Linked List Elements
 */
function removeElements(head: ListNode | null, val: number): ListNode | null {
    const dummy = new ListNode(0);
    dummy.next = head;
    
    let current: ListNode = dummy;
    
    while (current.next) {
        if (current.next.val === val) {
            current.next = current.next.next;
        } else {
            current = current.next;
        }
    }
    
    return dummy.next;
}

// Test
if (require.main === module) {
    // Test removeNthFromEnd
    console.log("Testing removeNthFromEnd:");
    let head = new ListNode(1, new ListNode(2, 
        new ListNode(3, new ListNode(4, new ListNode(5)))));
    removeNthFromEnd(head, 2);
    
    // Test rotateRight
    console.log("\nTesting rotateRight:");
    head = new ListNode(1, new ListNode(2, 
        new ListNode(3, new ListNode(4, new ListNode(5)))));
    rotateRight(head, 2);
}

export { ListNode, removeNthFromEnd, rotateRight, deleteDuplicates, deleteDuplicatesII, removeElements };
