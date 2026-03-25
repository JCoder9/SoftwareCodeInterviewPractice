/**
 * Fast/Slow Pointers - Find Cycle Start Pattern
 * 
 * Related LeetCode Problems:
 * - LC 142: Linked List Cycle II (Medium)
 * - LC 287: Find the Duplicate Number (Medium)
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
 * LC 142: Linked List Cycle II
 */
function detectCycle(head: ListNode | null): ListNode | null {
    if (!head || !head.next) {
        return null;
    }
    
    // Step 1: Detect if cycle exists
    let slow: ListNode | null = head;
    let fast: ListNode | null = head;
    let hasCycle = false;
    
    while (fast && fast.next) {
        slow = slow!.next;
        fast = fast.next.next;
        
        if (slow === fast) {
            hasCycle = true;
            break;
        }
    }
    
    if (!hasCycle) {
        return null;
    }
    
    // Step 2: Find cycle start
    slow = head;  // Reset slow to head
    
    while (slow !== fast) {
        slow = slow!.next;
       fast = fast!.next;
    }
    
    return slow;  // Cycle start
}

/**
 * Find cycle start and return cycle length
 */
function detectCycleWithLength(head: ListNode | null): [ListNode | null, number] {
    if (!head || !head.next) {
        return [null, 0];
    }
    
    // Detect cycle
    let slow: ListNode | null = head;
    let fast: ListNode | null = head;
    let hasCycle = false;
    
    while (fast && fast.next) {
        slow = slow!.next;
        fast = fast.next.next;
        
        if (slow === fast) {
            hasCycle = true;
            break;
        }
    }
    
    if (!hasCycle) {
        return [null, 0];
    }
    
    // Calculate cycle length
    let cycleLength = 1;
    let current = slow!.next;
    while (current !== slow) {
        cycleLength++;
        current = current!.next;
    }
    
    // Find cycle start
    slow = head;
    while (slow !== fast) {
        slow = slow!.next;
        fast = fast!.next;
    }
    
    return [slow, cycleLength];
}

/**
 * LC 287: Find the Duplicate Number
 */
function findDuplicate(nums: number[]): number {
    // Phase 1: Detect cycle
    let slow = nums[0];
    let fast = nums[0];
    
    do {
        slow = nums[slow];
        fast = nums[nums[fast]];
    } while (slow !== fast);
    
    // Phase 2: Find entrance to cycle
    slow = nums[0];
    while (slow !== fast) {
        slow = nums[slow];
        fast = nums[fast];
    }
    
    return slow;
}

// Test cases
if (require.main === module) {
    // Test detectCycle
    console.log("Testing detectCycle:");
    const node1 = new ListNode(1);
    const node2 = new ListNode(2);
    const node3 = new ListNode(3);
    const node4 = new ListNode(4);
    const node5 = new ListNode(5);
    node1.next = node2;
    node2.next = node3;
    node3.next = node4;
    node4.next = node5;
    node5.next = node3;
    
    const result = detectCycle(node1);
    console.log(`Cycle starts at: ${result?.val ?? 'null'}`);  // 3
    
    // Test with length
    console.log("\nTesting detectCycleWithLength:");
    const [start, length] = detectCycleWithLength(node1);
    console.log(`Cycle starts at: ${start?.val ?? 'null'}, Length: ${length}`);
    
    // Test findDuplicate
    console.log("\nTesting findDuplicate:");
    console.log(`Duplicate: ${findDuplicate([1,3,4,2,2])}`);  // 2
    console.log(`Duplicate: ${findDuplicate([3,1,3,4,2])}`);  // 3
}

export { ListNode, detectCycle, detectCycleWithLength, findDuplicate };
