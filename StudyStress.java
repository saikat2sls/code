
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NavigableSet;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class StudyStress {
    public static void main(String args[]) throws Exception {
        //System.out.println(getCommon("ADDA","BDAA"));
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();

        int max=0;
        int min=1000;
        for (int i = 0; i < T; i++) {
            int N= in.nextInt();
            Set<Integer> input = new TreeSet<>(new Comparator<Integer>()
            {
                public int compare(Integer i1,Integer i2)
                {
                    return i2.compareTo(i1);
                }
            });
            Map<Integer,Integer> frequency = new HashMap<>();
            for(int j=0;j<N;j++) {
                int y = in.nextInt();
                input.add(y);
                if(frequency.get(y)==null) {
                    frequency.put(y, 1);
                }
                else
                {
                    int temp = frequency.get(y);
                    frequency.put(y,temp+1);
                }
            }


            for(int num:input)
            {
                for(int k=0;k<frequency.get(num);k++) {
                    System.out.print(num + " ");
                }
            }
            System.out.println();
        }

    }
}
