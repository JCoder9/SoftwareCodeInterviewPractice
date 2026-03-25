/**
 * Binary Search on Answer - Binary Search with Constraints
 * Time: O(n * log(max_speed)), Space: O(1)
 */
function minSpeedOnTime(dist: number[], hour: number): number {
    function canArrive(speed: number): boolean {
        let time = 0;
        
        for (let i = 0; i < dist.length - 1; i++) {
            time += Math.ceil(dist[i] / speed);
        }
        
        time += dist[dist.length - 1] / speed;
        
        return time <= hour;
    }
    
    // Edge case
    if (dist.length > Math.ceil(hour)) {
        return -1;
    }
    
    let left = 1;
    let right = 10_000_000;
    
    while (left < right) {
        const mid = Math.floor(left + (right - left) / 2);
        
        if (canArrive(mid)) {
            right = mid;
        } else {
            left = mid + 1;
        }
    }
    
    return left;
}

console.log(minSpeedOnTime([1, 3, 2], 6));     // 1
console.log(minSpeedOnTime([1, 3, 2], 2.7));   // 3
