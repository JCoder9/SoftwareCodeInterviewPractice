/**
 * Binary Search on Answer - Maximize the Minimum
 * Time: O(n log n + n * log(max_pos - min_pos)), Space: O(1)
 */
function maxDistance(position: number[], m: number): number {
    function canPlace(minDist: number): boolean {
        let count = 1;
        let lastPos = position[0];
        
        for (let i = 1; i < position.length; i++) {
            if (position[i] - lastPos >= minDist) {
                count++;
                lastPos = position[i];
                if (count === m) {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    position.sort((a, b) => a - b);
    
    let left = 1;
    let right = position[position.length - 1] - position[0];
    
    while (left < right) {
        const mid = Math.floor(left + (right - left + 1) / 2);
        
        if (canPlace(mid)) {
            left = mid;
        } else {
            right = mid - 1;
        }
    }
    
    return left;
}

console.log(maxDistance([1, 2, 3, 4, 7], 3));  // 3
