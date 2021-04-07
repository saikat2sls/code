import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/*
https://www.hackerearth.com/codearena/ring/7ffd4c5/
 */

public class Query1 {
    public static void main(String args[]) throws Exception {
        //System.out.println(getCommon("ADDA","BDAA"));
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int Q = in.nextInt();

        long[] nums = new long[N+1];
        long[][] evenMax = new long[N+1][N+1];
        long[][] oddMax = new long[N+1][N+1];

        //HashMap<Integer, Long> numbers = new HashMap<>();
        for (int i = 1; i <= N; i++) {
            Long n = in.nextLong();
            //numbers.put(i, n);
            nums[i]=n;
        }

        updateMatrix(N,1,nums,evenMax, oddMax);
        showMatrix(N, evenMax, oddMax);
        Set<Integer> changeSet = new HashSet<>();

        for (int i = 0; i < Q; i++) {
            int commandType = in.nextInt();
            long a = in.nextLong();
            long b = in.nextLong();
            long max = -1;
            switch (commandType) {
                case 1:
                    //numbers.put(a, b);
                    nums[(int)a]=b;
                    updateMatrix(N,(int)a,nums,evenMax, oddMax);
                    changeSet.add((int)a);
                    break;
                case 2:

                    max = evenMax[(int)a][(int)b];
                    if (max == -1) {
                        System.out.println("DNE");
                    } else {
                        System.out.println(max);
                    }
                    break;
                case 3:

                    max=oddMax[(int)a][(int)b];
                    if (max == -1) {
                        System.out.println("DNE");
                    } else {
                        System.out.println(max);
                    }
                    break;

            }
        }
    }

    private static void  updateMatrix(int N, int from, long[] nums, long[][] evenMax, long[][] oddMax)
    {
        //even max
        for(int i=from;i<=N;i++)
        {
            long emax =  nums[i];
            if(emax%2!=0)
                emax=-1;
            evenMax[i][i]=emax;
            for(int j=i+1;j<=N;j++)
            {
                if(nums[j]%2==0 && nums[j]>emax)
                {
                    emax = nums[j];
                }
                evenMax[i][j]=emax;
            }
        }

        //odd max
        for(int i=1;i<=N;i++)
        {
            long omax =  nums[i];
            if(omax%2==0)
                omax=-1;
            oddMax[i][i]=omax;
            for(int j=i+1;j<=N;j++)
            {
                if(nums[j]%2!=0 && nums[j]>omax)
                {
                    omax = nums[j];
                }
                oddMax[i][j]=omax;
            }
        }
    }

    private static void  showMatrix(int N,long[][] evenMax, long[][] oddMax) {
        //even max
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                System.out.print(evenMax[i][j] + " ");
            }
            System.out.println();
        }

        //even max
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                System.out.print(oddMax[i][j] + " ");
            }
            System.out.println();
        }
    }
}
