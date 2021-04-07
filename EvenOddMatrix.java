import java.util.Scanner;

/*
https://www.hackerearth.com/codearena/ring/d8788af/
 */

public class evenOddMatrix {
    public static void main(String args[]) throws Exception {
        //System.out.println(getCommon("ADDA","BDAA"));
        Scanner in = new Scanner(System.in);
        //String input = in.nextLine();
        int N = in.nextInt();
        for (int i = 0; i < N; i++) {
            int r = in.nextInt();
            int c = in.nextInt();

            int sumEven = 0;
            int sumOdd = 0;
            int even = r / 2;
            int odd = r - even;
            sumEven += even;
            sumOdd += odd;

            if(c%2==0)  //col is even
            {
                int patSize = c/2;
                int left = even-patSize;
                if(left<=0)
                    left=0;
                sumEven = ((even*(even+1))/2 - (left*(left+1))/2)*2;

                left = odd-patSize-1;
                if(left<=0)
                    left=0;
                sumOdd += ((odd*(odd-1))/2 - (left*(left+1))/2)*2 + left;
            }
            else
            {
                int patSize = c/2;
                int left = even-patSize;
                if(left<=0)
                    left=0;
                sumEven = ((even*(even+1))/2 - (left*(left+1))/2)*2 +left;

                left = odd-patSize-1;
                if(left<=0)
                    left=0;
                sumOdd += ((odd*(odd-1))/2 - (left*(left+1))/2)*2;

            }

            /*
            for (int j = 2; j <= c && j<=r; j++) {
                if (j % 2 == 0) {
                    odd--;
                } else {
                    even--;
                }
                sumEven += even;
                sumOdd += odd;

            }

             */
            System.out.println(sumEven + " " + sumOdd);
        }
    }

}
