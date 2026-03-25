/**
 * Binary Search - On Answer Space
 * 
 * Time Complexity: O(n log(max - min))
 * Space Complexity: O(1)
 */

function minEatingSpeed(piles: number[], h: number): number {
    let left = 1, right = Math.max(...piles);
    
    function canFinish(speed: number): boolean {
        let hours = 0;
        for (const pile of piles) {
            hours += Math.ceil(pile / speed);
        }
        return hours <= h;
    }
    
    while (left < right) {
        const mid = Math.floor((left + right) / 2);
        if (canFinish(mid)) {
            right = mid;
        } else {
            left = mid + 1;
        }
    }
    
    return left;
}

function shipWithinDays(weights: number[], days: number): number {
    let left = Math.max(...weights);
    let right = weights.reduce((a, b) => a + b, 0);
    
    function canShip(capacity: number): boolean {
        let daysNeeded = 1;
        let currentWeight = 0;
        
        for (const weight of weights) {
            if (currentWeight + weight > capacity) {
                daysNeeded++;
                currentWeight = weight;
            } else {
                currentWeight += weight;
            }
        }
        
        return daysNeeded <= days;
    }
    
    while (left < right) {
        const mid = Math.floor((left + right) / 2);
        if (canShip(mid)) {
            right = mid;
        } else {
            left = mid + 1;
        }
    }
    
    return left;
}

function findPeakElement(nums: number[]): number {
    let left = 0, right = nums.length - 1;
    
    while (left < right) {
        const mid = Math.floor((left + right) / 2);
        
        if (nums[mid] < nums[mid + 1]) {
            left = mid + 1;
        } else {
            right = mid;
        }
    }
    
    return left;
}

// Test
if (require.main === module) {
    console.log("Min eating speed:", minEatingSpeed([3, 6, 7, 11], 8));
    console.log("Min ship capacity:", shipWithinDays([1,2,3,4,5,6,7,8,9,10], 5));
    console.log("Find peak:", findPeakElement([1, 2, 3, 1]));
}

export { minEatingSpeed, shipWithinDays, findPeakElement };
