"""
Backtracking - Generate All Subsets

Pattern: Finding all solutions, permutations, combinations, subsets, solving puzzles

Time Complexity: O(n × 2^n) - 2^n subsets, each takes O(n) to copy
Space Complexity: O(n) - recursion depth is n
"""

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
