/**
 * 1D Dynamic Programming - Longest Increasing Subsequence (LIS)
 * 
 * Related LeetCode Problems:
 * - LC 300: Longest Increasing Subsequence (Medium)
 * - LC 673: Number of Longest Increasing Subsequence (Medium)
 * - LC 354: Russian Doll Envelopes (Hard)
 * - LC 1671: Minimum Number of Removals to Make Mountain Array (Hard)
 * 
 * Time Complexity: O(n²) or O(n log n) with binary search
 * Space Complexity: O(n)
 */

// LC 300: LIS (DP solution O(n²))
export function lengthOfLIS(nums: number[]): number {
    if (nums.length === 0) return 0;
    
    const n = nums.length;
    const dp = Array(n).fill(1);
    
    for (let i = 1; i < n; i++) {
        for (let j = 0; j < i; j++) {
            if (nums[j] < nums[i]) {
                dp[i] = Math.max(dp[i], dp[j] + 1);
            }
        }
    }
    
    return Math.max(...dp);
}

// LC 300: LIS (Binary Search O(n log n))
export function lengthOfLISOptimized(nums: number[]): number {
    const tails: number[] = [];
    
    function binarySearch(arr: number[], target: number): number {
        let left = 0, right = arr.length;
        
        while (left < right) {
            const mid = Math.floor((left + right) / 2);
            if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        
        return left;
    }
    
    for (const num of nums) {
        const pos = binarySearch(tails, num);
        
        if (pos === tails.length) {
            tails.push(num);
        } else {
            tails[pos] = num;
        }
    }
    
    return tails.length;
}

// LC 673: Number of LIS
export function findNumberOfLIS(nums: number[]): number {
    if (nums.length === 0) return 0;
    
    const n = nums.length;
    const lengths = Array(n).fill(1);
    const counts = Array(n).fill(1);
    
    for (let i = 1; i < n; i++) {
        for (let j = 0; j < i; j++) {
            if (nums[j] < nums[i]) {
                if (lengths[j] + 1 > lengths[i]) {
                    lengths[i] = lengths[j] + 1;
                    counts[i] = counts[j];
                } else if (lengths[j] + 1 === lengths[i]) {
                    counts[i] += counts[j];
                }
            }
        }
    }
    
    const maxLength = Math.max(...lengths);
    let result = 0;
    for (let i = 0; i < n; i++) {
        if (lengths[i] === maxLength) {
            result += counts[i];
        }
    }
    
    return result;
}

// Longest Decreasing Subsequence
export function longestDecreasingSubsequence(nums: number[]): number {
    if (nums.length === 0) return 0;
    
    const n = nums.length;
    const dp = Array(n).fill(1);
    
    for (let i = 1; i < n; i++) {
        for (let j = 0; j < i; j++) {
            if (nums[j] > nums[i]) {
                dp[i] = Math.max(dp[i], dp[j] + 1);
            }
        }
    }
    
    return Math.max(...dp);
}

// Test cases
if (require.main === module) {
    const nums1 = [10, 9, 2, 5, 3, 7, 101, 18];
    console.log("LIS length:", lengthOfLIS(nums1));
    console.log("LIS length (optimized):", lengthOfLISOptimized(nums1));
    
    const nums2 = [1, 3, 5, 4, 7];
    console.log("Number of LIS:", findNumberOfLIS(nums2));
    
    const nums3 = [5, 4, 6, 3, 7, 2];
    console.log("LDS length:", longestDecreasingSubsequence(nums3));
}
