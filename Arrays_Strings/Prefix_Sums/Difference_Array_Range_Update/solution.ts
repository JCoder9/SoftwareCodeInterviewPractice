/**
 * Difference Array - Range Update Pattern
 * 
 * Time Complexity: O(1) per update, O(n) to get result
 * Space Complexity: O(n)
 */

class DifferenceArray {
    private diff: number[];
    private size: number;
    
    constructor(size: number) {
        this.diff = new Array(size + 1).fill(0);
        this.size = size;
    }
    
    rangeAdd(left: number, right: number, val: number): void {
        this.diff[left] += val;
        this.diff[right + 1] -= val;
    }
    
    getResult(): number[] {
        const result: number[] = [];
        let current = 0;
        for (let i = 0; i < this.size; i++) {
            current += this.diff[i];
            result.push(current);
        }
        return result;
    }
}

function corpFlightBookings(bookings: number[][], n: number): number[] {
    const da = new DifferenceArray(n);
    
    for (const [first, last, seats] of bookings) {
        da.rangeAdd(first - 1, last - 1, seats);
    }
    
    return da.getResult();
}

function rangeAddition(length: number, updates: number[][]): number[] {
    const diff = new Array(length + 1).fill(0);
    
    for (const [start, end, inc] of updates) {
        diff[start] += inc;
        diff[end + 1] -= inc;
    }
    
    const result: number[] = [];
    let current = 0;
    for (let i = 0; i < length; i++) {
        current += diff[i];
        result.push(current);
    }
    
    return result;
}

function carPooling(trips: number[][], capacity: number): boolean {
    const maxLoc = Math.max(...trips.map(t => t[2]));
    const diff = new Array(maxLoc + 1).fill(0);
    
    for (const [passengers, start, end] of trips) {
        diff[start] += passengers;
        diff[end] -= passengers;
    }
    
    let current = 0;
    for (const delta of diff) {
        current += delta;
        if (current > capacity) {
            return false;
        }
    }
    
    return true;
}

// Test
if (require.main === module) {
    const da = new DifferenceArray(5);
    da.rangeAdd(1, 3, 2);
    da.rangeAdd(0, 2, 3);
    console.log("Difference Array:", da.getResult());
    
    console.log("Flight bookings:", corpFlightBookings([[1,2,10],[2,3,20],[2,5,25]], 5));
    console.log("Car Pooling:", carPooling([[2,1,5],[3,3,7]], 5));
}

export { DifferenceArray, corpFlightBookings, rangeAddition, carPooling };
