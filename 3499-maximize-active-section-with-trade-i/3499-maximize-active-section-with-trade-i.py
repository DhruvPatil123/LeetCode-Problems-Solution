class Solution:
    def maxActiveSectionsAfterTrade(self, s: str) -> int:
        n = len(s)
        total_ones = s.count('1')
        
        # Build list of (char, length) blocks
        blocks = []
        i = 0
        while i < n:
            j = i
            while j < n and s[j] == s[i]:
                j += 1
            blocks.append((s[i], j - i))
            i = j
            
        max_zero_sum = 0
        
        # Loop through blocks and check valid '1' blocks to flip
        for idx in range(len(blocks)):
            char, length = blocks[idx]
            if char == '1':
                # Check if this '1' block is surrounded by '0's in the augmented string t = '1' + s + '1'
                has_left_zero = (idx > 0 and blocks[idx - 1][0] == '0')
                has_right_zero = (idx < len(blocks) - 1 and blocks[idx + 1][0] == '0')
                
                if has_left_zero and has_right_zero:
                    left_zero_len = blocks[idx - 1][1]
                    right_zero_len = blocks[idx + 1][1]
                    max_zero_sum = max(max_zero_sum, left_zero_len + right_zero_len)
                    
        return total_ones + max_zero_sum