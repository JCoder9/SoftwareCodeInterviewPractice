/**
 * Two Arrays Intersection - Unique Elements Pattern
 * 
 * Time Complexity: O(n + m)
 * Space Complexity: O(min(n, m))
 */

function intersection(nums1: number[], nums2: number[]): number[] {
    const set1 = new Set(nums1);
    const result = new Set<number>();
    
    for (const num of nums2) {
        if (set1.has(num)) {
            result.add(num);
        }
    }
    
    return Array.from(result);
}

function intersectionTwoPointers(nums1: number[], nums2: number[]): number[] {
    nums1.sort((a, b) => a - b);
    nums2.sort((a, b) => a - b);
    
    const result: number[] = [];
    let i = 0, j = 0;
    
    while (i < nums1.length && j < nums2.length) {
        if (nums1[i] < nums2[j]) {
            i++;
        } else if (nums1[i] > nums2[j]) {
            j++;
        } else {
            if (result.length === 0 || result[result.length - 1] !== nums1[i]) {
                result.push(nums1[i]);
            }
            i++;
            j++;
        }
    }
    
    return result;
}

function findRestaurant(list1: string[], list2: string[]): string[] {
    const indexMap = new Map<string, number>();
    
    for (let i = 0; i < list1.length; i++) {
        indexMap.set(list1[i], i);
    }
    
    let minSum = Infinity;
    const result: string[] = [];
    
    for (let j = 0; j < list2.length; j++) {
        if (indexMap.has(list2[j])) {
            const indexSum = indexMap.get(list2[j])! + j;
            
            if (indexSum < minSum) {
                minSum = indexSum;
                result.length = 0;
                result.push(list2[j]);
            } else if (indexSum === minSum) {
                result.push(list2[j]);
            }
        }
    }
    
    return result;
}

function findWords(words: string[]): string[] {
    const rows = [
        new Set('qwertyuiop'),
        new Set('asdfghjkl'),
        new Set('zxcvbnm')
    ];
    
    const result: string[] = [];
    
    for (const word of words) {
        const lower = word.toLowerCase();
        
        for (const row of rows) {
            if (lower.split('').every(c => row.has(c))) {
                result.push(word);
                break;
            }
        }
    }
    
    return result;
}

function uncommonFromSentences(s1: string, s2: string): string[] {
    const count = new Map<string, number>();
    
    const words = [...s1.split(' '), ...s2.split(' ')];
    
    for (const word of words) {
        count.set(word, (count.get(word) || 0) + 1);
    }
    
    const result: string[] = [];
    for (const [word, freq] of count) {
        if (freq === 1) {
            result.push(word);
        }
    }
    
    return result;
}

function removeVowels(s: string): string {
    const vowels = new Set('aeiou');
    return s.split('').filter(c => !vowels.has(c)).join('');
}

// Test
if (require.main === module) {
    console.log("Intersection:", intersection([1,2,2,1], [2,2]));
    console.log("Find Restaurant:", findRestaurant(
        ["Shogun","Tapioca Express","Burger King","KFC"],
        ["Piatti","The Grill at Torrey Pines","Hungry Hunter Steakhouse","Shogun"]
    ));
    console.log("Keyboard Row:", findWords(["Hello","Alaska","Dad","Peace"]));
    console.log("Uncommon Words:", uncommonFromSentences("this apple is sweet", "this apple is sour"));
    console.log("Remove Vowels:", removeVowels("leetcodeisacommunityforcoders"));
}

export { intersection, intersectionTwoPointers, findRestaurant, findWords, uncommonFromSentences, removeVowels };
