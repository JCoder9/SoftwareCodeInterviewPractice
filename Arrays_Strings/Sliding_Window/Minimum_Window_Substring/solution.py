"""
Minimum Window Substring - Hard Classic Sliding Window

Problem: Find the minimum substring of s that contains all characters of t
         (including their frequencies).

Pattern: Expand window until it "covers" t, then shrink to minimize while maintaining coverage.
         Track "formed" count to efficiently check if window is valid.

Time Complexity: O(|s| + |t|) - each character in s visited at most twice
Space Complexity: O(|s| + |t|) - space for both frequency maps
"""

from collections import Counter, defaultdict

# ─────────────────────────────────────────────────────────────────────────
# NAIVE APPROACH (Brute Force) - O(n² × m) time | O(m) space
# ─────────────────────────────────────────────────────────────────────────
# INTERVIEW SCRIPT:
#   1. Describe:   "Brute force checks every substring and validates if it contains
#                   all chars from t — O(n²) substrings × O(m) to validate each"
#   2. Problem:    "For large inputs (|s|=10⁴, |t|=100), this means ~10¹⁰ operations"
#   3. Transition: "With a sliding window we expand until valid, then shrink to
#                   minimize. Each char visited at most twice — drops to O(n + m)"
#
# def min_window_naive(s: str, t: str) -> str:
#     if len(t) > len(s):
#         return ""
#     
#     need = Counter(t)
#     best = ""
#     best_len = float('inf')
#     
#     for i in range(len(s)):
#         for j in range(i, len(s)):
#             window = Counter(s[i:j+1])
#             valid = all(window[c] >= need[c] for c in need)
#             if valid and j - i + 1 < best_len:
#                 best_len = j - i + 1
#                 best = s[i:j+1]
#     return best
# ─────────────────────────────────────────────────────────────────────────

def min_window(s: str, t: str) -> str:
    """
    Find minimum window substring of s containing all characters of t.
    
    Args:
        s: String to search in
        t: Pattern string - must find all these characters
        
    Returns:
        Minimum window substring, or empty string if not found
    """
    if not t or not s or len(t) > len(s):
        return ""  # No window possible

    # Build frequency map of what we need
    need = Counter(t)
    required = len(need)  # Number of unique chars we need

    # Current window frequency map
    window = defaultdict(int)
    formed = 0  # How many unique chars have reached required count

    best_len = float("inf")
    best = (0, 0)  # (left, right) indices of best window

    left = 0
    for right, ch in enumerate(s):
        # Expand window: add character from right
        window[ch] += 1
        
        # Check if this character now has required frequency
        if ch in need and window[ch] == need[ch]:
            formed += 1

        # Try to shrink window while it's valid
        while formed == required:
            # Update best if current window is smaller
            if right - left + 1 < best_len:
                best_len = right - left + 1
                best = (left, right)

            # Try to shrink from left
            lc = s[left]
            window[lc] -= 1
            if lc in need and window[lc] < need[lc]:
                formed -= 1
            left += 1

    return "" if best_len == float("inf") else s[best[0]:best[1] + 1]


# Test cases
if __name__ == "__main__":
    test_cases = [
        ("ADOBECODEBANC", "ABC", "BANC"),
        ("a", "a", "a"),
        ("a", "aa", ""),
        ("ab", "b", "b"),
        ("abc", "cba", "abc"),
    ]
    
    for s, t, expected in test_cases:
        result = min_window(s, t)
        status = "✓" if result == expected else "✗"
        print(f"{status} min_window('{s}', '{t}') = '{result}' (expected '{expected}')")
