/**
 * Sort + Greedy Selection Pattern
 * 
 * Time Complexity: O(n log n)
 * Space Complexity: O(1) to O(n)
 */

function maximumUnits(boxTypes: number[][], truckSize: number): number {
    boxTypes.sort((a, b) => b[1] - a[1]);
    
    let totalUnits = 0;
    
    for (const [boxes, unitsPerBox] of boxTypes) {
        if (truckSize <= 0) break;
        
        const boxesToTake = Math.min(boxes, truckSize);
        totalUnits += boxesToTake * unitsPerBox;
        truckSize -= boxesToTake;
    }
    
    return totalUnits;
}

function findContentChildren(g: number[], s: number[]): number {
    g.sort((a, b) => a - b);
    s.sort((a, b) => a - b);
    
    let child = 0, cookie = 0;
    
    while (child < g.length && cookie < s.length) {
        if (s[cookie] >= g[child]) {
            child++;
        }
        cookie++;
    }
    
    return child;
}

function numRescueBoats(people: number[], limit: number): number {
    people.sort((a, b) => a - b);
    
    let left = 0, right = people.length - 1;
    let boats = 0;
    
    while (left <= right) {
        if (people[left] + people[right] <= limit) {
            left++;
        }
        right--;
        boats++;
    }
    
    return boats;
}

function carPooling(trips: number[][], capacity: number): boolean {
    const events: [number, number][] = [];
    
    for (const [passengers, start, end] of trips) {
        events.push([start, passengers]);
        events.push([end, -passengers]);
    }
    
    events.sort((a, b) => a[0] - b[0]);
    
    let currentPassengers = 0;
    for (const [_, change] of events) {
        currentPassengers += change;
        if (currentPassengers > capacity) return false;
    }
    
    return true;
}

// Test
if (require.main === module) {
    console.log("Max Units:", maximumUnits([[1,3],[2,2],[3,1]], 4));
    console.log("Assign Cookies:", findContentChildren([1,2,3], [1,1]));
    console.log("Boats:", numRescueBoats([1,2], 3));
    console.log("Car Pooling:", carPooling([[2,1,5],[3,3,7]], 4));
}

export { maximumUnits, findContentChildren, numRescueBoats, carPooling };
