from typing import List

class Solution:
    def maxActiveSectionsAfterTrade(self, s: str, queries: List[List[int]]) -> List[int]:
        n = len(s)
        
        # 1. Base number of '1's in the ENTIRE string
        total_ones = s.count('1')
            
        # 2. Compress s into contiguous segments: (char_type, start_idx, end_idx, length)
        segments = []
        i = 0
        while i < n:
            j = i
            while j < n and s[j] == s[i]:
                j += 1
            segments.append((s[i], i, j - 1, j - i))
            i = j
            
        m = len(segments)
        
        # Map each string index to its segment index
        pos_to_seg = [0] * n
        for seg_idx, (_, start, end, _) in enumerate(segments):
            for k in range(start, end + 1):
                pos_to_seg[k] = seg_idx

        # 3. Precompute the trade gain for fully internal '1' segments
        # Flipping a '1' segment effectively merges its adjacent '0' segments.
        val = [0] * m
        for i in range(1, m - 1):
            if segments[i][0] == '1':
                val[i] = segments[i - 1][3] + segments[i + 1][3]

        # 4. Build Sparse Table for O(1) Range Maximum Query on `val`
        K = max(1, m.bit_length())
        st = [[0] * K for _ in range(m)]
        for i in range(m):
            st[i][0] = val[i]
            
        for j in range(1, K):
            offset = 1 << (j - 1)
            for i in range(m - (1 << j) + 1):
                st[i][j] = max(st[i][j - 1], st[i + offset][j - 1])
                
        def query_st(L: int, R: int) -> int:
            if L > R or L < 0 or R >= m:
                return 0
            length = R - L + 1
            k = length.bit_length() - 1
            return max(st[L][k], st[R - (1 << k) + 1][k])

        ans = []
        for l, r in queries:
            seg_l = pos_to_seg[l]
            seg_r = pos_to_seg[r]

            # If the range is within a single segment, no valid trade can be formed inside it
            if seg_l == seg_r:
                ans.append(total_ones)
                continue

            max_gain = 0

            # Find the max gain for '1' segments where their adjacent '0' segments are fully inside [l, r]
            if seg_l + 2 <= seg_r - 2:
                max_gain = max(max_gain, query_st(seg_l + 2, seg_r - 2))

            # Find the gain manually for boundary '1' segments
            # The only possible partially clipped valid '1' segments are the ones nearest to the boundaries
            candidates = set()
            for c in (seg_l + 1, seg_r - 1):
                if seg_l < c < seg_r and segments[c][0] == '1':
                    candidates.add(c)

            for c in candidates:
                left_0 = c - 1
                right_0 = c + 1
                
                # Number of '0's from the left segment that are inside [l, r]
                left_len = segments[c][1] - max(l, segments[left_0][1])
                
                # Number of '0's from the right segment that are inside [l, r]
                right_len = min(r, segments[right_0][2]) - segments[c][2]
                
                max_gain = max(max_gain, left_len + right_len)

            # The maximum ones is always the total ones in the entire string plus the optimal gain within [l, r]
            ans.append(total_ones + max_gain)

        return ans