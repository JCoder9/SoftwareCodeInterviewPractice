/**
 * Sort + Sliding Window Pattern
 * 
 * Time Complexity: O(n log n) + O(n)
 * Space Complexity: O(1)
 */

function numSubseq(nums: number[], target: number): number {
    nums.sort((a, b) => a - b);
    const MOD = 1000000007;
    let left = 0, right = nums.length - 1;
    let count = 0;
    
    const pow2: number[] = new Array(nums.length);
    pow2[0] = 1;
    for (let i = 1; i < nums.length; i++) {
        pow2[i] = (pow2[i - 1] * 2) % MOD;
    }
    
    while (left <= right) {
        if (nums[left] + nums[right] <= target) {
            count = (count + pow2[right - left]) % MOD;
            left++;
        } else {
            right--;
        }
    }
    
    return count;
}

function longestOnes(nums: number[], k: number): number {
    let left = 0, zeros = 0, maxLen = 0;
    
    for (let right = 0; right < nums.length; right++) {
        if (nums[right] === 0) zeros++;
        
        while (zeros > k) {
            if (nums[left] === 0) zeros--;
            left++;
        }
        
        maxLen = Math.max(maxLen, right - left + 1);
    }
    
    return maxLen;
}

function maxFrequency(nums: number[], k: number): number {
    nums.sort((a, b) => a - b);
    
    let left = 0;
    let total = 0;
    let maxFreq = 0;
    
    for (let right = 0; right < nums.length; right++) {
        total += nums[right];
        
        while (nums[right] * (right - left + 1) - total > k) {
            total -= nums[left];
            left++;
        }
        
        maxFreq = Math.max(maxFreq, right - left + 1);
    }
    
    return maxFreq;
}

// Test
if (require.main === module) {
    console.log("Num Subsequences:", numSubseq([3,5,6,7], 9));
    console.log("Longest Ones:", longestOnes([1,1,1,0,0,0,1,1,1,1,0], 2));
    console.log("Max Frequency:", maxFrequency([1,2,4], 5));
}

export { numSubseq, longestOnes, maxFrequency };
