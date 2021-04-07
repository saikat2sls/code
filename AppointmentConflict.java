import java.util.ArrayList;
import java.util.Collections;

/* 1. given a series of intervals with start and end time of each find if the intervals are conflicting.
2. If intervals are conflicting what is the maximum number(subset) of non-conflicting intervals that is possible of the given set
*/

public class AppointmentConflict
{

    public static void main(String args[])
    {
        int[][] input = {{6,7}, {2,4}, {8,12}};
        System.out.println(isConflicting(input));
    }

    private static boolean isConflicting(int[][] input)
    {
        int num = input.length;
        boolean isSuccess=true;

        if(num<=1)
            return isSuccess;

        ArrayList<AppointmentNode> appointmentList = new ArrayList<>();

        for(int i=0;i<num;i++)
        {
            AppointmentNode node = new AppointmentNode(input[i][0], input[i][1]) ;
            appointmentList.add(node);
        }

        //sort by start-time the entries
        Collections.sort(appointmentList);

        AppointmentNode prev=appointmentList.get(0);
        int count=1;
        while(isSuccess && count<num)
        {
            AppointmentNode cur = appointmentList.get(count++);
            if(prev.end>cur.start)
            {
                isSuccess=false;
            }
        }
        return isSuccess;
    }
}

class AppointmentNode implements Comparable<AppointmentNode>
{
    int start;
    int end;

    public AppointmentNode(int s, int e)
    {
        start =s;
        end =e;
    }

    public int compareTo(AppointmentNode to)
    {
        return this.start - to.start;
    }
}

