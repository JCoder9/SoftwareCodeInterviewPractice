/**
 * Sort by Custom Criteria Pattern
 * 
 * Time Complexity: O(n log n)
 * Space Complexity: O(n)
 */

function largestNumber(nums: number[]): string {
    const strs = nums.map(String);
    
    strs.sort((a, b) => {
        if (b + a > a + b) return 1;
        if (b + a < a + b) return -1;
        return 0;
    });
    
    if (strs[0] === "0") return "0";
    
    return strs.join("");
}

function sortArrayByParity(nums: number[]): number[] {
    return nums.sort((a, b) => (a % 2) - (b % 2));
}

function sortColors(nums: number[]): void {
    let low = 0, mid = 0, high = nums.length - 1;
    
    while (mid <= high) {
        if (nums[mid] === 0) {
            [nums[low], nums[mid]] = [nums[mid], nums[low]];
            low++;
            mid++;
        } else if (nums[mid] === 1) {
            mid++;
        } else {
            [nums[mid], nums[high]] = [nums[high], nums[mid]];
            high--;
        }
    }
}

function customSortString(order: string, s: string): string {
    const orderMap = new Map<string, number>();
    for (let i = 0; i < order.length; i++) {
        orderMap.set(order[i], i);
    }
    
    return s.split('')
        .sort((a, b) => (orderMap.get(a) ?? 26) - (orderMap.get(b) ?? 26))
        .join('');
}

// Test
if (require.main === module) {
    console.log("Largest Number:", largestNumber([10,2]));
    console.log("Sort by Parity:", sortArrayByParity([3,1,2,4]));
    
    const colors = [2,0,2,1,1,0];
    sortColors(colors);
    console.log("Sort Colors:", colors);
    
    console.log("Custom Sort:", customSortString("cba", "abcd"));
}

export { largestNumber, sortArrayByParity, sortColors, customSortString };
