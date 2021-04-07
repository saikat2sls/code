import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/*
https://www.hackerearth.com/codearena/ring/d191280/
 */

public class laneChange {
    public static void main(String args[]) throws Exception {
        //System.out.println(getCommon("ADDA","BDAA"));
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        for(int i=0;i<T;i++)
        {
            int  N = in.nextInt();
            List<Integer> firstToll = new ArrayList<>();
            List<Integer> secondToll = new ArrayList<>();
            List<Integer> firstTollChange = new ArrayList<>();
            List<Integer> secondTollChange = new ArrayList<>();
            for(int j=0;j<N;j++)
            {
                int a = in.nextInt();
                firstToll.add(j,a);
            }

            for(int j=0;j<N;j++)
            {
                int a = in.nextInt();
                secondToll.add(j,a);
            }

            for(int j=0;j<N-1;j++)
            {
                int a = in.nextInt();
                firstTollChange.add(j,a);
            }

            for(int j=0;j<N-1;j++)
            {
                int a = in.nextInt();
                secondTollChange.add(j,a);
            }

            int laneChooser=0;   //0 for 1st lane 1 for 2nd lane
            int cost=0;
            //TODO
            for(int j=0;j<N;j++)
            {
                int a= firstToll.get(j);
                int b = secondToll.get(j);
                if(a<b)
                {
                    if(laneChooser!=0 && j!=0)
                    {
                        cost+=secondTollChange.get(j-1);

                    }
                     cost+=firstToll.get(j);
                    laneChooser=0;
                }
                else if(b<a)
                {
                    if(laneChooser!=1 && j!=0)
                    {
                        cost+=firstTollChange.get(j-1);

                    }
                    cost+=secondToll.get(j);
                    laneChooser=1;
                }
                else
                {
                    cost+=secondToll.get(j);
                }
                System.out.println("j:"+j+"::"+cost+":"+laneChooser);
            }
            System.out.println(cost);
        }
    }
}
