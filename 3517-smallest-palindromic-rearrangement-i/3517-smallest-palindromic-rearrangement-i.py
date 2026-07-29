from collections import Counter

class Solution:
    def smallestPalindrome(self, s: str) -> str:
        freq = Counter(s)
        
        first_half = []
        mid_char = ""
        
        # Iterate from 'a' to 'z' to ensure the smallest lexicographical order
        for char in "abcdefghijklmnopqrstuvwxyz":
            if char in freq:
                count = freq[char]
                if count % 2 == 1:
                    mid_char = char
                first_half.append(char * (count // 2))
                
        first_half_str = "".join(first_half)
        return first_half_str + mid_char + first_half_str[::-1]