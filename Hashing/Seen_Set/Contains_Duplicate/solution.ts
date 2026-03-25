/**
 * Contains Duplicate - Boolean Check Pattern
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */

function containsDuplicate(nums: number[]): boolean {
    const seen = new Set<number>();
    
    for (const num of nums) {
        if (seen.has(num)) {
            return true;
        }
        seen.add(num);
    }
    
    return false;
}

function containsNearbyDuplicate(nums: number[], k: number): boolean {
    const window = new Set<number>();
    
    for (let i = 0; i < nums.length; i++) {
        if (window.has(nums[i])) {
            return true;
        }
        
        window.add(nums[i]);
        
        if (window.size > k) {
            window.delete(nums[i - k]);
        }
    }
    
    return false;
}

function containsNearbyAlmostDuplicate(nums: number[], indexDiff: number, valueDiff: number): boolean {
    if (valueDiff < 0) return false;
    
    const bucketSize = valueDiff + 1;
    const buckets = new Map<number, number>();
    
    const getBucketId = (num: number): number => {
        return num < 0 ? Math.floor((num + 1) / bucketSize) - 1 : Math.floor(num / bucketSize);
    };
    
    for (let i = 0; i < nums.length; i++) {
        const num = nums[i];
        const bucketId = getBucketId(num);
        
        if (buckets.has(bucketId)) {
            return true;
        }
        
        if (buckets.has(bucketId - 1) && 
            Math.abs(num - buckets.get(bucketId - 1)!) <= valueDiff) {
            return true;
        }
        
        if (buckets.has(bucketId + 1) && 
            Math.abs(num - buckets.get(bucketId + 1)!) <= valueDiff) {
            return true;
        }
        
        buckets.set(bucketId, num);
        
        if (i >= indexDiff) {
            buckets.delete(getBucketId(nums[i - indexDiff]));
        }
    }
    
    return false;
}

function findDuplicates(nums: number[]): number[] {
    const result: number[] = [];
    
    for (const num of nums) {
        const index = Math.abs(num) - 1;
        
        if (nums[index] < 0) {
            result.push(Math.abs(num));
        } else {
            nums[index] = -nums[index];
        }
    }
    
    return result;
}

function repeatedNTimes(nums: number[]): number {
    const seen = new Set<number>();
    
    for (const num of nums) {
        if (seen.has(num)) {
            return num;
        }
        seen.add(num);
    }
    
    return -1;
}

// Test
if (require.main === module) {
    console.log("Contains Duplicate:", containsDuplicate([1,2,3,1]));
    console.log("Contains Nearby Duplicate:", containsNearbyDuplicate([1,2,3,1], 3));
    console.log("Contains Nearby Almost Duplicate:", containsNearbyAlmostDuplicate([1,2,3,1], 3, 0));
    console.log("Find Duplicates:", findDuplicates([4,3,2,7,8,2,3,1]));
    console.log("Repeated N Times:", repeatedNTimes([1,2,3,3]));
}

export { containsDuplicate, containsNearbyDuplicate, containsNearbyAlmostDuplicate, findDuplicates, repeatedNTimes };
