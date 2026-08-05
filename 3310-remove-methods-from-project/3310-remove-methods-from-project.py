class Solution:
    def remainingMethods(self, n: int, k: int, invocations: list[list[int]]) -> list[int]:
        # Build adjacency list
        adj = [[] for _ in range(n)]
        for u, v in invocations:
            adj[u].append(v)
            
        # 1. Find all suspicious methods using BFS/DFS
        suspicious = set()
        stack = [k]
        suspicious.add(k)
        
        while stack:
            u = stack.pop()
            for v in adj[u]:
                if v not in suspicious:
                    suspicious.add(v)
                    stack.append(v)
                    
        # 2. Check if any non-suspicious method invokes a suspicious method
        for u, v in invocations:
            if u not in suspicious and v in suspicious:
                # Cannot remove suspicious methods
                return list(range(n))
                
        # 3. Return remaining non-suspicious methods
        return [i for i in range(n) if i not in suspicious]