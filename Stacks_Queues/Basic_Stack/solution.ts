/**
 * Valid Parentheses / Min Stack - Basic Stack Pattern
 *
 * Problem (LC 20): Given a string containing '(', ')', '{', '}', '[' and ']',
 * determine if the input string is valid. Brackets must close in the correct order.
 *
 * Problem (LC 155): Design a stack that supports push, pop, top, and retrieving
 * the minimum element — all in O(1) time.
 *
 * Pattern: Use a stack to track the most recent unmatched opening bracket.
 *          For Min Stack, maintain a parallel stack that records the running minimum.
 *
 * Time Complexity: O(n) - single pass through the string / O(1) per operation
 * Space Complexity: O(n) - stack stores at most n characters
 */

// ─────────────────────────────────────────────────────────────────────────────
// NAIVE APPROACH (Brute Force) — O(n²) time | O(n) space
// ─────────────────────────────────────────────────────────────────────────────
// INTERVIEW SCRIPT:
//   1. Describe:   "Scan every closing bracket and search backwards for its
//                  match by repeatedly collapsing adjacent pairs — O(n²)."
//   2. Problem:    "Deeply nested strings cause quadratic work."
//   3. Transition: "With a stack we track the most recent opening bracket in
//                  O(1), reducing overall cost to O(n)."
//
// function isValidNaive(s: string): boolean {
//     const chars = s.split('');
//     const pair: Record<string, string> = { ')': '(', '}': '{', ']': '[' };
//     while (true) {
//         let changed = false;
//         for (let i = 1; i < chars.length; i++) {
//             if (chars[i] in pair && chars[i - 1] === pair[chars[i]]) {
//                 chars.splice(i - 1, 2); changed = true; break;
//             }
//         }
//         if (!changed) break;
//     }
//     return chars.length === 0;
// }
// ─────────────────────────────────────────────────────────────────────────────

// ── Valid Parentheses (LC 20) ─────────────────────────────────────────────────
// Pattern: Push opening brackets; on closing bracket pop and verify match.
// Time: O(n)  Space: O(n)
function isValid(s: string): boolean {
    const stack: string[] = [];
    const mapping: Record<string, string> = { ')': '(', '}': '{', ']': '[' };
    for (const ch of s) {
        if (ch in mapping) {
            if (!stack.length || stack[stack.length - 1] !== mapping[ch]) return false;
            stack.pop();
        } else {
            stack.push(ch);
        }
    }
    return stack.length === 0;
}

// ── Min Stack (LC 155) ────────────────────────────────────────────────────────
// Pattern: Maintain a parallel min-tracking stack alongside the main stack.
// Each push records the running minimum so getMin() is always O(1).
// Time: O(1) all ops  Space: O(n)
class MinStack {
    private stack: number[] = [];
    private minStack: number[] = [];

    push(val: number): void {
        this.stack.push(val);
        const curMin =
            this.minStack.length === 0
                ? val
                : Math.min(val, this.minStack[this.minStack.length - 1]);
        this.minStack.push(curMin);
    }

    pop(): void {
        this.stack.pop();
        this.minStack.pop();
    }

    top(): number {
        return this.stack[this.stack.length - 1];
    }

    getMin(): number {
        return this.minStack[this.minStack.length - 1];
    }
}

// ── Tests ──────────────────────────────────────────────────────────────────────
console.log(isValid("()[]{}"));   // true
console.log(isValid("(]"));       // false
console.log(isValid("{[]}"));     // true

const ms = new MinStack();
ms.push(-2); ms.push(0); ms.push(-3);
console.log(ms.getMin());  // -3
ms.pop();
console.log(ms.top());     // 0
console.log(ms.getMin());  // -2
