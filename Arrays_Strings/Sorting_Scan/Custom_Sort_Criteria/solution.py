"""
Sort Multiple Arrays Together Pattern

Strategy: Combine parallel data, sort by one criterion, extract the other.

Time Complexity: O(n log n)
Space Complexity: O(n)
"""

from typing import List


def sort_people(names, heights):
    """Return names sorted by height descending."""
    # Combine into pairs
    people = list(zip(names, heights))
    
    # Sort by height descending
    people.sort(key=lambda x: x[1], reverse=True)
    
    # Extract sorted names
    return [name for name, height in people]


# Example
if __name__ == "__main__":
    print(sort_people(["Mary", "John", "Emma"], [180, 165, 170]))
    # Output: ["Mary", "Emma", "John"]
