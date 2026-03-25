"""
Valid Parentheses / Min Stack - Basic Stack Pattern

Problem (LC 20): Given a string containing '(', ')', '{', '}', '[' and ']',
determine if the input string is valid. Brackets must close in the correct order.

Problem (LC 155): Design a stack that supports push, pop, top, and retrieving
the minimum element — all in O(1) time.

Pattern: Use a stack to track the most recent unmatched opening bracket.
         For Min Stack, maintain a parallel stack that records the running minimum.

Time Complexity: O(n) - single pass through the string / O(1) per operation
Space Complexity: O(n) - stack stores at most n characters
"""

# ─────────────────────────────────────────────────────────────────────────────
# NAIVE APPROACH (Brute Force) — O(n²) time | O(n) space
# ─────────────────────────────────────────────────────────────────────────────
# INTERVIEW SCRIPT:
#   1. Describe:   "Scan every closing bracket and search backwards for its
#                  match by repeatedly collapsing adjacent pairs — O(n²)."
#   2. Problem:    "Deeply nested strings cause quadratic work."
#   3. Transition: "With a stack we track the most recent opening bracket in
#                  O(1), reducing overall cost to O(n)."
#
# def is_valid_naive(s: str) -> bool:
#     chars = list(s)
#     pair = {')': '(', '}': '{', ']': '['}
#     while True:
#         changed = False
#         for i in range(1, len(chars)):
#             if chars[i] in pair and chars[i - 1] == pair[chars[i]]:
#                 chars.pop(i); chars.pop(i - 1)
#                 changed = True; break
#         if not changed:
#             break
#     return len(chars) == 0
# ─────────────────────────────────────────────────────────────────────────────

# ── Valid Parentheses (LC 20) ─────────────────────────────────────────────────
# Pattern: Push opening brackets; on closing bracket pop and verify match.
# Time: O(n)  Space: O(n)
def is_valid(s: str) -> bool:
    stack: list[str] = []
    mapping = {')': '(', '}': '{', ']': '['}
    for ch in s:
        if ch in mapping:                          # closing bracket
            if not stack or stack[-1] != mapping[ch]:
                return False
            stack.pop()
        else:
            stack.append(ch)                       # opening bracket
    return not stack


# ── Min Stack (LC 155) ────────────────────────────────────────────────────────
# Pattern: Maintain a parallel min-tracking stack alongside the main stack.
# Each push records the running minimum so getMin() is always O(1).
# Time: O(1) all ops  Space: O(n)
class MinStack:
    def __init__(self) -> None:
        self.stack: list[int] = []
        self.min_stack: list[int] = []             # parallel min tracker

    def push(self, val: int) -> None:
        self.stack.append(val)
        cur_min = min(val, self.min_stack[-1]) if self.min_stack else val
        self.min_stack.append(cur_min)

    def pop(self) -> None:
        self.stack.pop()
        self.min_stack.pop()

    def top(self) -> int:
        return self.stack[-1]

    def getMin(self) -> int:
        return self.min_stack[-1]


if __name__ == "__main__":
    # Valid Parentheses
    print(is_valid("()[]{}"))   # True
    print(is_valid("(]"))       # False
    print(is_valid("{[]}"))     # True

    # Min Stack
    ms = MinStack()
    ms.push(-2); ms.push(0); ms.push(-3)
    print(ms.getMin())  # -3
    ms.pop()
    print(ms.top())     # 0
    print(ms.getMin())  # -2
