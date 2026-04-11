/**
 * 3Sum - Sort + Two Pointers Pattern
 * 
 * Problem: Find all unique triplets that sum to zero.
 *          Example: nums = [-1,0,1,2,-1,-4] → [[-1,-1,2],[-1,0,1]]
 * 
 * Pattern: 1. Sort array
 *          2. Fix first element, use two pointers for remaining pair
 *          3. Skip duplicates to avoid duplicate triplets
 * 
 * Related LeetCode Problems:
 * - LC 15: 3Sum (Medium) ⭐⭐⭐
 * - LC 16: 3Sum Closest (Medium)
 * - LC 18: 4Sum (Medium)
 * - LC 259: 3Sum Smaller (Medium)
 * 
 * Time Complexity: O(n²) - O(n log n) sort + O(n²) two pointers
 * Space Complexity: O(1) or O(n) for result (depending on requirements)
 */

// ─────────────────────────────────────────────────────────────────────────────
// NAIVE APPROACH (Brute Force) — O(n³) time | O(n) space
// ─────────────────────────────────────────────────────────────────────────────
// INTERVIEW SCRIPT:
//   1. Describe:   "Brute force checks all triplet combinations with 3 nested loops,
//                  uses set to avoid duplicates — O(n³)"
//   2. Problem:    "For n=100: 100³ = 1M comparisons; for n=1000: 1 billion;
//                  duplicate detection is expensive"
//   3. Transition: "Sort first (O(n log n)), fix one element, use two pointers
//                  for pair (O(n²)), skip duplicates during iteration"
//
// function threeSumNaive(nums: number[]): number[][] {
//     const result: number[][] = [];
//     const seen = new Set<string>();
//     const n = nums.length;
//     
//     for (let i = 0; i < n; i++) {
//         for (let j = i + 1; j < n; j++) {
//             for (let k = j + 1; k < n; k++) {
//                 if (nums[i] + nums[j] + nums[k] === 0) {
//                     const triplet = [nums[i], nums[j], nums[k]].sort((a, b) => a - b);
//                     const key = triplet.join(',');
//                     if (!seen.has(key)) {
//                         seen.add(key);
//                         result.push(triplet);
//                     }
//                 }
//             }
//         }
//     }
//     return result;
// }
// ─────────────────────────────────────────────────────────────────────────────

/**
 * LC 15: Find all unique triplets that sum to zero.
 * 
 * Algorithm:
 * 1. Sort the array
 * 2. Fix first element (i)
 * 3. Use two pointers (left, right) to find pair that sums to -nums[i]
 * 4. Skip duplicates at each level
 */
function threeSum(nums: number[]): number[][] {
    nums.sort((a, b) => a - b);
    const result: number[][] = [];
    const n = nums.length;
    
    for (let i = 0; i < n - 2; i++) {
        // Skip duplicate first elements
        if (i > 0 && nums[i] === nums[i - 1]) {
            continue;
        }
        
        // Two pointers for the remaining pair
        let left = i + 1;
        let right = n - 1;
        const target = -nums[i];
        
        while (left < right) {
            const currentSum = nums[left] + nums[right];
            
            if (currentSum === target) {
                result.push([nums[i], nums[left], nums[right]]);
                
                // Skip duplicates for left pointer
                while (left < right && nums[left] === nums[left + 1]) {
                    left++;
                }
                // Skip duplicates for right pointer
                while (left < right && nums[right] === nums[right - 1]) {
                    right--;
                }
                
                left++;
                right--;
            } else if (currentSum < target) {
                left++;
            } else {
                right--;
            }
        }
    }
    
    return result;
}

/**
 * LC 16: Find triplet sum closest to target.
 */
function threeSumClosest(nums: number[], target: number): number {
    nums.sort((a, b) => a - b);
    const n = nums.length;
    let closestSum = Infinity;
    
    for (let i = 0; i < n - 2; i++) {
        let left = i + 1;
        let right = n - 1;
        
        while (left < right) {
            const currentSum = nums[i] + nums[left] + nums[right];
            
            // Update closest if current is closer
            if (Math.abs(currentSum - target) < Math.abs(closestSum - target)) {
                closestSum = currentSum;
            }
            
            if (currentSum < target) {
                left++;
            } else if (currentSum > target) {
                right--;
            } else {
                return currentSum;  // Exact match
            }
        }
    }
    
    return closestSum;
}

// Example usage
console.log(threeSum([-1, 0, 1, 2, -1, -4]));
// Output: [[-1, -1, 2], [-1, 0, 1]]

console.log(threeSum([0, 1, 1]));
// Output: []

console.log(threeSum([0, 0, 0]));
// Output: [[0, 0, 0]]

console.log(threeSumClosest([-1, 2, 1, -4], 1));
// Output: 2 (sum of -1 + 2 + 1 = 2)
