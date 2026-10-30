#include <vector>
#include <string>
#include <queue>
#include <tuple>

using namespace std;

class Solution {
public:
    int shortestPath(int n, vector<vector<int>>& edges, string labels, int k) {
        // Adjacency list: u -> list of pairs (v, w)
        vector<vector<pair<int, int>>> adj(n);
        for (const auto& edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];
            adj[u].push_back({v, w});
        }

        // Min-priority queue storing {weight, u, count}
        priority_queue<tuple<long long, int, int>, 
                       vector<tuple<long long, int, int>>, 
                       greater<tuple<long long, int, int>>> pq;

        // dist[u][count] stores the minimum weight to reach node u with a given consecutive count
        // Since count ranges from 1 to k, we size the inner vector to k + 1
        vector<vector<long long>> dist(n, vector<long long>(k + 1, -1));

        // Initial state at node 0
        // The first node contributes a count of 1 for its own label.
        pq.push({0, 0, 1});
        dist[0][1] = 0;

        while (!pq.empty()) {
            auto [d, u, count] = pq.top();
            pq.pop();

            if (u == n - 1) {
                return d;
            }

            if (d > dist[u][count]) {
                continue;
            }

            for (auto& neighbor : adj[u]) {
                int v = neighbor.first;
                int w = neighbor.second;

                int next_count = (labels[u] == labels[v]) ? (count + 1) : 1;

                if (next_count > k) {
                    continue;
                }

                if (dist[v][next_count] == -1 || d + w < dist[v][next_count]) {
                    dist[v][next_count] = d + w;
                    pq.push({d + w, v, next_count});
                }
            }
        }

        // Find the minimum distance to reach node n - 1 across all valid counts
        long long min_weight = -1;
        for (int c = 1; c <= k; ++c) {
            if (dist[n - 1][c] != -1) {
                if (min_weight == -1 || dist[n - 1][c] < min_weight) {
                    min_weight = dist[n - 1][c];
                }
            }
        }

        return min_weight;
    }
};