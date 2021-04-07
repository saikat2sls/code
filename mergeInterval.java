import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

public class mergeInterval {

    static class Interval implements Comparable<Interval>
    {
        int start;
        int end;

        Interval(Integer a,Integer b)
        {
            start =a;
            end=b;
        }

        @Override
        public int compareTo(Interval interval) {
            return Integer.compare(start,interval.start);
        }
    }


    public static void displayMergedInterval(List<List<Integer>> inputList)
    {
        List<Interval> intervalList = new ArrayList<Interval>();

        //massaging input data
        int n= inputList.size();
        for(List<Integer> record: inputList)
        {
            Interval interval = new Interval(record.get(0),record.get(1));
            //System.out.println(interval.start+" "+interval.end);
            intervalList.add(interval);
        }

        //sort the data
        Collections.sort(intervalList);

        //merge
        List<Interval> output = new ArrayList<>();
        Interval prev = intervalList.get(0);
        for(int i=1;i<n;i++)
        {
            //if previous interval doesnot end before current interval then merge the 2 interval
            //update the merged interval end time
            Interval cur = intervalList.get(i);

            if(prev.end>=cur.start)
            {
                if(prev.end<cur.end)
                {
                    prev.end =cur.end;
                }
            }
            else
            {
                output.add(prev);
                prev=cur;
            }
        }
        output.add(prev);

        //print
        for(Interval i: output) {
            System.out.println(i.start+" "+i.end);
        }
    }

    public static void main(String args[])
    {
        int n,a,b;
        Scanner scanner = new Scanner(System.in);
        n= scanner.nextInt();
        List<List<Integer>> inputs = new ArrayList<>();

        for(int i=0;i<n;i++)
        {
            List<Integer> input = new ArrayList<>();
            a= scanner.nextInt();
            b= scanner.nextInt();
            input.add(a);
            input.add(b);
            inputs.add(input);
        }

        displayMergedInterval(inputs);
    }
}
