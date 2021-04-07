import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class parenthisisCombo {
    public static void main(String args[]) throws Exception {

        int N;  //no
        Scanner in = new Scanner(System.in);
        N = in.nextInt();   //No of brackets
        findAllCombinations(N);
    }

    private static void findAllCombinations(int N)
    {
        int max=0,min=0;
        List<Integer> solutions = new ArrayList<Integer>();

        //generate max
        for(int i=0;i<N;i++)
        {
            max=(max<<1)+1;
        }
        for(int i=0;i<N;i++)
        {
            max = max<<1;
        }

        //generate min
        for(int i=0;i<N;i++)
        {
            min=(min<<1)+1;
            min=min<<1;
        }

        //System.out.println("max:"+max+"min:"+min);

        for(int i=max;i>=min;i--)
        {
            if(isValidCombo(i, 2*N))
            {
                //System.out.println("Valid");
                solutions.add(i);
            }
        }

        for(Integer n: solutions)
        {
            //System.out.println(n.toBinaryString(n));
            //convert binary format to string
            System.out.println(toString(n));
        }

    }

    private static String toString(int N)
    {
        int copy=N;
        String strFormat="";
        while(copy!=0) {
            int bit = copy & 1;
            if (bit == 1)
                strFormat = "(" + strFormat;
            else
                strFormat = ")" + strFormat;
            copy = copy >> 1;
        }
        return strFormat;
    }

    private static boolean isValidCombo(int number, int bitCount)
    {
        int count=0;
        int temp;
        int input = bitCount/2;
        int ones=0,open = 0;
        //System.out.println("number:"+number);
        while(bitCount>0)
        {
            bitCount--;
            int mask = 1<<bitCount;
            int bit = mask & number;
            bit = bit>>bitCount;

            //System.out.println("bit"+bit);

            if(bit==1)
            {
                open++;
                ones++;
            }
            else
            {
                open--;
            }
            if(open<0)
                return false;
        }

        if(ones==input)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}