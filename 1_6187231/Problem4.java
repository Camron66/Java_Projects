/*
Purpose/Description: Problem 4: Identify lists containing the same element
                                more than half of the time in the list
Author’s Panther ID:  6187231
Certification:
I hereby certify that this work is my own and none of it is the work of
any other person.
   */

import java.util.*;
public class Problem4 {
    static int Leader(int[] A)
    {
        Stack<Integer> s = new Stack<Integer>();

        int i=1;
        s.push(0);// consider first element as leader

        int count=1;
        for(i=1;i<A.length;i++)
        {
            if(A[s.peek()]==A[i])
            {

                count++;
            }
            else count--;
            if(count==0)
            {
                s.pop();
                s.push(i);//push new leader
                count=1;
            }
        }

        int leader = s.peek();//checking if it is leader
        count=0;
        for(i=0;i<A.length;i++)
        {
            if(A[i]==A[leader])
                count++;
        }
        if(A.length/2 <count )
            return leader+1;//return leader index
        return -1;
    }
    public static void main(String argv[])
    {
        int[] a = {23, 23, 67, 23, 67, 23, 45};
        System.out.println("Leader = "+Leader(a));

        int[] b = {23, 24, 67, 23, 67, 23, 45};
        System.out.println("Leader = "+Leader(b));
    }
}
