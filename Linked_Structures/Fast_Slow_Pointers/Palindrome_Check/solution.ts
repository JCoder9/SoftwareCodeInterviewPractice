/**
 * Fast/Slow Pointers - Palindrome Check Pattern
 * 
 * Related LeetCode Problems:
 * - LC 234: Palindrome Linked List (Easy)
 * - LC 2130: Maximum Twin Sum of a Linked List (Medium)
 * - LC 1721: Swapping Nodes in a Linked List (Medium)
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
 * LC 234: Palindrome Linked List
 */
function isPalindrome(head: ListNode | null): boolean {
    if (!head || !head.next) {
        return true;
    }
    
    // Find middle
    let slow: ListNode | null = head;
    let fast: ListNode | null = head;
    
    while (fast && fast.next) {
        slow = slow!.next;
        fast = fast.next.next;
    }
    
    // Reverse second half
    let prev: ListNode | null = null;
    let current: ListNode | null = slow;
    
    while (current) {
        const nextNode = current.next;
        current.next = prev;
        prev = current;
        current = nextNode;
    }
    
    // Compare
    let left: ListNode | null = head;
    let right: ListNode | null = prev;
    
    while (right) {
        if (left!.val !== right.val) {
            return false;
        }
        left = left!.next;
        right = right.next;
    }
    
    return true;
}

/**
 * LC 2130: Maximum Twin Sum
*/
function pairSum(head: ListNode | null): number {
    // Find middle
    let slow: ListNode | null = head;
    let fast: ListNode | null = head;
    
    while (fast && fast.next) {
        slow = slow!.next;
        fast = fast.next.next;
    }
    
    // Reverse second half
    let prev: ListNode | null = null;
    let current: ListNode | null = slow;
    
    while (current) {
        const nextNode = current.next;
        current.next = prev;
        prev = current;
        current = nextNode;
    }
    
    // Calculate max sum
    let maxSum = 0;
    let first: ListNode | null = head;
    let second: ListNode | null = prev;
    
    while (second) {
        maxSum = Math.max(maxSum, first!.val + second.val);
        first = first!.next;
        second = second.next;
    }
    
    return maxSum;
}

/**
 * LC 1721: Swapping Nodes in a Linked List
 */
function swapNodes(head: ListNode | null, k: number): ListNode | null {
    // Find kth node from beginning
    let first: ListNode | null = head;
    for (let i = 0; i < k - 1; i++) {
        first = first!.next;
    }
    
    // Find kth from end
    let slow: ListNode | null = head;
    let fast: ListNode | null = first;
    
    while (fast!.next) {
        slow = slow!.next;
        fast = fast!.next;
    }
    
    // Swap values
    const temp = first!.val;
    first!.val = slow!.val;
    slow!.val = temp;
    
    return head;
}

// Test
if (require.main === module) {
    // Test isPalindrome
    console.log("Testing isPalindrome:");
    const head = new ListNode(1, new ListNode(2, 
        new ListNode(3, new ListNode(2, new ListNode(1)))));
    console.log(`[1,2,3,2,1] is palindrome: ${isPalindrome(head)}`);
    
    // Test pairSum
    console.log("\nTesting pairSum:");
    const head2 = new ListNode(5, new ListNode(4, new ListNode(2, new ListNode(1))));
    console.log(`Max twin sum: ${pairSum(head2)}`);  // 6
    
    // Test swapNodes
    console.log("\nTesting swapNodes:");
    const head3 = new ListNode(1, new ListNode(2, 
        new ListNode(3, new ListNode(4, new ListNode(5)))));
    swapNodes(head3, 2);
}

export { ListNode, isPalindrome, pairSum, swapNodes };
