import jdk.internal.util.xml.impl.Pair;

import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

//import for Scanner and other utility classes
//https://www.hackerearth.com/challenges/competitive/april-circuits-20/algorithm/lets-shift-2-36d90caa/
import java.util.*;


// Warning: Printing unwanted or ill-formatted data to output will cause the test cases to fail


class RoboGrid {
   // public static int[][] gridCount = new int[100000][100000];
    public static long BOMB =  100000007700000049L;
    public static long SMALL_BOMB =  100000007L;
    public static long BIG_BOMB =  1000000007L;
    public static int MOD = 1000000007;
    public static void main(String args[]) throws Exception {


        /* Sample code to perform I/O:
         * Use either of these methods for input */

        //BufferedReader
        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //String name = br.readLine();                // Reading input from STDIN
        //System.out.println("Hi, " + name + ".");    // Writing output to STDOUT

        ArrayList<Point> smallBombList = new ArrayList<Point>();
        ArrayList<Point> bigBomblist = new ArrayList();

        Scanner in = new Scanner(System.in);
        int m = in.nextInt();
        int n = in.nextInt();
        int k = in.nextInt();
        int w = in.nextInt();
        int[][] gridCount = new int[m][n];

        int count = 0;
        while (count < k) {
            int x = in.nextInt()-1;
            int y = in.nextInt()-1;
            long v = in.nextLong();
            if(v%BOMB==0)
            {
                gridCount[x][y]=-1;
            }
            else if(v%SMALL_BOMB==0)
            {
                Point coord = new Point(x,y);
                smallBombList.add(coord);
            }
            else if(v%BIG_BOMB==0)
            {
                Point coord = new Point(x,y);
                smallBombList.add(coord);
            }

            count++;
        }

        /*
        //Calculate the potential mines path where there is combination of small and big bombs
        Iterator sit = smallBombList.iterator();
        while(sit.hasNext())
        {
            Point smallBomb = (Point) sit.next();
            Iterator bit = bigBomblist.iterator();
            while(bit.hasNext())
            {
                Point bigBomb = (Point) bit.next();
                if(smallBomb.x >= bigBomb.x && smallBomb.y >= bigBomb.y)
                {
                    gridCount[smallBomb.x][smallBomb.y]=-1;
                }
                if(smallBomb.x <= bigBomb.x && smallBomb.y <= bigBomb.y)
                {
                    gridCount[bigBomb.x][bigBomb.y]=-1;
                }
            }
        }

         */


        // Initializing the leftmost column
        //Here, If we encounter a blocked cell, there is no way of visiting any cell
        //directly below it.(therefore the break)
        for (int i=0;i<m;i++)
        {
            if(gridCount[i][0] == 0) gridCount[i][0] = 1;
        else break;
        }

        //Similarly initialize the topmost row.
        for (int i=1; i<n ; i++)
        {
            if(gridCount[0][i] == 0) gridCount[0][i] = 1;
        else break;
        }

        //Now the recurrence part
        //The only difference is that if a cell has been marked as -1,
        //simply ignore it and continue to the next iteration.
        for(int i=1; i<m; i++)
        {
            for(int j=1; j<n ; j++)
            {
                if(gridCount[i][j] == -1) continue;

                //While adding the number of ways from the left and top cells,
                //check that they are reachable,i.e. they aren't blocked

                if(gridCount[i-1][j] > 0) gridCount[i][j] = (gridCount[i][j] + gridCount[i-1][j] + MOD)%MOD;
                if(gridCount[i][j-1] > 0) gridCount[i][j] = (gridCount[i][j] + gridCount[i][j-1] + MOD)%MOD;
            }
        }
        //printGrid(gridCount,m,n);
        System.out.print(gridCount[m-1][n-1]);
    }

    public static void printGrid(int[][] gridCount, int m, int n)
    {
        for(int i=0;i<m;i++)
        {
            for(int j=0;j<n;j++)
            {
                System.out.print(gridCount[i][j]+ " ");
            }
            System.out.println();
        }
    }
}