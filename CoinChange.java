/**
 * Given a set of denominations D {d1,d2,d3..}
 * and a target T
 * Find number of ways to reach T using different denominations from D
 * 
 * P.S denominations has infinite supply
 */


public class CoinChange {

    public static int findWaysRecursive(int sum, int[]denominations, int n)
    {
        if(sum<0)
        {
            //System.out.println();
            return 0;
        }

        if(sum==0) {
            //System.out.println("!!FOUND!!");
            return 1;
        }

        int count=0;

        for(int i=n;i>0;i--)
        {
            //System.out.print(denominations[i-1]);
            count+=findWaysRecursive(sum-denominations[i-1],denominations,i);
        }

        return count;
    }

    public static int findWays(int sum, int[] denominations)
    {
        int[] dp=new int[sum+1];
        dp[0]=1;

       for(int i=0;i<denominations.length;i++)
       {
           for(int j=denominations[i];j<=sum;j++)
           {
               dp[j]+=dp[j-denominations[i]];
           }
       }
        return dp[sum];
    }

    public static void main(String args[])
    {
        int[] denominations = {1,3,5};
        int sum=18;

        System.out.println(findWays(sum, denominations));
        System.out.println(findWaysRecursive(sum, denominations, denominations.length));
    }
}
