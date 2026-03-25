"""
Difference Array - Range Update Pattern

Problem: Add a value to a range [L, R] multiple times efficiently.

Key Insight: Mark START and END of updates instead of updating every element!

Time Complexity: O(1) per update, O(n) to get final result
Space Complexity: O(n)
"""

from typing import List


class DifferenceArray:
    """
    Efficiently apply range updates then compute final array.
    
    Pattern: Instead of updating each element in range [L, R],
    mark +val at L and -val at R+1, then use prefix sum.
    """
    
    def __init__(self, size):
        """Initialize difference array with zeros"""
        self.diff = [0] * (size + 1)  # Extra space for boundary
    
    def range_add(self, left, right, val):
        """Add val to range [left, right]"""
        self.diff[left] += val
        self.diff[right + 1] -= val  # Cancel out after the range
    
    def get_result(self):
        """Convert difference array back to actual values"""
        result = []
        current = 0
        for i in range(len(self.diff) - 1):  # Skip last boundary element
            current += self.diff[i]
            result.append(current)
        return result


# Example: Start with [0,0,0,0,0]
if __name__ == "__main__":
    da = DifferenceArray(5)
    da.range_add(1, 3, 2)  # Add 2 to indices 1-3
    da.range_add(0, 2, 3)  # Add 3 to indices 0-2
    print(da.get_result())  # [3, 5, 5, 2, 0]
    
    return True


# Test cases
if __name__ == "__main__":
    # Example: Start with [0,0,0,0,0]
    da = DifferenceArray(5)
    da.range_add(1, 3, 2)  # Add 2 to indices 1-3
    da.range_add(0, 2, 3)  # Add 3 to indices 0-2
    print("Difference Array result:", da.get_result())  # [3, 5, 5, 2, 0]
    
    # Corporate Flight Bookings
    bookings = [[1, 2, 10], [2, 3, 20], [2, 5, 25]]
    print("Flight bookings:", corporate_flight_bookings(bookings, 5))
    # [10, 55, 45, 25, 25]
    
    # Range Addition
    updates = [[1, 3, 2], [2, 4, 3], [0, 2, -2]]
    print("Range Addition:", range_addition(5, updates))
    # [-2, 0, 3, 5, 3]
    
    # Car Pooling
    trips = [[2, 1, 5], [3, 3, 7]]
    print("Car Pooling (capacity 4):", car_pooling(trips, 4))  # False
    print("Car Pooling (capacity 5):", car_pooling(trips, 5))  # True
