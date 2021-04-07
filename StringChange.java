import java.util.*;


// Warning: Printing unwanted or ill-formatted data to output will cause the test cases to fail

class StringChange {
    public static void main(String args[]) throws Exception {
        /* Sample code to perform I/O:
         * Use either of these methods for input */

        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        int count = 0;
        while (count < t) {
            int strlen = in.nextInt();
            String input = in.next();
            System.out.println(getChangeCount(strlen, input));
            count++;
        }
    }

    public static int getChangeCount(int len, String word)
    {
        int aIndex=len-1;
        int bIndex=0 ;
        int aCount=0,bCount=0;
        int netCount=0;
        while(bIndex<aIndex)
        {

            //calculate bIndexCount
            if(bCount==0) {
                while (word.charAt(bIndex) != 'B' && bIndex<len-1) {
                    bIndex++;
                }
                int temp = bIndex;
                while ((word.charAt(temp) == 'B') && temp<len-1) {
                    bCount++;
                    temp++;
                }
            }

            if(aCount==0)
            {
                while(word.charAt(aIndex) != 'A' && aIndex>0)
                {
                    aIndex--;
                }
                int temp = aIndex;
                while(word.charAt(temp) == 'A' && temp>0)
                {
                    aCount++;
                    temp--;
                }
            }

            System.out.println("aIndex:"+aIndex);
            System.out.println("bIndex:"+bIndex);
            System.out.println("aCount:"+aCount);
            System.out.println("bCount:"+bCount);
            System.out.println("--------------");

            if(aCount!=0 && bCount!=0 && aIndex>bIndex) {
                if (bCount > aCount) {
                    netCount += aCount;
                    aIndex = aIndex - aCount;
                    aCount = 0;
                } else {
                    netCount += bCount;
                    bIndex = bIndex + bCount;
                    bCount = 0;
                }
            }
        }
        return netCount;
    }
}