/**
 * Valid Anagram - Basic Frequency Check
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */

function isAnagram(s: string, t: string): boolean {
    if (s.length !== t.length) return false;
    
    const count = new Array(26).fill(0);
    
    for (let i = 0; i < s.length; i++) {
        count[s.charCodeAt(i) - 97]++;
        count[t.charCodeAt(i) - 97]--;
    }
    
    return count.every(c => c === 0);
}

function isIsomorphic(s: string, t: string): boolean {
    if (s.length !== t.length) return false;
    
    const sToT = new Map<string, string>();
    const tToS = new Map<string, string>();
    
    for (let i = 0; i < s.length; i++) {
        const c1 = s[i], c2 = t[i];
        
        if (sToT.has(c1)) {
            if (sToT.get(c1) !== c2) return false;
        } else {
            sToT.set(c1, c2);
        }
        
        if (tToS.has(c2)) {
            if (tToS.get(c2) !== c1) return false;
        } else {
            tToS.set(c2, c1);
        }
    }
    
    return true;
}

function wordPattern(pattern: string, s: string): boolean {
    const words = s.split(' ');
    
    if (pattern.length !== words.length) return false;
    
    const charToWord = new Map<string, string>();
    const wordToChar = new Map<string, string>();
    
    for (let i = 0; i < pattern.length; i++) {
        const c = pattern[i];
        const word = words[i];
        
        if (charToWord.has(c)) {
            if (charToWord.get(c) !== word) return false;
        } else {
            charToWord.set(c, word);
        }
        
        if (wordToChar.has(word)) {
            if (wordToChar.get(word) !== c) return false;
        } else {
            wordToChar.set(word, c);
        }
    }
    
    return true;
}

// Test
if (require.main === module) {
    console.log("Valid Anagram:", isAnagram("anagram", "nagaram"));
    console.log("Isomorphic:", isIsomorphic("egg", "add"));
    console.log("Word Pattern:", wordPattern("abba", "dog cat cat dog"));
}

export { isAnagram, isIsomorphic, wordPattern };
