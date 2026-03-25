/**
 * K Distinct Characters - Sliding Window Pattern
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(k)
 */

function lengthOfLongestSubstringKDistinct(s: string, k: number): number {
    if (k === 0) return 0;
    
    const charCount = new Map<string, number>();
    let left = 0, maxLen = 0;
    
    for (let right = 0; right < s.length; right++) {
        const c = s[right];
        charCount.set(c, (charCount.get(c) || 0) + 1);
        
        while (charCount.size > k) {
            const leftChar = s[left];
            charCount.set(leftChar, charCount.get(leftChar)! - 1);
            if (charCount.get(leftChar) === 0) {
                charCount.delete(leftChar);
            }
            left++;
        }
        
        maxLen = Math.max(maxLen, right - left + 1);
    }
    
    return maxLen;
}

function lengthOfLongestSubstring(s: string): number {
    const charIndex = new Map<string, number>();
    let left = 0, maxLen = 0;
    
    for (let right = 0; right < s.length; right++) {
        const c = s[right];
        
        if (charIndex.has(c) && charIndex.get(c)! >= left) {
            left = charIndex.get(c)! + 1;
        }
        
        charIndex.set(c, right);
        maxLen = Math.max(maxLen, right - left + 1);
    }
    
    return maxLen;
}

function characterReplacement(s: string, k: number): number {
    const count = new Map<string, number>();
    let left = 0, maxFreq = 0, maxLen = 0;
    
    for (let right = 0; right < s.length; right++) {
        const c = s[right];
        count.set(c, (count.get(c) || 0) + 1);
        maxFreq = Math.max(maxFreq, count.get(c)!);
        
        while ((right - left + 1) - maxFreq > k) {
            const leftChar = s[left];
            count.set(leftChar, count.get(leftChar)! - 1);
            left++;
        }
        
        maxLen = Math.max(maxLen, right - left + 1);
    }
    
    return maxLen;
}

function totalFruit(fruits: number[]): number {
    const basket = new Map<number, number>();
    let left = 0, maxFruits = 0;
    
    for (let right = 0; right < fruits.length; right++) {
        basket.set(fruits[right], (basket.get(fruits[right]) || 0) + 1);
        
        while (basket.size > 2) {
            basket.set(fruits[left], basket.get(fruits[left])! - 1);
            if (basket.get(fruits[left]) === 0) {
                basket.delete(fruits[left]);
            }
            left++;
        }
        
        maxFruits = Math.max(maxFruits, right - left + 1);
    }
    
    return maxFruits;
}

// Test
if (require.main === module) {
    console.log("K Distinct:", lengthOfLongestSubstringKDistinct("eceba", 2));
    console.log("No Repeating:", lengthOfLongestSubstring("abcabcbb"));
    console.log("Character Replacement:", characterReplacement("ABAB", 2));
    console.log("Total Fruit:", totalFruit([1,2,1]));
}

export { lengthOfLongestSubstringKDistinct, lengthOfLongestSubstring, characterReplacement, totalFruit };
