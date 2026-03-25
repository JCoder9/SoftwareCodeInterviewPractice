/**
 * Fast/Slow Pointers - Detect Cycle Pattern
 * 
 * Related LeetCode Problems:
 * - LC 141: Linked List Cycle (Easy)
 * - LC 287: Find the Duplicate Number (Medium)
 * - LC 457: Circular Array Loop (Medium)
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

/**
 * LC 141: Linked List Cycle
 */
function hasCycle(head: ListNode | null): boolean {
    if (!head || !head.next) {
        return false;
    }
    
    let slow: ListNode | null = head;
    let fast: ListNode | null = head;
    
    while (fast && fast.next) {
        slow = slow!.next;           // Move 1 step
        fast = fast.next.next;       // Move 2 steps
        
        if (slow === fast) {         // They met
            return true;
        }
    }
    
    return false;  // Fast reached the end
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

/**
 * LC 457: Circular Array Loop
 */
function circularArrayLoop(nums: number[]): boolean {
    const n = nums.length;
    
    const nextIndex = (i: number): number => {
        return ((i + nums[i]) % n + n) % n;  // Handle negative modulo
    };
    
    for (let i = 0; i < n; i++) {
        if (nums[i] === 0) {
            continue;
        }
        
        let slow = i;
        let fast = i;
        const forward = nums[i] > 0;
        
        while (true) {
            // Move slow one step
            slow = nextIndex(slow);
            if (nums[slow] === 0 || (nums[slow] > 0) !== forward) {
                break;
            }
            
            // Move fast two steps
            fast = nextIndex(fast);
            if (nums[fast] === 0 || (nums[fast] > 0) !== forward) {
                break;
            }
            fast = nextIndex(fast);
            if (nums[fast] === 0 || (nums[fast] > 0) !== forward) {
                break;
            }
            
            // Check single element loop
            if (slow === nextIndex(slow)) {
                break;
            }
            
            if (slow === fast) {
                return true;
            }
        }
        
        // Mark visited elements
        slow = i;
        const val = nums[i];
        while (nums[slow] !== 0 && (nums[slow] > 0) === forward) {
            const nextI = nextIndex(slow);
            nums[slow] = 0;
            slow = nextI;
        }
    }
    
    return false;
}

// Test cases
if (require.main === module) {
    // Test hasCycle
    console.log("Testing hasCycle:");
    const node1 = new ListNode(1);
    const node2 = new ListNode(2);
    const node3 = new ListNode(3);
    const node4 = new ListNode(4);
    node1.next = node2;
    node2.next = node3;
    node3.next = node4;
    node4.next = node2;
    console.log(`Has cycle: ${hasCycle(node1)}`);  // true
    
    // Test findDuplicate
    console.log("\nTesting findDuplicate:");
    console.log(`Duplicate in [1,3,4,2,2]: ${findDuplicate([1,3,4,2,2])}`);  // 2
    
    // Test circularArrayLoop
    console.log("\nTesting circularArrayLoop:");
    console.log(`Loop in [2,-1,1,2,2]: ${circularArrayLoop([2,-1,1,2,2])}`);  // true
}

export { ListNode, hasCycle, findDuplicate, circularArrayLoop };
