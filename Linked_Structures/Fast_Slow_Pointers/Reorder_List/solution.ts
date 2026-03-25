/**
 * Fast/Slow Pointers - Reorder List Pattern
 * 
 * Related LeetCode Problems:
 * - LC 143: Reorder List (Medium)
 * - LC 2074: Reverse Nodes in Even Length Groups (Medium)
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
 * LC 143: Reorder List
 */
function reorderList(head: ListNode | null): void {
    if (!head || !head.next) {
        return;
    }
    
    // Find middle
    let slow: ListNode | null = head;
    let fast: ListNode | null = head;
    
    while (fast.next && fast.next.next) {
        slow = slow!.next;
        fast = fast.next.next;
    }
    
    // Reverse second half
    let secondHalf: ListNode | null = slow!.next;
    slow!.next = null;
    
    let prev: ListNode | null = null;
    let current: ListNode | null = secondHalf;
    
    while (current) {
        const nextTemp = current.next;
        current.next = prev;
        prev = current;
        current = nextTemp;
    }
    secondHalf = prev;
    
    // Merge
    let firstHalf: ListNode | null = head;
    while (secondHalf) {
        const temp1 = firstHalf!.next;
        const temp2 = secondHalf.next;
        
        firstHalf!.next = secondHalf;
        secondHalf.next = temp1;
        
        firstHalf = temp1;
        secondHalf = temp2;
    }
}

/**
 * LC 2074: Reverse Nodes in Even Length Groups
 */
function reverseEvenLengthGroups(head: ListNode | null): ListNode | null {
    if (!head || !head.next) {
        return head;
    }
    
    const dummy = new ListNode(0);
    dummy.next = head;
    let prevGroupEnd: ListNode = dummy;
    let groupLen = 1;
    
    while (prevGroupEnd.next) {
        let groupStart: ListNode = prevGroupEnd.next;
        let groupEnd: ListNode = groupStart;
        let actualLen = 1;
        
        for (let i = 0; i < groupLen - 1; i++) {
            if (!groupEnd.next) break;
            groupEnd = groupEnd.next;
            actualLen++;
        }
        
        const nextGroupStart = groupEnd.next;
        
        if (actualLen % 2 === 0) {
            let prev: ListNode | null = nextGroupStart;
            let current: ListNode | null = groupStart;
            
            for (let i = 0; i < actualLen; i++) {
                const nextTemp = current!.next;
                current!.next = prev;
                prev = current;
                current = nextTemp;
            }
            
            prevGroupEnd.next = prev;
            prevGroupEnd = groupStart;
        } else {
            prevGroupEnd = groupEnd;
        }
        
        groupLen++;
    }
    
    return dummy.next;
}

// Test
if (require.main === module) {
    // Test reorderList
    console.log("Testing reorderList:");
    const head = new ListNode(1, new ListNode(2, 
        new ListNode(3, new ListNode(4, new ListNode(5)))));
    reorderList(head);
    
    // Test reverseEvenLengthGroups
    console.log("\nTesting reverseEvenLengthGroups:");
    const head2 = new ListNode(1, new ListNode(2, new ListNode(3, 
        new ListNode(4, new ListNode(5, new ListNode(6, new ListNode(7)))))));
    reverseEvenLengthGroups(head2);
}

export { ListNode, reorderList, reverseEvenLengthGroups };
