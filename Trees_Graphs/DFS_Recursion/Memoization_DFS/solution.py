"""
Memoization DFS (Top-Down DP) - Fibonacci with Memoization

Pattern: Problems with overlapping subproblems (solving the same thing multiple times)

Time Complexity: O(n) - each number calculated once
Space Complexity: O(n) - for memoization cache and call stack
"""

def fib(n, memo=None):
    if memo is None:
        memo = {}
    
    # Base cases
    if n <= 1:
        return n
    
    # Check cache
    if n in memo:
        return memo[n]
    
    # Calculate and cache
    memo[n] = fib(n - 1, memo) + fib(n - 2, memo)
    return memo[n]

# Alternative: using decorator
from functools import lru_cache

@lru_cache(maxsize=None)
def fib_cached(n):
    if n <= 1:
        return n
    return fib_cached(n - 1) + fib_cached(n - 2)

# Usage
if __name__ == "__main__":
    print(fib(10))         # Output: 55
    print(fib_cached(10))  # Output: 55
