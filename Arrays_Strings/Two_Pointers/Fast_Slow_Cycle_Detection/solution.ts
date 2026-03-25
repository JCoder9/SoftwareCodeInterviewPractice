/**
 * Linked List Cycle Detection - Fast/Slow Pointer Pattern (Floyd's Algorithm)
 * 
 * Problem: Detect if a linked list has a cycle.
 * 
 * Pattern: Slow pointer moves 1 step, fast pointer moves 2 steps.
 *          If there's a cycle, they will eventually meet.
 * 
 * Time Complexity: O(n) - worst case visit each node once
 * Space Complexity: O(1) - only two pointers
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
 * Detect if a linked list has a cycle using Floyd's algorithm.
 * 
 * @param head - Head of the linked list
 * @returns true if cycle exists, false otherwise
 */
function hasCycle(head: ListNode | null): boolean {
    if (!head || !head.next) {
        return false;
    }
    
    let slow: ListNode | null = head;
    let fast: ListNode | null = head;
    
    while (fast && fast.next) {
        slow = slow!.next;        // Move 1 step
        fast = fast.next.next;    // Move 2 steps
        
        if (slow === fast) {      // They met - cycle exists
            return true;
        }
    }
    
    return false;  // fast reached end - no cycle
}

/**
 * Helper function to create a linked list with an optional cycle.
 */
function createListWithCycle(values: number[], cyclePos: number): ListNode | null {
    if (values.length === 0) return null;
    
    const nodes = values.map(val => new ListNode(val));
    
    // Link nodes
    for (let i = 0; i < nodes.length - 1; i++) {
        nodes[i].next = nodes[i + 1];
    }
    
    // Create cycle if cyclePos is valid
    if (cyclePos >= 0 && cyclePos < nodes.length) {
        nodes[nodes.length - 1].next = nodes[cyclePos];
    }
    
    return nodes[0];
}

// Test cases
if (require.main === module) {
    const testCases: [number[], number, boolean][] = [
        [[3, 2, 0, -4], 1, true],   // Cycle at position 1
        [[1, 2], 0, true],           // Cycle at position 0
        [[1], -1, false],            // No cycle
        [[1, 2, 3], -1, false],      // No cycle
    ];
    
    for (const [values, cyclePos, expected] of testCases) {
        const head = createListWithCycle(values, cyclePos);
        const result = hasCycle(head);
        const status = result === expected ? "✓" : "✗";
        const cycleDesc = cyclePos >= 0 ? `cycle at pos ${cyclePos}` : "no cycle";
        console.log(`${status} List [${values}] (${cycleDesc}): hasCycle = ${result}`);
    }
}

export { ListNode, hasCycle };
