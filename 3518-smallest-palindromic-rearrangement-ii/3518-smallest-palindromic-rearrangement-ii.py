from collections import Counter

class Solution:
    def smallestPalindrome(self, s: str, k: int) -> str:
        n = len(s)
        freq = Counter(s)
        
        half_freq = {}
        mid_char = ""
        
        for char, count in freq.items():
            if count % 2 == 1:
                mid_char = char
            if count // 2 > 0:
                half_freq[char] = count // 2
                
        half_length = n // 2
        CAP = k + 1

        # Calculate nCr capped at CAP (k + 1)
        def nCr_capped(n_val, r_val):
            if r_val < 0 or r_val > n_val:
                return 0
            if r_val == 0 or r_val == n_val:
                return 1
            if r_val > n_val // 2:
                r_val = n_val - r_val
            
            res = 1
            for i in range(1, r_val + 1):
                res = res * (n_val - i + 1) // i
                if res >= CAP:
                    return CAP
            return res

        # Calculate total unique permutations capped at CAP
        def get_permutations_capped(length, counts):
            if length == 0:
                return 1
            ans = 1
            rem_len = length
            for count in counts:
                if count <= 0:
                    continue
                ans *= nCr_capped(rem_len, count)
                if ans >= CAP:
                    return CAP
                rem_len -= count
            return ans

        # Check if k exceeds the total possible unique palindromic permutations
        total_perms = get_permutations_capped(half_length, half_freq.values())
        if k > total_perms:
            return ""
        
        first_half = []
        
        # Build the first half greedily
        for i in range(half_length):
            rem_length = half_length - 1 - i
            
            for char in "abcdefghijklmnopqrstuvwxyz":
                if half_freq.get(char, 0) > 0:
                    
                    # Temporarily place character
                    half_freq[char] -= 1
                    
                    # Count permutations with remaining available characters
                    perms = get_permutations_capped(rem_length, half_freq.values())
                    
                    if k <= perms:
                        # The k-th permutation is within this branch
                        first_half.append(char)
                        break
                    else:
                        # Skip this branch and adjust k
                        k -= perms
                        half_freq[char] += 1
        
        first_half_str = "".join(first_half)
        return first_half_str + mid_char + first_half_str[::-1]