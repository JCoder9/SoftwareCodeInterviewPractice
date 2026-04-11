"""
Backtracking - Generate All Subsets

Problem: Generate all possible subsets, permutations, or combinations.
         Example: subsets([1,2,3]) → [[], [1], [2], [3], [1,2], [1,3], [2,3], [1,2,3]]

Pattern: Choose → Explore → Un-choose (backtrack)

Related LeetCode Problems:
- LC 78: Subsets (Medium) ⭐⭐⭐
- LC 46: Permutations (Medium) ⭐⭐⭐
- LC 77: Combinations (Medium)

Time Complexity: O(n × 2^n) - 2^n subsets, each takes O(n) to copy
Space Complexity: O(n) - recursion depth
"""

# ─────────────────────────────────────────────────────────────────────────────
# NAIVE APPROACH (Brute Force) — O(2^n × n²) time | O(2^n × n) space
# ─────────────────────────────────────────────────────────────────────────────
# INTERVIEW SCRIPT:
#   1. Describe:   "Brute force uses bit manipulation to generate 2^n subsets, checks
#                  each bit to include/exclude elements — O(2^n × n)"
#   2. Problem:    "For n=20: 1M subsets; creates all subsets upfront in memory;
#                  no early termination for constraints"
#   3. Transition: "Backtracking explores incrementally with pruning — same O(2^n)
#                  but more flexible for constraints and memory-efficient"
#
# def subsets_naive(nums):
#     result = []
#     n = len(nums)
#     # Generate all 2^n bit patterns
#     for mask in range(1 << n):  # 0 to 2^n - 1
#         subset = []
#         for i in range(n):
#             if mask & (1 << i):  # Check if i-th bit is set
#                 subset.append(nums[i])
#         result.append(subset)
#     return result
# ─────────────────────────────────────────────────────────────────────────────

def subsets(nums):
    result = []
    current = []
    
    def backtrack(index):
        # Base case: we've considered all numbers
        if index == len(nums):
            result.append(current[:])  # Make a copy!
            return
        
        # Choice 1: Include nums[index]
        current.append(nums[index])
        backtrack(index + 1)
        
        # Backtrack: Undo the choice
        current.pop()
        
        # Choice 2: Exclude nums[index]
        backtrack(index + 1)
    
    backtrack(0)
    return result

# Usage
if __name__ == "__main__":
    print(subsets([1, 2, 3]))
    # Output: [[], [1], [1, 2], [1, 2, 3], [1, 3], [2], [2, 3], [3]]
