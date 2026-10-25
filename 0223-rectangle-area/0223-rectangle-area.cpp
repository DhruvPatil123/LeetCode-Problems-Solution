class Solution {
public:
    int computeArea(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {
        // Calculate areas of both rectangles
        long long area1 = (long long)(ax2 - ax1) * (ay2 - ay1);
        long long area2 = (long long)(bx2 - bx1) * (by2 - by1);
        
        // Calculate overlap width and height
        long long xOverlap = max(0LL, min((long long)ax2, (long long)bx2) - max((long long)ax1, (long long)bx1));
        long long yOverlap = max(0LL, min((long long)ay2, (long long)by2) - max((long long)ay1, (long long)by1));
        
        long long overlapArea = xOverlap * yOverlap;
        
        // Total area = Area 1 + Area 2 - Overlap Area
        return area1 + area2 - overlapArea;
    }
};