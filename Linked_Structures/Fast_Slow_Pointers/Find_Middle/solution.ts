/**
 * Fast/Slow Pointers - Find Middle Pattern
 * 
 * Related LeetCode Problems:
 * - LC 876: Middle of the Linked List (Easy)
 * - LC 2095: Delete the Middle Node of a Linked List (Medium)
 * - LC 143: Reorder List (Medium)
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
 * LC 876: Middle of the Linked List
 */
function middleNode(head: ListNode | null): ListNode | null {
    let slow: ListNode | null = head;
    let fast: ListNode | null = head;
    
    while (fast && fast.next) {
        slow = slow!.next;
        fast = fast.next.next;
    }
    
    return slow;
}

/**
 * Return first middle for even-length lists
 */
function middleNodeFirst(head: ListNode | null): ListNode | null {
    if (!head || !head.next) {
        return head;
    }
    
    let slow: ListNode | null = head;
    let fast: ListNode | null = head.next!.next;
    
    while (fast && fast.next) {
        slow = slow!.next;
        fast = fast.next.next;
    }
    
    return slow;
}

/**
 * LC 2095: Delete the Middle Node
 */
function deleteMiddle(head: ListNode | null): ListNode | null {
    if (!head || !head.next) {
        return null;
    }
    
    let slow: ListNode | null = head;
    let fast: ListNode | null = head;
    let prev: ListNode | null = null;
    
    while (fast && fast.next) {
        prev = slow;
        slow = slow!.next;
        fast = fast.next.next;
    }
    
    // Delete middle node
    if (prev) {
        prev.next = slow!.next;
    }
    
    return head;
}

/**
 * Split linked list into two halves
 */
function splitList(head: ListNode | null): [ListNode | null, ListNode | null] {
    if (!head || !head.next) {
        return [head, null];
    }
    
    let slow: ListNode | null = head;
    let fast: ListNode | null = head;
    let prev: ListNode | null = null;
    
    while (fast && fast.next) {
        prev = slow;
        slow = slow!.next;
        fast = fast.next.next;
    }
    
    // Split the list
    if (prev) {
        prev.next = null;
    }
    
    return [head, slow];
}

// Helper to print list
function printList(head: ListNode | null): string {
    const values: string[] = [];
    let current = head;
    while (current) {
        values.push(String(current.val));
        current = current.next;
    }
    return values.join(" -> ");
}

// Test cases
if (require.main === module) {
    // Test middleNode (odd length)
    console.log("Testing middleNode (odd length):");
    const head = new ListNode(1, new ListNode(2, new ListNode(3, 
        new ListNode(4, new ListNode(5)))));
    
    const result = middleNode(head);
    console.log(`Middle node value: ${result?.val}`);  // 3
    console.log(`List from middle: ${printList(result)}`);
    
    // Test deleteMiddle
    console.log("\nTesting deleteMiddle:");
    const head2 = new ListNode(1, new ListNode(2, new ListNode(3, 
        new ListNode(4, new ListNode(5)))));
    console.log(`Before: ${printList(head2)}`);
    const result2 = deleteMiddle(head2);
    console.log(`After:  ${printList(result2)}`);
    
    // Test splitList
    console.log("\nTesting splitList:");
    const head3 = new ListNode(1, new ListNode(2, new ListNode(3, 
        new ListNode(4, new ListNode(5, new ListNode(6))))));
    const [first, second] = splitList(head3);
    console.log(`First half:  ${printList(first)}`);
    console.log(`Second half: ${printList(second)}`);
}

export { ListNode, middleNode, middleNodeFirst, deleteMiddle, splitList };
