/*
   Given a set of number
   find the maximum subset with increasing trend
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LongestIncreasingSubset {

    private static int findMaxIncreasingSubset(List<Integer> input)
    {
        int l = input.size();
        //longest increasing sequence
        int lis[] = new int[l];

        for(int i=0;i<l;i++)
        {
            lis[i]=1;
        }

        for(int i=1;i<l;i++)
        {
            for(int j=0;j<i;j++)
            {
                if(input.get(j)<input.get(i) && (lis[j]+1 > lis[i]))
                {
                    lis[i]=lis[j]+1;
                }
            }
        }

        //find max sequence
        int max=1;
        for(int i=0;i<l;i++)
        {
            if(lis[i]>max)
            {
                max=lis[i];
            }
        }
        return  max;
    }

    public static void main(String args[])
    {
        Scanner scanner = new Scanner(System.in);
        int N= scanner.nextInt();
        ArrayList<Integer> list = new ArrayList<>();

        for(int i=0;i<N;i++)
        {
            int in= scanner.nextInt();
            list.add(in);
        }
        Integer output = findMaxIncreasingSubset(list);
        System.out.println(output);
    }
}
