/**
 * Binary Search on Answer - Binary Search on Real Numbers
 * Time: O(log(x / precision)), Space: O(1)
 */
function sqrt(x: number, precision: number = 1e-6): number {
    if (x < 0) {
        return -1;
    }
    if (x === 0) {
        return 0;
    }
    
    let left = 0.0;
    let right = Math.max(1.0, x);
    
    while (right - left > precision) {
        const mid = left + (right - left) / 2;
        const square = mid * mid;
        
        if (square < x) {
            left = mid;
        } else if (square > x) {
            right = mid;
        } else {
            return mid;
        }
    }
    
    return left;
}

console.log(sqrt(2).toFixed(6));   // 1.414214
console.log(sqrt(10).toFixed(6));  // 3.162278
