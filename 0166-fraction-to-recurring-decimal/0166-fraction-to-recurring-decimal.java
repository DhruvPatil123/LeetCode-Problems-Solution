import java.util.HashMap;
import java.util.Map;

class Solution {
    public String fractionToDecimal(int numerator, int denominator) {
        // Base case: if numerator is 0, the fraction is just "0"
        if (numerator == 0) {
            return "0";
        }

        StringBuilder res = new StringBuilder();
        
        // Determine the sign of the result
        if ((numerator < 0) ^ (denominator < 0)) {
            res.append("-");
        }

        // Convert to absolute long values to prevent integer overflow
        long num = Math.abs((long) numerator);
        long den = Math.abs((long) denominator);

        // 1. Integral part (before the decimal point)
        res.append(num / den);
        long remainder = num % den;

        // If it divides perfectly, return the integer string
        if (remainder == 0) {
            return res.toString();
        }

        // 2. Fractional part (after the decimal point)
        res.append(".");
        
        // Map stores: Key = Remainder -> Value = Index in StringBuilder where this remainder appeared
        Map<Long, Integer> map = new HashMap<>();

        while (remainder != 0) {
            // If the current remainder has already been seen, we've found the cycle
            if (map.containsKey(remainder)) {
                int index = map.get(remainder);
                res.insert(index, "(");
                res.append(")");
                break;
            }

            // Record the current remainder and its position in the string builder
            map.put(remainder, res.length());

            // Simulate long division: multiply the remainder by 10
            remainder *= 10;
            res.append(remainder / den);
            remainder %= den;
        }

        return res.toString();
    }
}