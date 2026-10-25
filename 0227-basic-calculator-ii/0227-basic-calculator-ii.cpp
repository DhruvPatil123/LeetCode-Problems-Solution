class Solution {
public:
    int calculate(string s) {
        int n = s.length();
        stack<int> st;
        long long currentNumber = 0;
        char sign = '+'; // Default sign for the first number
        
        for (int i = 0; i < n; ++i) {
            char c = s[i];
            
            // If the character is a digit, build the current number
            if (isdigit(c)) {
                currentNumber = currentNumber * 10 + (c - '0');
            }
            
            // If it's an operator or the last character of the string
            if ((!isdigit(c) && !isspace(c)) || i == n - 1) {
                if (sign == '+') {
                    st.push(currentNumber);
                } else if (sign == '-') {
                    st.push(-currentNumber);
                } else if (sign == '*') {
                    long long top = st.top();
                    st.pop();
                    st.push(top * currentNumber);
                } else if (sign == '/') {
                    long long top = st.top();
                    st.pop();
                    st.push(top / currentNumber); // Truncates toward zero by default in C++ for integers
                }
                
                sign = c; // Update the sign
                currentNumber = 0; // Reset for the next number
            }
        }
        
        // Sum up all elements in the stack
        int result = 0;
        while (!st.empty()) {
            result += st.top();
            st.pop();
        }
        
        return result;
    }
};