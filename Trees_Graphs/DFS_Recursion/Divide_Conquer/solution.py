"""
Divide and Conquer - Merge Sort

Pattern: Problems that can be split into independent subproblems

Time Complexity: O(n log n) - log n levels of recursion, n work per level
Space Complexity: O(n) - for temporary arrays during merging
"""

def mergeSort(arr):
    # Base case: arrays of 0 or 1 element are already sorted
    if len(arr) <= 1:
        return arr
    
    # Divide: split array in half
    mid = len(arr) // 2
    left = mergeSort(arr[:mid])
    right = mergeSort(arr[mid:])
    
    # Conquer & Combine: merge sorted halves
    return merge(left, right)

def merge(left, right):
    result = []
    i = j = 0
    
    # Merge while both have elements
    while i < len(left) and j < len(right):
        if left[i] <= right[j]:
            result.append(left[i])
            i += 1
        else:
            result.append(right[j])
            j += 1
    
    # Add remaining elements
    result.extend(left[i:])
    result.extend(right[j:])
    
    return result

# Usage
if __name__ == "__main__":
    arr = [38, 27, 43, 3, 9, 82, 10]
    print(mergeSort(arr))  # Output: [3, 9, 10, 27, 38, 43, 82]
