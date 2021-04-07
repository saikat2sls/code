

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;
//https://www.hackerearth.com/challenges/competitive/september-circuits-20/algorithm/lights-2-c20ca270/

public class Lights {


    public static void main(String args[]) throws Exception {
        //System.out.println(getCommon("ADDA","BDAA"));
        int N;  //no of lights
        int M;  //no of days
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();   //No of cases

        for (int i = 0; i < T; i++) {
            N = in.nextInt();
            M = in.nextInt();
            List<BitSet> lights = new ArrayList<>();
            BitSet bs = new BitSet();
            lights.add(bs);
            //System.out.println(lights.get(0).toString());

            int l, r, a, b;

            for (int j = 1; j <= M; j++) {
                int commandType = in.nextInt();
                BitSet temp = lights.get(j-1);
                bs = (BitSet) temp.clone();
                lights.add(bs);
                switch (commandType) {
                    case 1:
                        l = in.nextInt();
                        r = in.nextInt();
                        processCommand1(lights,l, r,j);
                        break;
                    case 2:
                        a = in.nextInt();
                        b = in.nextInt();
                        l = in.nextInt();
                        r = in.nextInt();
                        processCommand2(lights,a, b, l, r,j);
                        break;
                    case 3:
                        processCommand3(lights,j);
                    default:
                }
                //System.out.println(lights.get(j).toString());
            }
        }
    }

    private static void processCommand1(List<BitSet> lights, int l, int r, int j)
    {
        BitSet temp = lights.get(j);
        temp.flip(l,r+1);
        lights.set(j, temp);
    }

    private static  void processCommand2(List<BitSet> lights, int a, int b, int l, int r, int j)
    {
        SortedSet<Integer> lightsValid = new TreeSet<>();
        for(int lightPos = l; lightPos<=r; lightPos++)
        {
            int lightOnCountDays=0;
            for(int day=a;day<=b;day++)
            {
                if(lights.get(day).get(lightPos))
                {
                    lightOnCountDays++;
                }
            }
            if(lightOnCountDays%2==1) //odd number of days light was on
            {
                lightsValid.add(lightPos);  //Add the position of the light which was on for odd number of days
            }
        }
        if(lightsValid.size()!=0) {
            System.out.print(lightsValid.size() + " ");
            System.out.println(lightsValid.first());
        }
        else {
            System.out.println("0 0");
        }

    }

    private static void processCommand3(List<BitSet> lights, int j)
    {
        int max=0;
        int maxDay=0;
        for(int day=1;day<=j;day++)
        {
            BitSet bs = lights.get(day);
            int count = bs.cardinality();
            if(count>max)
            {
                max = count;
                maxDay = day;
            }
        }
        System.out.println(maxDay);
    }

}
