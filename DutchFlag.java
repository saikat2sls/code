/**
 * given an unsorted array of integer, need to sort the array with best time complexity and space complexity
 *
 * e.g
 * N=3      (possible numbers are 0,1,2)
 * input : {0, 1, 1, 0, 1, 2, 1, 2, 0, 0, 0, 1}
 * Output: {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 2, 2}
 */


public class DutchFlag {

    static int N=4;

    public static void sortDutch(int[] input)
    {
        int len = input.length;
        int pos=0;
        for(int i=0;i<N;i++)
        {
            int left=pos;
            int right=len-1;

            while(left<right)
            {
                while(input[left]==i && left<right)
                {
                    left++;
                }

                //System.out.print(right);
                while(input[right]!=i && right>left)
                {
                    right--;
                }

                //System.out.println(left+":"+right);
                //swap left and right
                    int swap = input[left];
                    input[left] = input[right];
                    input[right] = swap;
            }
            pos=left;


            for(int j=0;j<input.length;j++)
                System.out.print(input[j]+" ");
            System.out.println();
        }
    }

    public static void main(String args[])
    {
        int input[]=new int[]{3, 0, 1, 1, 0, 1, 3, 2, 1, 2, 0, 3, 0, 0, 1};

        sortDutch(input);

        for(int i=0;i<input.length;i++)
        System.out.print(input[i]+" ");
    }
}
