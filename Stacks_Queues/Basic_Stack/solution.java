import java.util.*;

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
public class solution {

    // ─────────────────────────────────────────────────────────────────────────
    // NAIVE APPROACH (Brute Force) — O(n²) time | O(n) space
    // ─────────────────────────────────────────────────────────────────────────
    // INTERVIEW SCRIPT:
    //   1. Describe:   "Scan every closing bracket and search backwards for its
    //                  match by repeatedly collapsing adjacent pairs — O(n²)."
    //   2. Problem:    "Deeply nested strings cause quadratic work."
    //   3. Transition: "With a stack we track the most recent opening bracket in
    //                  O(1), reducing overall cost to O(n)."
    //
    // static boolean isValidNaive(String s) {
    //     List<Character> chars = new ArrayList<>();
    //     for (char c : s.toCharArray()) chars.add(c);
    //     Map<Character, Character> pair = Map.of(')', '(', '}', '{', ']', '[');
    //     boolean changed = true;
    //     while (changed) {
    //         changed = false;
    //         for (int i = 1; i < chars.size(); i++) {
    //             if (pair.containsKey(chars.get(i)) &&
    //                 chars.get(i - 1).equals(pair.get(chars.get(i)))) {
    //                 chars.remove(i); chars.remove(i - 1);
    //                 changed = true; break;
    //             }
    //         }
    //     }
    //     return chars.isEmpty();
    // }
    // ─────────────────────────────────────────────────────────────────────────

    // ── Valid Parentheses (LC 20) ─────────────────────────────────────────────
    // Pattern: Push opening brackets; on closing bracket pop and verify match.
    // Time: O(n)  Space: O(n)
    static boolean isValid(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        for (char ch : s.toCharArray()) {
            if (ch == '(' || ch == '{' || ch == '[') {
                stack.push(ch);
            } else {
                if (stack.isEmpty()) return false;
                char top = stack.pop();
                if ((ch == ')' && top != '(') ||
                    (ch == '}' && top != '{') ||
                    (ch == ']' && top != '[')) return false;
            }
        }
        return stack.isEmpty();
    }

    // ── Min Stack (LC 155) ────────────────────────────────────────────────────
    // Pattern: Maintain a parallel min-tracking stack alongside the main stack.
    // Each push records the running minimum so getMin() is always O(1).
    // Time: O(1) all ops  Space: O(n)
    static class MinStack {
        private Deque<Integer> stack    = new ArrayDeque<>();
        private Deque<Integer> minStack = new ArrayDeque<>();

        public void push(int val) {
            stack.push(val);
            int curMin = minStack.isEmpty() ? val : Math.min(val, minStack.peek());
            minStack.push(curMin);
        }

        public void pop()      { stack.pop(); minStack.pop(); }
        public int  top()      { return stack.peek(); }
        public int  getMin()   { return minStack.peek(); }
    }

    public static void main(String[] args) {
        System.out.println(isValid("()[]{}"));   // true
        System.out.println(isValid("(]"));       // false
        System.out.println(isValid("{[]}"));     // true

        MinStack ms = new MinStack();
        ms.push(-2); ms.push(0); ms.push(-3);
        System.out.println(ms.getMin());  // -3
        ms.pop();
        System.out.println(ms.top());     // 0
        System.out.println(ms.getMin());  // -2
    }
}
