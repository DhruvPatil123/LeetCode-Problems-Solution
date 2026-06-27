#include <string>
#include <vector>
#include <numeric>

using namespace std;

class Solution {
public:
    string convert(string s, int numRows) {
        // Edge case: if there's only 1 row or the string fits entirely within the rows
        if (numRows == 1 || numRows >= s.length()) {
            return s;
        }

        // Create an array of strings representing each row
        vector<string> rows(min(numRows, (int)s.length()));
        int currRow = 0;
        bool goingDown = false;

        // Iterate through each character in the string
        for (char c : s) {
            rows[currRow] += c;
            
            // Turn around when we hit the top or bottom row
            if (currRow == 0 || currRow == numRows - 1) {
                goingDown = !goingDown;
            }
            
            // Move up or down
            currRow += goingDown ? 1 : -1;
        }

        // Combine all rows into a single string
        string result = "";
        for (const string& row : rows) {
            result += row;
        }

        return result;
    }
};