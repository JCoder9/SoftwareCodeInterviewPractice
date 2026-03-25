"""
Sort + Greedy Selection Pattern

Strategy: Sort to enable greedy choices.

Time Complexity: O(n log n)
Space Complexity: O(1) to O(n)
"""

from typing import List


def maximum_units(box_types, truck_size):
    """Maximize units loaded. box_types = [[numberOfBoxes, unitsPerBox], ...]"""
    # Sort by units per box (descending)
    box_types.sort(key=lambda x: x[1], reverse=True)
    
    total_units = 0
    remaining_capacity = truck_size
    
    for num_boxes, units_per_box in box_types:
        # Take as many boxes of this type as possible
        boxes_to_take = min(num_boxes, remaining_capacity)
        
        total_units += boxes_to_take * units_per_box
        remaining_capacity -= boxes_to_take
        
        if remaining_capacity == 0:
            break
    
    return total_units


# Example
if __name__ == "__main__":
    print(maximum_units([[1,3], [2,2], [3,1]], 4))  # Output: 8
    # Take 1 box with 3 units, 2 boxes with 2 units each, 1 box with 1 unit
