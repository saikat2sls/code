import java.io.BufferedReader;
import java.io.InputStreamReader;

//import for Scanner and other utility classes
//https://www.hackerearth.com/challenges/competitive/april-circuits-20/algorithm/lets-shift-2-36d90caa/
import java.util.*;


// Warning: Printing unwanted or ill-formatted data to output will cause the test cases to fail

class TestClass {
    public static void main(String args[] ) throws Exception {
        /* Sample code to perform I/O:
         * Use either of these methods for input */

        //BufferedReader
        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //String name = br.readLine();                // Reading input from STDIN
        //System.out.println("Hi, " + name + ".");    // Writing output to STDOUT

        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        int count = 0;
        while (count<t) {
            int number = in.nextInt();
            int places = in.nextInt();
            char side = in.next().charAt(0);

            int modifiedNumber = processShift(number,side, places);
            System.out.println(modifiedNumber);
            count++;
        }


        // Write your code here

    }

    private static int processShift(int number, char s, int places)
    {
        boolean leftShift = false;
        boolean rightShift = false;
        int filter =0;
        int filtered = 0;
        int modifiednumber = 0;

        if(s == 'L')
        {
            leftShift = true;
            filter = (int) Math.pow(2,places) -1 ;
            filter = filter<<(16-places);
        }
        else if(s == 'R')
        {
            rightShift = true;
            filter = (int) Math.pow(2,places) -1 ;
        }
        //System.out.println("filter:"+filter);

        filtered = number & filter;
        //System.out.println("filtered:"+filtered);

        if(leftShift)
        {
            filtered = filtered>>(16-places);
            number  =  number<<places;
            number = number & (int)Math.pow(2,16)-1;
            modifiednumber = number+filtered;
        }

        if(rightShift)
        {
            number = number>>places;
            number = number & (int)Math.pow(2,16)-1;
            filtered = filtered<<(16-places);
            modifiednumber = number + filtered;
        }

        //System.out.println("o/p:"+number);
        //System.out.println(("new:"+modifiednumber));
        return  modifiednumber;
    }
}