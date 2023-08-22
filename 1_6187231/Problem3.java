/*
Purpose/Description: Problem 3: Use recursion for exponentiation
Author’s Panther ID:  6187231
Certification:
I hereby certify that this work is my own and none of it is the work of
any other person.
   */

import java.io.IOException;
import java.io.*;

// a)
public class Problem3 {

    public static long exponentiation(long x, int n)
    {
        if(n==0) {
            return 1;
        }else if(n==1) {
            return x;

        }else {
            return x * exponentiation(x, n - 1);
        }
    }

    public static void main(String args[]) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Problem3 func = new Problem3();

        System.out.println("Enter the value of x :"); // Taking Input
        long x = Integer.parseInt(br.readLine());

        System.out.println("Enter the value of n :"); // Taking Input
        int n = Integer.parseInt(br.readLine());

        System.out.println("\n" +x +"^" +n +" = "+func.exponentiation(x,n)); // Method Called
    }
}
/*
b)
T(x,n) = 2 * T(x,n/2) + c
       = 4 * T(x,n/4) + 2c + c
       = ...
       = [log2(n) E i=1] 2^(i-1) * c
       = c (n - 1)
       = O(n)


c) The function is multiplied by 2 a total of 62 times
Since x = 2 and n = 63,
The multiplication will go as follows:

2exp(2,62)
4exp(2,61)
8exp(2,60)
16exp(2,59)
32exp(2,58)
...
4,611,686,018,427,387,904exp(2,1)
9,223,372,036,854,775,808

 */
