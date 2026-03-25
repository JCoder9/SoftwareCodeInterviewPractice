/**
 * Binary Search on Answer - Minimize the Maximum
 * Time: O(n * log(sum - max)), Space: O(1)
 */
function splitArray(nums: number[], k: number): number {
    function canSplit(maxSum: number): boolean {
        let groups = 1;
        let currentSum = 0;
        
        for (const num of nums) {
            if (currentSum + num > maxSum) {
                groups++;
                currentSum = num;
                if (groups > k) {
                    return false;
                }
            } else {
                currentSum += num;
            }
        }
        
        return true;
    }
    
    let left = Math.max(...nums);
    let right = nums.reduce((a, b) => a + b, 0);
    
    while (left < right) {
        const mid = Math.floor(left + (right - left) / 2);
        
        if (canSplit(mid)) {
            right = mid;
        } else {
            left = mid + 1;
        }
    }
    
    return left;
}

console.log(splitArray([7, 2, 5, 10, 8], 2));  // 18
