class Solution:
    def minimumPushes(self, word: str) -> int:
        n = len(word)
        
        pushes = 0
        k = 1  # number of pushes per letter
        
        while n > 0:
            count = min(n, 8)
            pushes += count * k
            n -= count
            k += 1
            
        return pushes