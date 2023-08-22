/*
Purpose/Description: Problem 2: Find duplicate elements
Author’s Panther ID:  6187231
Certification:
I hereby certify that this work is my own and none of it is the work of
any other person.
   */

import java.util.*;
public class Problem2 {

    static int amount(int[] A, int k){

        int tally=0,i;

        for(i = 0; i < A.length; i++)
            if(A[i] == k)
                tally++;
        return tally;
    }

    public static void main(String[] args)
    {
        int num, count = 0;
        Scanner s = new Scanner(System.in);
        int[] A = {-1,2,3,5,6,6,6,9,10};

        System.out.println("Enter the element you wish to search:");

        num = s.nextInt();
        count= amount(A,num);

        System.out.println("The Number of Occurrences of the Searched Element are: "+count);
    }
}
