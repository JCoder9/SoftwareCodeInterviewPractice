"""Binary Search on Answer - Binary Search on Real Numbers
Find square root with precision. If X² < target, answer is larger.
Time: O(log(x / precision)), Space: O(1)"""

def sqrt(x, precision=1e-6):
    if x < 0:
        return -1
    if x == 0:
        return 0
    
    # Search space: [0, x] for x >= 1, or [0, 1] for x < 1
    left = 0.0
    right = max(1.0, x)
    
    while right - left > precision:
        mid = left + (right - left) / 2
        square = mid * mid
        
        if square < x:
            left = mid
        elif square > x:
            right = mid
        else:
            return mid
    
    return left

if __name__ == "__main__":
    print(f"{sqrt(2):.6f}")   # 1.414214
    print(f"{sqrt(10):.6f}")  # 3.162278
