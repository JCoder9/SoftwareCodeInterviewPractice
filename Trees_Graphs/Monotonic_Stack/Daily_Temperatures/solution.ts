/**
 * Monotonic Stack - Daily Temperatures
 * Time: O(n), Space: O(n)
 */
function dailyTemperatures(temperatures: number[]): number[] {
    const n = temperatures.length;
    const result: number[] = new Array(n).fill(0);
    const stack: number[] = [];
    
    for (let i = 0; i < n; i++) {
        // Found warmer day
        while (stack.length > 0 && 
               temperatures[i] > temperatures[stack[stack.length - 1]]) {
            const prevDay = stack.pop()!;
            result[prevDay] = i - prevDay;
        }
        stack.push(i);
    }
    
    return result;
}

const temps = [73, 74, 75, 71, 69, 72, 76, 73];
console.log(dailyTemperatures(temps)); // [1, 1, 4, 2, 1, 1, 0, 0]
