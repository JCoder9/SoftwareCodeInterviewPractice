/**
 * Binary Search on Answer - Capacity/Resource Allocation
 * Time: O(n * log(max_pile)), Space: O(1)
 */
function minEatingSpeed(piles: number[], h: number): number {
    function canFinish(speed: number): boolean {
        let hours = 0;
        
        for (const pile of piles) {
            hours += Math.ceil(pile / speed);
            if (hours > h) {
                return false;
            }
        }
        
        return true;
    }
    
    let left = 1;
    let right = Math.max(...piles);
    
    while (left < right) {
        const mid = Math.floor(left + (right - left) / 2);
        
        if (canFinish(mid)) {
            right = mid;
        } else {
            left = mid + 1;
        }
    }
    
    return left;
}

console.log(minEatingSpeed([3, 6, 7, 11], 8));  // 4
