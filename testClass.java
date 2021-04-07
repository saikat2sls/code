import java.util.Scanner;


public class testClass {
    public static void main(String args[]) throws Exception {
        //System.out.println(getCommon("ADDA","BDAA"));
        Scanner in = new Scanner(System.in);

        String input = in.nextLine();
        int length = input.length();
        int T = in.nextInt();
        for (int i = 0; i < T; i++) {
            long a = in.nextLong();
            long b = in.nextLong();
            long start,end;

            if(a % length==0) {
                start=length;
            }
            else
            {
                start=a % length;
            }

            if(b%length==0)
            {
                end=length;
            }
            else
            {
                end = b % length;
            }

            if(start>end)
            {
                long temp = start;
                start = end;
                end= temp;
            }

            //System.out.println("start:"+start+"end:"+end);
            if(input.charAt((int)start-1)==input.charAt((int)end-1))
            {
                System.out.println("Yes");
            }
            else
            {
                System.out.println("No");
            }



        }
    }

    static long nCr(long n, long r)
    {

        long res = 1;
        for (long i = n-r+1,j=1; i <= n; i++,j++)
        {
            res = res * i;
            if(j<=r)
            {
                res/=j;
            }
        }

        /*
        for (long i = 1; i <= r; i++)
        {
            res = res/i;
        }

         */
        return res;
    }

    // Returns factorial of n
    static long fact(long n)
    {
        System.out.println(n+":");
        long res = 1;
        for (long i = 2; i <= n; i++)
            res = res * i;
        return res;
    }

}
