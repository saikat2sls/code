import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Mode {
    public static void main(String args[]) throws Exception {
        //System.out.println(getCommon("ADDA","BDAA"));
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        for (int i = 0; i < T; i++) {
            int N = in.nextInt();
            Map<Integer, Integer> countMap = new HashMap<Integer, Integer>();
            List<Integer> numbers = new ArrayList<Integer>();
            List<Integer> output = new ArrayList<>();
            for (int j = 0; j < N; j++) {
                int a= in.nextInt();
                //System.out.println(a);
                Integer count = countMap.get(a);
                if(null==count)
                {
                    count=0;
                }
                countMap.put(a,++count);
            }

            int max=0;
            for(Integer count:countMap.values())
            {
                if(count>=max)
                {
                    max=count;
                }
            }

            //System.out.println("Max"+max);

            for(int val:countMap.keySet())
            {
                int count = countMap.get(val);
                if(count==max)
                {
                    output.add(val);
                }
            }

            Collections.sort(output, Collections.reverseOrder());
            for(int val:output) {
                System.out.print(val+" ");
            }
            System.out.println();
        }
    }
}
