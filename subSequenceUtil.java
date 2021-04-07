public class subSequenceUtil {
    boolean dpPrint=false;

    public int longestIncreasingSubsequence(int[] a)
    {
        int[] count =new int[a.length];
        int max=1;
        for(int i=1;i<a.length;i++)
        {
            for(int j=0;j<i;j++)
            {
                if(a[i]>a[j])
                {
                    count[i]= Math.max(count[i],count[j]+1);
                    if(count[i]>max)
                    {
                        max = count[i];
                    }
                }
            }
        }

        //System.out.println(count[a.length-1]);
        return max+1;  //should count itself
    }

    public int longestSubsequenceLength(int[] a, int[]b)
    {
        int[][] dp = new int[a.length+1][b.length+1];

        for(int i=1;i<=a.length;i++)
        {
            for(int j=1;j<=b.length;j++)
            {
                if(a[i-1]==b[j-1])
                {
                    dp[i][j]=dp[i-1][j-1]+1;
                }
                else
                {
                    dp[i][j]=Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }
        if(dpPrint)
        {
            printMatrix(dp);
        }
        return dp[a.length][b.length];
    }

    public void printMatrix(int[][] matrix)
    {
        for(int i=0;i<matrix.length;i++)
        {
            for(int j=0;j<matrix[0].length;j++)
            {
                System.out.print(matrix[i][j]+" ");
            }
            System.out.println();
        }
    }

    public int longestSubarrayLength(int[] a, int[] b)
    {
        int[][] dp = new int[a.length+1][b.length+1];
        int max=0;

        for(int i=1;i<=a.length;i++)
        {
            for(int j=1;j<=b.length;j++)
            {
                if(a[i-1]==b[j-1])
                {
                    dp[i][j]=dp[i-1][j-1]+1;
                    if(dp[i][j]>max)
                    {
                        max=dp[i][j];
                    }
                }
                else
                {
                    //TODO Nothing for now
                }
            }
        }

        if(dpPrint)
          printMatrix(dp);

        return max;
    }

    public static void main(String args[])
    {
        int[] a={1,3,7,4,6,8,9,11,15};
        int[] b ={7,9,10,11,15,20};
        int[] c={5,4,3,2,1,2,3,4,5};
        subSequenceUtil util = new subSequenceUtil();
        System.out.println(util.longestSubsequenceLength(a,b));
        System.out.println(util.longestSubarrayLength(a,b));
        System.out.println(util.longestIncreasingSubsequence(c));
    }
}
