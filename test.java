import java.util.Scanner;

public class test {

    static boolean isHappynumber(int n) {
        if (n == 1 || n == 7)
            return true;
        int sum = n, x = n;
        //System.out.println(n);
        // this loop executes till the sum of square of
        // digits obtained is not a single digit number
        while(sum > 9) {
            sum = 0;
            System.out.println(x);
            // this loop finds the sum of square of digits
            while (x > 0) {
                int d = x%10;
                sum += d*d;
                x/=10;
            }
            if (sum == 1)
                return true;
            x = sum;

        }
        if(sum == 7)
            return true;
        return false;
    }


    public static void main(String args[]) throws Exception {
        //System.out.println(getCommon("ADDA","BDAA"));
        /*
        Scanner in = new Scanner(System.in);
        int N1 = in.nextInt();
        int N2 = in.nextInt();
        System.out.println(lcm(N1,N2));
         */
        System.out.println(isHappynumber(13));
    }
    static int gcd(int a, int b)
    {
        if (a == 0)
            return b;
        return gcd(b % a, a);
    }

    // method to return LCM of two numbers
    static int lcm(int a, int b)
    {
        return (a / gcd(a, b)) * b;
    }
}
