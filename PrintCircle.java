/**
 * Given the centre of a circle and radious of the circle print the circle in a 2d matrix
 *
 */

public class PrintCircle {
    static final int N = 30;
    static int[][] screen = new int[N+1][N+1];

    private static void printCircle(int x1, int y1, int radius)
    {
        for(int i=0;i<N;i++)
        {
            for(int j=0;j<N;j++)
            {
                //check if its inside circle
                if(isInside(i,j,x1,y1,radius))
                {
                    screen[i][j]=1;
                }
                else {
                    screen[i][j] = 0;
                }
            }
        }

        for(int i=0;i<N;i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(screen[i][j]);
            }
            System.out.println();
        }
    }

    private static boolean isInside(int x1, int y1, int x2, int y2, int radius)
    {
        double distance = Math.pow((x1-x2),2) + Math.pow((y1-y2),2);
        if(distance < (radius*radius))
        {
            return true;
        }
        return false;
    }

    public static void main(String args[])
    {
        int x1,y1,r;
        x1=15;
        y1=15;
        r=10;

        printCircle(x1,y1,r);
    }

}
