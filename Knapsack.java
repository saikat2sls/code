import java.util.Scanner;

/*
  Sample Input :
  2
3 <-no of bags
3 <- max capacity (weight)
1 2 3 <-weights
2 4 8 <-values
4
8
2 4 5 7
4 9 7 5


Sample output :

8
13
 */

public class Knapsack {
    public static void main(String args[]) throws Exception {
        //System.out.println(getCommon("ADDA","BDAA"));
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        for (int i = 0 ;i < T; i++) {
            int N = in.nextInt();
            int maxCapacity = in.nextInt();

            int[] values = new int[N];
            int[] weight = new int[N];

            for (int j = 0; j < N; j++) {
                weight[j] = in.nextInt();
            }

            for (int j = 0; j < N; j++) {
                values[j] = in.nextInt();
            }

            //System.out.println(knapSack(maxCapacity, weight, values, N));
            System.out.println(knapsackRecursive(maxCapacity, weight, values, N));
        }
    }

        static int max(int a, int b)
        {
            return (a > b) ? a : b;
        }

        // Returns the maximum value that can
        // be put in a knapsack of capacity W
        static int knapSack(int W, int wt[], int val[], int n)
        {
            int i, w;
            int K[][] = new int[n + 1][W + 1];

            // Build table K[][] in bottom up manner
            for (i = 0; i <= n; i++) {
                for (w = 0; w <= W; w++) {
                    if (i == 0 || w == 0)
                        K[i][w] = 0;
                    else if (wt[i - 1] <= w)
                        K[i][w] = max(
                                val[i - 1] + K[i - 1][w - wt[i - 1]],
                                K[i - 1][w]);
                    else
                        K[i][w] = K[i - 1][w];
                }
            }

            return K[n][W];
        }

    public static int knapsackRecursive(int W, int[] wt, int[] val, int n)
    {
        if(n==0)
            return 0;

        if(wt[n-1]<=W)
        {
            return Math.max(val[n-1]+knapsackRecursive(W-wt[n-1],wt,val,n-1),
                             knapsackRecursive(W,wt,val,n-1));
        }
        else
            return knapsackRecursive(W,wt,val,n-1);
    }

}
