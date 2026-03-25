/**
 * Longest Subarray Sum K - Positive Numbers Only
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */

function maxSubArrayLenPositive(nums: number[], k: number): number {
    let left = 0;
    let currentSum = 0;
    let maxLength = 0;
    
    for (let right = 0; right < nums.length; right++) {
        currentSum += nums[right];
        
        while (currentSum > k && left <= right) {
            currentSum -= nums[left];
            left++;
        }
        
        if (currentSum === k) {
            maxLength = Math.max(maxLength, right - left + 1);
        }
    }
    
    return maxLength;
}

function minSubArrayLen(target: number, nums: number[]): number {
    let left = 0;
    let currentSum = 0;
    let minLength = Infinity;
    
    for (let right = 0; right < nums.length; right++) {
        currentSum += nums[right];
        
        while (currentSum >= target) {
            minLength = Math.min(minLength, right - left + 1);
            currentSum -= nums[left];
            left++;
        }
    }
    
    return minLength === Infinity ? 0 : minLength;
}

function numSubarraysWithSum(nums: number[], goal: number): number {
    function atMost(target: number): number {
        if (target < 0) return 0;
        
        let left = 0;
        let currentSum = 0;
        let count = 0;
        
        for (let right = 0; right < nums.length; right++) {
            currentSum += nums[right];
            
            while (currentSum > target) {
                currentSum -= nums[left];
                left++;
            }
            
            count += right - left + 1;
        }
        
        return count;
    }
    
    return atMost(goal) - atMost(goal - 1);
}

function lengthOfLongestSubstringKDistinct(s: string, k: number): number {
    if (k === 0) return 0;
    
    let left = 0;
    const charCount = new Map<string, number>();
    let maxLength = 0;
    
    for (let right = 0; right < s.length; right++) {
        const c = s[right];
        charCount.set(c, (charCount.get(c) || 0) + 1);
        
        while (charCount.size > k) {
            const leftChar = s[left];
            charCount.set(leftChar, charCount.get(leftChar)! - 1);
            if (charCount.get(leftChar) === 0) {
                charCount.delete(leftChar);
            }
            left++;
        }
        
        maxLength = Math.max(maxLength, right - left + 1);
    }
    
    return maxLength;
}

function maxScore(cardPoints: number[], k: number): number {
    const n = cardPoints.length;
    const totalSum = cardPoints.reduce((sum, num) => sum + num, 0);
    
    if (k === n) return totalSum;
    
    const windowSize = n - k;
    let currentSum = 0;
    
    for (let i = 0; i < windowSize; i++) {
        currentSum += cardPoints[i];
    }
    
    let minSum = currentSum;
    
    for (let i = windowSize; i < n; i++) {
        currentSum += cardPoints[i] - cardPoints[i - windowSize];
        minSum = Math.min(minSum, currentSum);
    }
    
    return totalSum - minSum;
}

function maxSumTwoNoOverlap(nums: number[], firstLen: number, secondLen: number): number {
    const n = nums.length;
    const prefix = [0];
    
    for (const num of nums) {
        prefix.push(prefix[prefix.length - 1] + num);
    }
    
    let maxSum = 0;
    
    // firstLen before secondLen
    let maxFirst = 0;
    for (let i = firstLen; i <= n - secondLen; i++) {
        maxFirst = Math.max(maxFirst, prefix[i] - prefix[i - firstLen]);
        maxSum = Math.max(maxSum, maxFirst + prefix[i + secondLen] - prefix[i]);
    }
    
    // secondLen before firstLen
    let maxSecond = 0;
    for (let i = secondLen; i <= n - firstLen; i++) {
        maxSecond = Math.max(maxSecond, prefix[i] - prefix[i - secondLen]);
        maxSum = Math.max(maxSum, maxSecond + prefix[i + firstLen] - prefix[i]);
    }
    
    return maxSum;
}

// Test
if (require.main === module) {
    console.log("Longest Subarray Sum K:", maxSubArrayLenPositive([1,2,3,4,5], 9));
    console.log("Min Subarray Len:", minSubArrayLen(7, [2,3,1,2,4,3]));
    console.log("Binary Subarrays:", numSubarraysWithSum([1,0,1,0,1], 2));
    console.log("K Distinct:", lengthOfLongestSubstringKDistinct("eceba", 2));
    console.log("Max Score Cards:", maxScore([1,2,3,4,5,6,1], 3));
    console.log("Max Sum Two No Overlap:", maxSumTwoNoOverlap([0,6,5,2,2,5,1,9,4], 1, 2));
}

export { maxSubArrayLenPositive, minSubArrayLen, numSubarraysWithSum, lengthOfLongestSubstringKDistinct, maxScore, maxSumTwoNoOverlap };
