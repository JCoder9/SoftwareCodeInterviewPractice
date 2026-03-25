/**
 * Longest Subarray Sum K - With Negatives
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */

function maxSubArrayLen(nums: number[], k: number): number {
    const prefixMap = new Map<number, number>();
    prefixMap.set(0, -1);
    
    let prefixSum = 0;
    let maxLength = 0;
    
    for (let i = 0; i < nums.length; i++) {
        prefixSum += nums[i];
        
        if (prefixMap.has(prefixSum - k)) {
            maxLength = Math.max(maxLength, i - prefixMap.get(prefixSum - k)!);
        }
        
        if (!prefixMap.has(prefixSum)) {
            prefixMap.set(prefixSum, i);
        }
    }
    
    return maxLength;
}

function longestWPI(hours: number[]): number {
    const prefixMap = new Map<number, number>();
    prefixMap.set(0, -1);
    
    let prefixSum = 0;
    let maxLength = 0;
    
    for (let i = 0; i < hours.length; i++) {
        prefixSum += hours[i] > 8 ? 1 : -1;
        
        if (prefixSum > 0) {
            maxLength = i + 1;
        } else {
            if (prefixMap.has(prefixSum - 1)) {
                maxLength = Math.max(maxLength, i - prefixMap.get(prefixSum - 1)!);
            }
        }
        
        if (!prefixMap.has(prefixSum)) {
            prefixMap.set(prefixSum, i);
        }
    }
    
    return maxLength;
}

function numSubmatrixSumTarget(matrix: number[][], target: number): number {
    const rows = matrix.length;
    const cols = matrix[0].length;
    let count = 0;
    
    for (let top = 0; top < rows; top++) {
        const colSums = new Array(cols).fill(0);
        
        for (let bottom = top; bottom < rows; bottom++) {
            for (let c = 0; c < cols; c++) {
                colSums[c] += matrix[bottom][c];
            }
            
            const prefixCount = new Map<number, number>();
            prefixCount.set(0, 1);
            let prefixSum = 0;
            
            for (const colSum of colSums) {
                prefixSum += colSum;
                count += prefixCount.get(prefixSum - target) || 0;
                prefixCount.set(prefixSum, (prefixCount.get(prefixSum) || 0) + 1);
            }
        }
    }
    
    return count;
}

function findMaxAverage(nums: number[], k: number): number {
    let currentSum = 0;
    
    for (let i = 0; i < k; i++) {
        currentSum += nums[i];
    }
    
    let maxSum = currentSum;
    
    for (let i = k; i < nums.length; i++) {
        currentSum += nums[i] - nums[i - k];
        maxSum = Math.max(maxSum, currentSum);
    }
    
    return maxSum / k;
}

function findMaxAverageII(nums: number[], k: number): number {
    let left = Math.min(...nums);
    let right = Math.max(...nums);
    
    const epsilon = 1e-5;
    
    function canAchieveAvg(targetAvg: number): boolean {
        const n = nums.length;
        const prefix = [0];
        
        for (const num of nums) {
            prefix.push(prefix[prefix.length - 1] + (num - targetAvg));
        }
        
        let minPrefix = 0;
        
        for (let i = k; i <= n; i++) {
            if (i >= k) {
                minPrefix = Math.min(minPrefix, prefix[i - k]);
            }
            
            if (prefix[i] - minPrefix >= 0) {
                return true;
            }
        }
        
        return false;
    }
    
    while (right - left > epsilon) {
        const mid = (left + right) / 2;
        
        if (canAchieveAvg(mid)) {
            left = mid;
        } else {
            right = mid;
        }
    }
    
    return left;
}

// Test
if (require.main === module) {
    console.log("Max Subarray Len:", maxSubArrayLen([1,-1,5,-2,3], 3));
    console.log("Longest WPI:", longestWPI([9,9,6,0,6,6,9]));
    console.log("Submatrix Sum:", numSubmatrixSumTarget([[0,1,0],[1,1,1],[0,1,0]], 0));
    console.log("Max Average I:", findMaxAverage([1,12,-5,-6,50,3], 4));
    console.log("Max Average II:", findMaxAverageII([1,12,-5,-6,50,3], 4));
}

export { maxSubArrayLen, longestWPI, numSubmatrixSumTarget, findMaxAverage, findMaxAverageII };
