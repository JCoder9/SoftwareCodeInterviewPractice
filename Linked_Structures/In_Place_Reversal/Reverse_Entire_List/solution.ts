/**
 * In-Place Reversal - Reverse Entire List Pattern
 * 
 * Related LeetCode Problems:
 * - LC 206: Reverse Linked List (Easy)
 * - LC 92, 25: Use this as building block
 * 
 * Time: O(n), Space: O(1) iterative, O(n) recursive
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
 * LC 206: Reverse Linked List (Iterative)
 */
function reverseList(head: ListNode | null): ListNode | null {
    let prev: ListNode | null = null;
    let current: ListNode | null = head;
    
    while (current !== null) {
        // Save next node
        const nextTemp: ListNode | null = current.next;
        
        // Reverse pointer
        current.next = prev;
        
        // Move forward
        prev = current;
        current = nextTemp;
    }
    
    return prev;
}

/**
 * LC 206: Reverse Linked List (Recursive)
 */
function reverseListRecursive(head: ListNode |

 null): ListNode | null {
    // Base case
    if (!head || !head.next) {
        return head;
    }
    
    // Reverse rest
    const newHead = reverseListRecursive(head.next);
    
    // Fix pointers
    head.next.next = head;
    head.next = null;
    
    return newHead;
}

/**
 * LC 206: Reverse Linked List (Tail Recursion)
 */
function reverseListRecursiveCleaner(head: ListNode | null, prev: ListNode | null = null): ListNode | null {
    if (!head) {
        return prev;
    }
    
    const nextTemp = head.next;
    head.next = prev;
    
    return reverseListRecursiveCleaner(nextTemp, head);
}

// Helper to create list
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

// Helper to print list
function printList(head: ListNode | null): string {
    const values: string[] = [];
    while (head) {
        values.push(String(head.val));
        head = head.next;
    }
    return values.join(" -> ");
}

// Test
if (require.main === module) {
    // Test reverseList (iterative)
    console.log("Testing reverseList (iterative):");
    let head = createList([1, 2, 3, 4, 5]);
    console.log(`Original: ${printList(head)}`);
    let reversed = reverseList(head);
    console.log(`Reversed: ${printList(reversed)}`);
    
    // Test reverseListRecursive
    console.log("\nTesting reverseList (recursive):");
    head = createList([1, 2, 3, 4, 5]);
    console.log(`Original: ${printList(head)}`);
    reversed = reverseListRecursive(head);
    console.log(`Reversed: ${printList(reversed)}`);
    
    // Test reverseListRecursiveCleaner
    console.log("\nTesting reverseList (tail recursive):");
    head = createList([1, 2, 3, 4, 5]);
    console.log(`Original: ${printList(head)}`);
    reversed = reverseListRecursiveCleaner(head);
    console.log(`Reversed: ${printList(reversed)}`);
}

export { ListNode, reverseList, reverseListRecursive, reverseListRecursiveCleaner, createList, printList };
