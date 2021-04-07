//import jdk.internal.util.xml.impl.Pair;

/*
https://www.hackerearth.com/codearena/ring/1a49101/
 */

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class stringCheck
{
    public static void main(String args[]) throws Exception {
        //System.out.println(getCommon("ADDA","BDAA"));
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        int N = in.nextInt();
        in.nextLine();
        int output=0;

        Map<Character, Integer> masterList = new HashMap<>();
        for(int j=0;j<input.length();j++)
        {
            char c= input.charAt(j);
            int count=1;
            if(masterList.containsKey(c))
            {
                count+=masterList.get(c)+1;
            }
            masterList.put(c,count);
        }

        for(int i=0;i<N;i++)
        {
            String w1 = in.nextLine();
            output+=canForm(w1,masterList);
        }
        System.out.println(output);
    }

    private static  int canForm(String word, Map masterList)
    {
        Map<Character,Integer> tempList = new HashMap<>(masterList);
        for(int j=0;j<word.length();j++) {
            char c = word.charAt(j);
            int count = 0;
            if (tempList.containsKey(c)) {
                count = tempList.get(c);
                if(count>0) {
                    tempList.put(c, count - 1);
                }
                else if(count==0)
                {
                    return 0;
                }

            } else {
                return 0;
            }
        }
        return 1;

    }


}
