package Camron_Cisneros_Project1;
import java.io.FileWriter;
import java.io.IOException;

import java.math.BigInteger;
import java.util.Random;

/**
 Camron Cisneros
 PID: 6187231
 Section: U01
 Description:
 * This program includes two methods to test the primality
 * of integers: one deterministic and one randomized.

 * The program runs these methods on integers ranging from
 * 10000000000000819 to 170141183460469231731687303715884105727,
 * and for each integer tests both methods, outputting
 * the results to a CSV file.

 *  The deterministic method determines if a given integer
 * is prime by iterating through all possible factors of
 * the integer up to the square root of the integer. If any
 * factors are found, the integer is not prime.

 * The randomized method determines if a given integer is
 * probably prime by selecting random bases and verifying
 * that they meet the conditions of Fermat's Little Theorem
 * for a specified number of iterations. The more iterations,
 * the more likely it is that the integer is actually prime.

 */
public class PrimalityTest {
    private static final BigInteger FIRST = new BigInteger("10000000000000819");
    private static final BigInteger LAST = new BigInteger("170141183460469231731687303715884105727");
    private static final BigInteger ZERO = BigInteger.ZERO;
    private static final BigInteger ONE = BigInteger.ONE;
    private static final BigInteger TWO = BigInteger.TWO;

    private static final int SMALL_RAND_ITERATIONS = 5;
    private static final int LARGE_RAND_ITERATIONS = 500;
    /**
     * Main method that runs the PrimalityTest program by calling the runTest method twice for two different
     * file outputs.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // Small number of values tested in randomized method
            runTest(SMALL_RAND_ITERATIONS, "small_values.csv");
            // Larger number of values tested in randomized method
            runTest(LARGE_RAND_ITERATIONS, "large_values.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Runs the primality tests on a range of integers and outputs the results to a CSV file.
     *
     * @param randIterations the number of iterations to run the randomized primality test for each integer
     * @param filename the name of the output file
     * @throws IOException if an I/O error occurs
     */
    private static void runTest(int randIterations, String filename) throws IOException {
        FileWriter writer = new FileWriter(filename);
        writer.write("Value, Time of DA, Prime? (According to DA), Time of RA, Prime? (According to RA)\n");
        System.out.println("Starting test for " + filename);
        BigInteger n = FIRST;
        long startTime = System.currentTimeMillis();
        while (System.currentTimeMillis() - startTime < 3600000 && n.compareTo(LAST) <= 0) {
            long detStart = System.currentTimeMillis();
            boolean isPrimeDet = isPrimeDeterministic(n);
            long detTime = System.currentTimeMillis() - detStart;

            long randStart = System.currentTimeMillis();
            boolean isPrimeRand = isPrimeRandomized(n, randIterations);
            long randTime = System.currentTimeMillis() - randStart;

            writer.write(n + "," + detTime + "," + isPrimeDet + "," + randTime + "," + isPrimeRand + "\n");
            n = n.add(ONE);
            // Print progress timer
            double percentComplete = (double) (System.currentTimeMillis() - startTime) / (double) (3600000) * 100.0;
            if ((int)percentComplete % 2 == 0 && (int)percentComplete != 0 || (int)percentComplete == 1) {
                System.out.println("Test is " + percentComplete + "% complete");
            }
        }
        System.out.println("Test Ended for " + filename);
        writer.close();
    }

    /**
     * Determines if a given integer is prime using a deterministic algorithm.
     *
     * @param n the given integer; n must be >= 1
     * @return true if n is prime, false otherwise
     */
    public static boolean isPrimeDeterministic(BigInteger n) {
        BigInteger s = n.sqrt();
        if (n.compareTo(ONE) == 0)
            return false;

        BigInteger i;
        for (i = TWO; i.compareTo(s) <= 0; i = i.add(ONE)) {
            if (n.mod(i).compareTo(ZERO) == 0)
                return false;
        }
        return true;
    }

    /**
     * Determines if a given integer is prime using a randomized algorithm based on the corollary of Fermat's little theorem.
     *
     * @param n          the given integer; n must be >= 1
     * @param iterations the number of iterations to run the test
     * @return true if n is probably prime, false otherwise
     */
    public static boolean isPrimeRandomized(BigInteger n, int iterations) {
        if (n.compareTo(TWO) < 0) {
            return false;
        }
        if (n.compareTo(TWO) == 0) {
            return true;
        }
        if (n.mod(TWO).equals(ZERO)) {
            return false;
        }
        int i = 0;
        while (i < iterations) {
            BigInteger a = randomBase(n);
            if (!checkBase(n, a)) {
                return false;
            }
            i++;
        }
        return true;
    }

    /**
     * Generates a random base between 2 and n-2 inclusive.
     *
     * @param n the given integer; n must be >= 5
     * @return a random base between 2 and n-2 inclusive
     */
    private static BigInteger randomBase(BigInteger n) {
        Random rand = new Random();
        BigInteger a;
        do {
            a = new BigInteger(n.bitLength(), rand);
        } while (a.compareTo(TWO) < 0 || a.compareTo(n.subtract(TWO)) > 0);
        return a;
    }

    /**
     * Checks if the given base is a witness for the compositeness of the given number.
     *
     * @param n the given integer; n must be >= 3
     * @param a the given base; 2 <= a <= n-2
     * @return true if a is a witness for the compositeness of n, false otherwise
     */
    private static boolean checkBase(BigInteger n, BigInteger a) {
        BigInteger nm1 = n.subtract(ONE);
        BigInteger y = a.modPow(nm1, n);
        if (!y.equals(ONE)) {
            int j = 0;
            while (!y.equals(nm1) && j <= nm1.bitLength()) {
                y = y.modPow(TWO, n);
                j++;
            }
            if (!y.equals(nm1)) {
                return false;
            }
        }
        return true;
    }
}
/*
Camron Cisneros
PID: 6187231
Section: U01
 */