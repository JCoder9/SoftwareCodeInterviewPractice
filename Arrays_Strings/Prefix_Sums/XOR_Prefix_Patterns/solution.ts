/**
 * XOR Prefix - Find Subarrays with XOR = K
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */

function countSubarraysXorK(nums: number[], k: number): number {
    let count = 0;
    let xorPrefix = 0;
    const prefixMap = new Map<number, number>();
    prefixMap.set(0, 1);
    
    for (const num of nums) {
        xorPrefix ^= num;
        
        const target = xorPrefix ^ k;
        if (prefixMap.has(target)) {
            count += prefixMap.get(target)!;
        }
        
        prefixMap.set(xorPrefix, (prefixMap.get(xorPrefix) || 0) + 1);
    }
    
    return count;
}

function xorQueries(nums: number[], queries: number[][]): number[] {
    const n = nums.length;
    const prefixXor: number[] = [0];
    
    for (const num of nums) {
        prefixXor.push(prefixXor[prefixXor.length - 1] ^ num);
    }
    
    return queries.map(([left, right]) => prefixXor[right + 1] ^ prefixXor[left]);
}

function singleNumber(nums: number[]): number {
    let result = 0;
    for (const num of nums) {
        result ^= num;
    }
    return result;
}

function singleNumberIII(nums: number[]): number[] {
    let xorAll = 0;
    for (const num of nums) {
        xorAll ^= num;
    }
    
    const rightmostBit = xorAll & (-xorAll);
    
    let a = 0, b = 0;
    for (const num of nums) {
        if ((num & rightmostBit) === 0) {
            a ^= num;
        } else {
            b ^= num;
        }
    }
    
    return [a, b];
}

function missingNumber(nums: number[]): number {
    let xorAll = 0;
    const n = nums.length;
    
    for (let i = 0; i <= n; i++) {
        xorAll ^= i;
    }
    
    for (const num of nums) {
        xorAll ^= num;
    }
    
    return xorAll;
}

// Test
if (require.main === module) {
    console.log("Count XOR = K:", countSubarraysXorK([4,2,2,6,4], 6));
    console.log("XOR Queries:", xorQueries([1,3,4,8], [[0,1],[1,2],[0,3],[3,3]]));
    console.log("Single Number:", singleNumber([4,1,2,1,2]));
    console.log("Single Number III:", singleNumberIII([1,2,1,3,2,5]));
    console.log("Missing Number:", missingNumber([3,0,1]));
}

export { countSubarraysXorK, xorQueries, singleNumber, singleNumberIII, missingNumber };
