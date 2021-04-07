public class MatrixLongestPath
{
    static int X_MAX;
    static int Y_MAX;
    static int[][] maxValues = new int[100][100];

    public static void main(String arg[])
    {
        int input[][] = new int[][]{{0,1,2,3,4},{1,2,3,4,5},{2,3,4,5,1},{1,2,5,6,7}};
        X_MAX= input.length;
        Y_MAX= input[0].length;
        System.out.println(X_MAX+":"+Y_MAX);
        for(int i=0;i<X_MAX;i++)
        {
            for(int j=0;j<Y_MAX;j++)
            {
                maxValues[i][j]=-1;
            }
        }
        System.out.println(findMaxPath(input,0,0));
    }


    public static int findMaxPath(int[][] input, int startx, int starty)
    {
        //traverse only if untraversed yet
        if(maxValues[startx][starty]==-1) {
            int value = 1;
            int left = 0;
            int right = 0;
            if (startx + 1 < X_MAX && input[startx + 1][starty] > input[startx][starty]) {
                left = findMaxPath(input, startx + 1, starty);
            }

            if (starty + 1 < Y_MAX && input[startx][starty + 1] > input[startx][starty]) {
                right = findMaxPath(input, startx, starty + 1);
            }

            value += Math.max(left, right);
            maxValues[startx][starty] = value;
        }
        //System.out.println(startx+":"+starty+":"+maxValues[startx][starty]);
        return maxValues[startx][starty];
    }
}