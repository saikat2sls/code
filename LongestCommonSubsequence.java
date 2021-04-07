/*
Enter 2 words/string find the the longest common sequence
abcdert ,  bsdierot  -> bdert
 */

import java.util.Scanner;

public class LongestCommonSubsequence {
    private static int findLongestSubsequenceRecursive(String word1, String word2,int m, int n)
    {
        if (m == 0 || n == 0)
            return 0;
        if (word1.charAt(m-1) == word2.charAt(n-1))
            return 1 + findLongestSubsequenceRecursive(word1, word2, m-1, n-1);
        else
            return Math.max(findLongestSubsequenceRecursive(word1, word2, m, n-1),
                    findLongestSubsequenceRecursive(word1, word2, m-1, n));
    }

    private static int findLongestSubsequence(String word1, String word2)
    {
        int l1=word1.length();
        int l2=word2.length();

        int ls[][] = new int[l1+1][l2+1];
        int max=0;

        for(int i=0;i<=l1;i++)
        {
            for(int j=0;j<=l2;j++)
            {
                if(i==0 || j==0) {
                    ls[i][j] = 0;
                }
                else if(word1.charAt(i-1)==word2.charAt(j-1))
                {
                    ls[i][j]=ls[i-1][j-1]+1;
                }
                else {
                    ls[i][j]=Math.max(ls[i-1][j], ls[i][j-1]);
                }

                if(ls[i][j]>max)
                {
                    max=ls[i][j];
                }
            }
        }
        return max;
    }

    public static void main(String args[])
    {
        Scanner scanner = new Scanner(System.in);
        String word1 = scanner.nextLine();
        String word2= scanner.nextLine();

        int max = findLongestSubsequence(word1,word2);
        //int max = findLongestSubsequenceRecursive(word1,word2,word1.length(),word2.length());
        System.out.println(max);
    }
}
