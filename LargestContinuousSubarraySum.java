/**
 * input: array of numbers
 * output: subarray with the lasrget sum
 *
 *
 * Modification of Kandane algorithm
 */

public class LargestContinuosSubarraySum {

    public static void main(String args[])
    {
        //input
        int input[]=new int[]{2,3,6,-3,-1,9,2,-80,60};
        System.out.println(largestSubArraySum(input));
    }

    private static int largestSubArraySum(int[] input)
    {
        int start=0;int maxSum=-1111;
        int maxStart=0;int maxEnd=0;

        int sum=0;
        int i=0;

        for(i=0;i<input.length;i++)
        {
            sum+=input[i];

            if(sum>maxSum)
            {
                maxStart=start;
                maxEnd=i;
                maxSum=sum;
            }
            else
            {
                if(sum<0)
                {
                    sum=0;
                    start=i;
                }
            }
        }

        System.out.println("Start:"+maxStart+"End:"+maxEnd);
        return maxSum;
    }

}
