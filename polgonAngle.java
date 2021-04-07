import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/*
https://www.hackerearth.com/codearena/ring/7cdff6a/
 */

public class polgonAngle {
    public static void main(String args[]) throws Exception {
        //System.out.println(getCommon("ADDA","BDAA"));
        Scanner in = new Scanner(System.in);
        Set<Double> interiorAngle= new HashSet<>();
        for(int i=3;i<1000;i++)
        {
            double interior = (i-2)*180/(double)i;
            interiorAngle.add(interior);
        }
        int T = in.nextInt();
        for(int i=0;i<T;i++)
        {
            Double angle =in.nextDouble();
            if(interiorAngle.contains(angle))
            {
                System.out.println("YES");
            }
            else
            {
                System.out.println("NO");
            }
        }
    }
}
