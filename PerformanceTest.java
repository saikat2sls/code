

import jdk.nashorn.internal.ir.LiteralNode;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

public class PerformanceTest
{
    private static List<Integer> list = new ArrayList();
    private static long startTime;
    private static long endTime;
    static
    {
        for(int i=0; i < 10000; i++)
        {
            list.add(i);
        }
    }
    //@SuppressWarnings(&quot;unused&quot;)
    public static void main(String[] args)
    {
        int k;
        //Type 1
        startTime = Calendar.getInstance().getTimeInMillis();
        for(Integer i : list)
        {
            k = i;
        }
        endTime = Calendar.getInstance().getTimeInMillis();
        System.out.println("For each loop :: " + (endTime - startTime));

        //Type 2
        startTime = Calendar.getInstance().getTimeInMillis();
        for(int j = 0; j < list.size() ; j++)
        {

            k = list.get(j);
        }
        endTime = Calendar.getInstance().getTimeInMillis();
        System.out.println("Using collection.size() :: " + (endTime - startTime));

        //Type 3
        startTime = Calendar.getInstance().getTimeInMillis();
        for(Iterator it = list.iterator() ; it.hasNext();)
        {
            k = (int)it.next();
        }
        endTime = Calendar.getInstance().getTimeInMillis();
        System.out.println("Using (Iterator it = list.iterator() ; it.hasNext();) :: " + (endTime - startTime) );

        //Type 4
        startTime = Calendar.getInstance().getTimeInMillis();
        for(int j = list.size()-1; j>=0 ; j--)
        {
             k = list.get(j);
        }
        endTime = Calendar.getInstance().getTimeInMillis();
        System.out.println("Using [int j = list.size(); j &gt; size ; j--] :: " + (endTime - startTime) );

        //Type 5
        startTime = Calendar.getInstance().getTimeInMillis();
        list.parallelStream().forEach(e -> {if(true){}});
        endTime = Calendar.getInstance().getTimeInMillis();
        System.out.println("Using java 8 streaming :: " + (endTime - startTime) );
    }
}
