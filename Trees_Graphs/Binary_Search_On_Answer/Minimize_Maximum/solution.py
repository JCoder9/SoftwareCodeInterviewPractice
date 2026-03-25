"""Binary Search on Answer - Minimize the Maximum (LC 410)
Split Array Largest Sum: Split array into k subarrays to minimize max sum.
If we can split with max_sum=X, we can with X+1. Search for minimum X.
Time: O(n * log(sum - max)), Space: O(1)"""

def splitArray(nums, k):
    def can_split(max_sum):
        # Can we split into k subarrays with each sum <= max_sum?
        groups = 1
        current_sum = 0
        
        for num in nums:
            if current_sum + num > max_sum:
                groups += 1
                current_sum = num
                if groups > k:
                    return False
            else:
                current_sum += num
        
        return True
    
    # Search space: [largest element, sum of all elements]
    left = max(nums)  # At minimum, one subarray must have the largest element
    right = sum(nums)  # At maximum, all elements in one subarray
    
    while left < right:
        mid = left + (right - left) // 2
        
        if can_split(mid):
            # If we can split with max_sum = mid, try smaller
            right = mid
        else:
            # If we can't, need larger max_sum
            left = mid + 1
    
    return left

if __name__ == "__main__":
    print(splitArray([7, 2, 5, 10, 8], 2))  # 18
