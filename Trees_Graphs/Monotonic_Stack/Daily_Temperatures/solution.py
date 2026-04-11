"""
Monotonic Stack - Daily Temperatures (LC 739)

Problem: Find days until next warmer temperature for each day.
         Example: temps = [73,74,75,71,69,72,76,73] → [1,1,4,2,1,1,0,0]

Pattern: Monotonic decreasing stack tracks indices of previous cooler days

Related LeetCode Problems:
- LC 739: Daily Temperatures (Medium) ⭐⭐⭐
- LC 901: Online Stock Span (Medium)
- LC 1019: Next Greater Node In Linked List (Medium)

Time Complexity: O(n) - each element pushed/popped once
Space Complexity: O(n) - stack storage
"""

# ─────────────────────────────────────────────────────────────────────────────
# NAIVE APPROACH (Brute Force) — O(n²) time | O(1) space
# ─────────────────────────────────────────────────────────────────────────────
# INTERVIEW SCRIPT:
#   1. Describe:   "Brute force scans ahead from each day to find next warmer day —
#                  O(n²) worst case"
#   2. Problem:    "For n=1000 with decreasing temps: 1000 + 999 + 998... = 500K
#                  comparisons"
#   3. Transition: "Use monotonic stack to track pending days in one pass — O(n)"
#
# def daily_temperatures_naive(temperatures):
#     n = len(temperatures)
#     result = [0] * n
#     for i in range(n):
#         for j in range(i + 1, n):
#             if temperatures[j] > temperatures[i]:
#                 result[i] = j - i
#                 break
#     return result
# ─────────────────────────────────────────────────────────────────────────────

def daily_temperatures(temperatures):
    n = len(temperatures)
    result = [0] * n  # Default: 0 days (no warmer day)
    stack = []  # Monotonic decreasing stack
    
    for i in range(n):
        # Found a warmer day for previous cooler days
        while stack and temperatures[i] > temperatures[stack[-1]]:
            prev_day = stack.pop()
            result[prev_day] = i - prev_day
        
        stack.append(i)
    
    return result

if __name__ == "__main__":
    temps = [73, 74, 75, 71, 69, 72, 76, 73]
    print(daily_temperatures(temps))  # [1, 1, 4, 2, 1, 1, 0, 0]
