/** In-Place Reversal - Palindrome Using Reversal. LC 234. Time: O(n), Space: O(1) */

class ListNode { val: number; next: ListNode | null; constructor(val: number = 0, next: ListNode | null = null) { this.val = val; this.next = next; } }

function isPalindrome(head: ListNode | null): boolean {
    if (!head || !head.next) return true;
    
    let slow: ListNode | null = head;
    let fast: ListNode | null = head;
    
    while (fast.next && fast.next.next) {
        slow = slow!.next;
        fast = fast.next.next;
    }
    
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
    
    let firstHalf: ListNode | null = head;
    let result = true;
    while (secondHalf) {
        if (firstHalf!.val !== secondHalf.val) {
            result = false;
            break;
        }
        firstHalf = firstHalf!.next;
        secondHalf = secondHalf.next;
    }
    
    return result;
}

export { ListNode, isPalindrome };
