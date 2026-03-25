/**
 * Frequency Counting and Anagrams - Hash Map Pattern
 * 
 * Problem: Use hash maps to count character/element frequencies for comparison.
 * 
 * Pattern: Build frequency maps and compare them (for anagrams, permutations, etc.)
 * 
 * Time Complexity: O(n) for building frequency map
 * Space Complexity: O(k) where k is unique elements
 */

/**
 * Check if two strings are anagrams.
 */
function isAnagram(s: string, t: string): boolean {
    if (s.length !== t.length) return false;
    
    const count = new Map<string, number>();
    
    for (const c of s) {
        count.set(c, (count.get(c) || 0) + 1);
    }
    
    for (const c of t) {
        if (!count.has(c)) return false;
        count.set(c, count.get(c)! - 1);
        if (count.get(c)! < 0) return false;
    }
    
    return true;
}

/**
 * Group strings that are anagrams of each other.
 */
function groupAnagrams(strs: string[]): string[][] {
    const groups = new Map<string, string[]>();
    
    for (const s of strs) {
        // Sort string to create key
        const key = s.split('').sort().join('');
        
        if (!groups.has(key)) {
            groups.set(key, []);
        }
        groups.get(key)!.push(s);
    }
    
    return Array.from(groups.values());
}

// Test cases
if (require.main === module) {
    console.log("Is Anagram:");
    console.log("  " + isAnagram("anagram", "nagaram"));
    console.log("  " + isAnagram("rat", "car"));
    
    console.log("\nGroup Anagrams:");
    const strs = ["eat", "tea", "tan", "ate", "nat", "bat"];
    const groups = groupAnagrams(strs);
    console.log("  " + JSON.stringify(groups));
}

export { isAnagram, groupAnagrams };
