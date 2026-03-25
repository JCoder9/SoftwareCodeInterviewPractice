/**
 * Minimum Size Subarray Sum >= K
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */

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

function minOperations(nums: number[], x: number): number {
    const target = nums.reduce((sum, num) => sum + num, 0) - x;
    
    if (target < 0) return -1;
    if (target === 0) return nums.length;
    
    let left = 0;
    let currentSum = 0;
    let maxLength = -1;
    
    for (let right = 0; right < nums.length; right++) {
        currentSum += nums[right];
        
        while (currentSum > target && left <= right) {
            currentSum -= nums[left];
            left++;
        }
        
        if (currentSum === target) {
            maxLength = Math.max(maxLength, right - left + 1);
        }
    }
    
    return maxLength !== -1 ? nums.length - maxLength : -1;
}

function shortestSubarray(nums: number[], k: number): number {
    const n = nums.length;
    const prefix: number[] = [0];
    
    for (const num of nums) {
        prefix.push(prefix[prefix.length - 1] + num);
    }
    
    const dq: number[] = [];
    let minLength = Infinity;
    
    for (let i = 0; i <= n; i++) {
        while (dq.length > 0 && prefix[i] - prefix[dq[0]] >= k) {
            minLength = Math.min(minLength, i - dq.shift()!);
        }
        
        while (dq.length > 0 && prefix[i] <= prefix[dq[dq.length - 1]]) {
            dq.pop();
        }
        
        dq.push(i);
    }
    
    return minLength === Infinity ? -1 : minLength;
}

function maxConsecutiveAnswers(answerKey: string, k: number): number {
    function maxLengthWithChar(char: string): number {
        let left = 0;
        let flips = 0;
        let maxLen = 0;
        
        for (let right = 0; right < answerKey.length; right++) {
            if (answerKey[right] !== char) {
                flips++;
            }
            
            while (flips > k) {
                if (answerKey[left] !== char) {
                    flips--;
                }
                left++;
            }
            
            maxLen = Math.max(maxLen, right - left + 1);
        }
        
        return maxLen;
    }
    
    return Math.max(maxLengthWithChar('T'), maxLengthWithChar('F'));
}

function longestOnes(nums: number[], k: number): number {
    let left = 0;
    let zeros = 0;
    let maxLength = 0;
    
    for (let right = 0; right < nums.length; right++) {
        if (nums[right] === 0) {
            zeros++;
        }
        
        while (zeros > k) {
            if (nums[left] === 0) {
                zeros--;
            }
            left++;
        }
        
        maxLength = Math.max(maxLength, right - left + 1);
    }
    
    return maxLength;
}

function characterReplacement(s: string, k: number): number {
    const count = new Array(26).fill(0);
    let left = 0;
    let maxFreq = 0;
    let maxLength = 0;
    
    for (let right = 0; right < s.length; right++) {
        const idx = s.charCodeAt(right) - 'A'.charCodeAt(0);
        count[idx]++;
        maxFreq = Math.max(maxFreq, count[idx]);
        
        while ((right - left + 1) - maxFreq > k) {
            const leftIdx = s.charCodeAt(left) - 'A'.charCodeAt(0);
            count[leftIdx]--;
            left++;
        }
        
        maxLength = Math.max(maxLength, right - left + 1);
    }
    
    return maxLength;
}

function minWindow(s: string, t: string): string {
    if (s.length === 0 || t.length === 0) return "";
    
    const need = new Map<string, number>();
    for (const c of t) {
        need.set(c, (need.get(c) || 0) + 1);
    }
    
    const have = new Map<string, number>();
    const required = need.size;
    let formed = 0;
    
    let left = 0;
    let minLength = Infinity;
    let result = [0, 0];
    
    for (let right = 0; right < s.length; right++) {
        const c = s[right];
        have.set(c, (have.get(c) || 0) + 1);
        
        if (need.has(c) && have.get(c) === need.get(c)) {
            formed++;
        }
        
        while (formed === required && left <= right) {
            if (right - left + 1 < minLength) {
                minLength = right - left + 1;
                result = [left, right];
            }
            
            const leftChar = s[left];
            have.set(leftChar, have.get(leftChar)! - 1);
            if (need.has(leftChar) && have.get(leftChar)! < need.get(leftChar)!) {
                formed--;
            }
            
            left++;
        }
    }
    
    return minLength === Infinity ? "" : s.substring(result[0], result[1] + 1);
}

// Test
if (require.main === module) {
    console.log("Min Subarray Len:", minSubArrayLen(7, [2,3,1,2,4,3]));
    console.log("Min Operations:", minOperations([1,1,4,2,3], 5));
    console.log("Shortest Subarray:", shortestSubarray([2,-1,2], 3));
    console.log("Max Consecutive Answers:", maxConsecutiveAnswers("TTFF", 2));
    console.log("Longest Ones:", longestOnes([1,1,1,0,0,0,1,1,1,1,0], 2));
    console.log("Character Replacement:", characterReplacement("ABAB", 2));
    console.log("Min Window:", minWindow("ADOBECODEBANC", "ABC"));
}

export { minSubArrayLen, minOperations, shortestSubarray, maxConsecutiveAnswers, longestOnes, characterReplacement, minWindow };
