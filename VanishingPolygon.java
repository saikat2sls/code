import java.io.BufferedReader;
import java.io.InputStreamReader;

//import for Scanner and other utility classes
//https://www.hackerearth.com/challenges/competitive/april-circuits-20/algorithm/lets-shift-2-36d90caa/
import java.util.*;


// Warning: Printing unwanted or ill-formatted data to output will cause the test cases to fail

class VanishingPloygon {
    public static void main(String args[]) throws Exception {
        /* Sample code to perform I/O:
         * Use either of these methods for input */

        //BufferedReader
        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //String name = br.readLine();                // Reading input from STDIN
        //System.out.println("Hi, " + name + ".");    // Writing output to STDOUT

        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        int count = 0;
        long output,p;
        while (count < t) {
            count++;
            long number = in.nextLong();
            long k = in.nextLong();
            long closestPower2 = highestPowerof2(number);
            //System.out.println(closestPower2);

            if(number%2==0)
            {
                p = computeforEven(number);
            }
            else
            {
               p = computeForOdd(number);
            }
            //System.out.println(p);
            output = (p+k*2)%(number);
            if(output==0)
                output=number;
            System.out.println(output);

        }
    }

    static long computeforEven(long number)
    {
        long closestPower2 = highestPowerof2(number);
        long firstBurst = 3 + 2*(number-(closestPower2+1));
        return firstBurst;
    }

    static long computeForOdd(long number)
    {
        long closestPower2 = highestPowerof2(number);
        long firstBurst = 1 + 2*(number-(closestPower2));
        return firstBurst;
    }

    static long highestPowerof2(long n)
    {
        return Long.highestOneBit(n-1);
        /*
        long res = 0;
        for (long i = n; i >= 1; i--)
        {
            // If i is a power of 2
            if ((i & (i - 1)) == 0)
            {
                res = i;
                break;
            }
        }
        return res;

         */
    }
}