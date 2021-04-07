/*
 * Enter your code here. Read input from STDIN. Print your output to STDOUT.
 * Your class should be named CandidateCode.
 *
 * Input:
coronavirus
3
abcde
crnas
onarous
*
Output:
NEGATIVE
POSITIVE
NEGATIVE
 */

import java.io.*;
import java.util.*;
public class IsSubsequence {
    public static void main(String args[] ) throws Exception {

        //Write code here
        //System.out.println(isSubsequence("coronavirus","crna"));

        Scanner in = new Scanner(System.in);
        String input1 = in.nextLine();
        System.out.println(input1);
        int t = in.nextInt();
        in.nextLine();
        int count = 0;
        while (count < t) {
            String input2 = in.nextLine();
            if(isSubsequence(input1,input2))
                System.out.println("POSITIVE");
            else
                System.out.println("NEGATIVE");
            count++;
        }
    }

    public static boolean isSubsequence(String s1, String s2)
    {
        int l1= s1.length();
        int l2 = s2.length();
        int dp[][]= new int[l1+1][l2+1];
        int max=0;

        for(int i=0;i<=s1.length();i++)
        {
            for(int j=0;j<=s2.length();j++)
            {
                if(i==0 || j==0)
                {
                    dp[i][j]=0;
                    continue;
                }
                if(s1.charAt(i-1)==s2.charAt(j-1))
                {
                    dp[i][j]=dp[i-1][j-1]+1;
                }
                else
                {
                    dp[i][j]=Math.max(dp[i-1][j],dp[i][j-1]);
                }
                if(dp[i][j]>max)
                {
                    max=dp[i][j];
                }
            }
        }

        //System.out.println(max);
        if(max==l2)
            return true;
        else
            return false;
    }
}
