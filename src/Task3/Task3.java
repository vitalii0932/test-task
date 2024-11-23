package Task3;

import java.math.BigInteger;

/**
 * Task 3 solve
 */
public class Task3 {
    /**
     * Main method
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        // Calculate factorial of 100
        BigInteger factorial = factorial(100);
        // print factorial of 100
        System.out.println("Factorial of 100 is: " + factorial);
        // print sum of digits of factorial of 100
        System.out.println("Sum of digits of factorial of 100 is: " + getDigitsSum(factorial));
    }

    /**
     * Calculate factorial of n
     *
     * @param n - number
     * @return - factorial of n
     */
    public static BigInteger factorial(int n) {
        BigInteger result = BigInteger.ONE; // initialize result to 1
        // multiply all numbers from 1 to n
        for (int i = 1; i <= n; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }

    /**
     * Calculate sum of digits of a number
     *
     * @param number - number
     * @return - sum of digits of number
     */
    public static int getDigitsSum(BigInteger number) {
        int sum = 0; // initialize sum to 0\
        // calculate sum of digits
        while (number.compareTo(BigInteger.ZERO) > 0) {
            sum += number.mod(BigInteger.TEN).intValue(); // get last digit and add to sum
            number = number.divide(BigInteger.TEN); // remove last digit
        }
        return sum;
    }
}
