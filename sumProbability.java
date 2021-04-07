import java.util.Scanner;
/*
https://www.hackerearth.com/codearena/ring/1e61af2/
PROBLEM STATEMENTPoints: 30
Assume there is an Ideal Random Number Generator which generates any real number between 0 and given integer. Two numbers are generated from the above generator using integer A and B, let's assume the numbers generated are X1 and X2. There is another integer C. What is the probability that summation of X1 and X2 is less than C.

Input Format
A single line containing three integers A,B,C
1 <= A,B,C <= 100000

Output Format
Print the probability in form of P/Q

Problem Setter: Practo Tech Team

SAMPLE INPUT
1 1 1
SAMPLE OUTPUT
1/2
Explanation
For example if A and B are 1. Then the random number generated will be any real number between 0 - 1.

0 < x1 < 1 0 < x2 < 1

If C is also 1, then you need to determine the probability of that x1 + x2 < C.
 */

public class sumProbability {
    public static void main(String args[]) throws Exception {
        //System.out.println(getCommon("ADDA","BDAA"));
        Scanner in = new Scanner(System.in);
        int A = in.nextInt();
        int B = in.nextInt();
        int C = in.nextInt();

        long area = 2*A*B;
        long considered = (C*C);
        long hcf = gcd(area,considered);
        area/=hcf;
        considered/=hcf;

        if(considered>area)
        {
            System.out.println("1/1");

        }
        else
            System.out.println(considered+"/"+area);

    }

    static long gcd(long a, long b)
    {
        // Everything divides 0
        if (a == 0)
            return b;
        if (b == 0)
            return a;

        // base case
        if (a == b)
            return a;

        // a is greater
        if (a > b)
            return gcd(a-b, b);
        return gcd(a, b-a);
    }
}
