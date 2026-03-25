/**
 * Fast/Slow Pointers - Happy Number Pattern
 * 
 * Related LeetCode Problems:
 * - LC 202: Happy Number (Easy)
 * - LC 258: Add Digits (Easy)
 * - LC 263, 264: Ugly Number I & II
 * 
 * Time: O(log n), Space: O(1)
 */

/**
 * LC 202: Happy Number
 */
function isHappy(n: number): boolean {
    function getNext(num: number): number {
        let totalSum = 0;
        while (num > 0) {
            const digit = num % 10;
            totalSum += digit * digit;
            num = Math.floor(num / 10);
        }
        return totalSum;
    }
    
    let slow = n;
    let fast = n;
    
    while (true) {
        slow = getNext(slow);
        fast = getNext(getNext(fast));
        
        if (fast === 1) {
            return true;
        }
        
        if (slow === fast) {
            return false;
        }
    }
}

/**
 * LC 258: Add Digits
 */
function addDigits(num: number): number {
    function getSum(n: number): number {
        let total = 0;
        while (n > 0) {
            total += n % 10;
            n = Math.floor(n / 10);
        }
        return total;
    }
    
    while (num >= 10) {
        num = getSum(num);
    }
    
    return num;
}

/**
 * LC 258: Mathematical solution
 */
function addDigitsMath(num: number): number {
    if (num === 0) return 0;
    if (num % 9 === 0) return 9;
    return num % 9;
}

/**
 * LC 263: Ugly Number
 */
function isUgly(n: number): boolean {
    if (n <= 0) {
        return false;
    }
    
    for (const factor of [2, 3, 5]) {
        while (n % factor === 0) {
            n = Math.floor(n / factor);
        }
    }
    
    return n === 1;
}

/**
 * LC 264: Ugly Number II
 */
function nthUglyNumber(n: number): number {
    const ugly: number[] = [1];
    let i2 = 0, i3 = 0, i5 = 0;
    
    while (ugly.length < n) {
        const next2 = ugly[i2] * 2;
        const next3 = ugly[i3] * 3;
        const next5 = ugly[i5] * 5;
        
        const nextUgly = Math.min(next2, next3, next5);
        ugly.push(nextUgly);
        
        if (nextUgly === next2) i2++;
        if (nextUgly === next3) i3++;
        if (nextUgly === next5) i5++;
    }
    
    return ugly[n - 1];
}

/**
 * LC 1812: Determine Color of a Chessboard Square
 */
function squareIsWhite(coordinates: string): boolean {
    const col = coordinates.charCodeAt(0) - 'a'.charCodeAt(0) + 1;
    const row = parseInt(coordinates[1]);
    
    return (col + row) % 2 === 1;
}

// Test
if (require.main === module) {
    // Test isHappy
    console.log("Testing isHappy:");
    console.log(`19 is happy: ${isHappy(19)}`);  // true
    console.log(`2 is happy: ${isHappy(2)}`);    // false
    
    // Test addDigits
    console.log("\nTesting addDigits:");
    console.log(`addDigits(38): ${addDigits(38)}`);  // 2
    console.log(`addDigits(38) math: ${addDigitsMath(38)}`);  // 2
    
    // Test isUgly
    console.log("\nTesting isUgly:");
    console.log(`6 is ugly: ${isUgly(6)}`);    // true
    console.log(`14 is ugly: ${isUgly(14)}`);  // false
    
    // Test nthUglyNumber
    console.log("\nTesting nth UglyNumber:");
    console.log(`10th ugly number: ${nthUglyNumber(10)}`);  // 12
}

export { isHappy, addDigits, addDigitsMath, isUgly, nthUglyNumber, squareIsWhite };
