/**
 * Find All Anagrams - Sliding Window + Frequency
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */

function findAnagrams(s: string, p: string): number[] {
    const result: number[] = [];
    if (p.length > s.length) return result;
    
    const pCount = new Array(26).fill(0);
    const sCount = new Array(26).fill(0);
    
    for (const c of p) {
        pCount[c.charCodeAt(0) - 97]++;
    }
    
    const windowSize = p.length;
    
    for (let i = 0; i < s.length; i++) {
        sCount[s.charCodeAt(i) - 97]++;
        
        if (i >= windowSize) {
            sCount[s.charCodeAt(i - windowSize) - 97]--;
        }
        
        if (arraysEqual(sCount, pCount)) {
            result.push(i - windowSize + 1);
        }
    }
    
    return result;
}

function arraysEqual(a: number[], b: number[]): boolean {
    return a.every((val, i) => val === b[i]);
}

function checkInclusion(s1: string, s2: string): boolean {
    if (s1.length > s2.length) return false;
    
    const s1Count = new Array(26).fill(0);
    const s2Count = new Array(26).fill(0);
    
    for (const c of s1) {
        s1Count[c.charCodeAt(0) - 97]++;
    }
    
    for (let i = 0; i < s2.length; i++) {
        s2Count[s2.charCodeAt(i) - 97]++;
        
        if (i >= s1.length) {
            s2Count[s2.charCodeAt(i - s1.length) - 97]--;
        }
        
        if (arraysEqual(s1Count, s2Count)) {
            return true;
        }
    }
    
    return false;
}

function minWindow(s: string, t: string): string {
    const tCount = new Map<string, number>();
    for (const c of t) {
        tCount.set(c, (tCount.get(c) || 0) + 1);
    }
    
    const windowCount = new Map<string, number>();
    const required = tCount.size;
    let formed = 0;
    
    let left = 0;
    let minLen = Infinity;
    let minLeft = 0;
    
    for (let right = 0; right < s.length; right++) {
        const c = s[right];
        windowCount.set(c, (windowCount.get(c) || 0) + 1);
        
        if (tCount.has(c) && windowCount.get(c) === tCount.get(c)) {
            formed++;
        }
        
        while (left <= right && formed === required) {
            if (right - left + 1 < minLen) {
                minLen = right - left + 1;
                minLeft = left;
            }
            
            const leftChar = s[left];
            windowCount.set(leftChar, windowCount.get(leftChar)! - 1);
            if (tCount.has(leftChar) && windowCount.get(leftChar)! < tCount.get(leftChar)!) {
                formed--;
            }
            
            left++;
        }
    }
    
    return minLen === Infinity ? "" : s.substring(minLeft, minLeft + minLen);
}

// Test
if (require.main === module) {
    console.log("Find Anagrams:", findAnagrams("cbaebabacd", "abc"));
    console.log("Check Inclusion:", checkInclusion("ab", "eidbaooo"));
    console.log("Min Window:", minWindow("ADOBECODEBANC", "ABC"));
}

export { findAnagrams, checkInclusion, minWindow };
