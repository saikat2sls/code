

public class FindIsland
{
    static int R =4;
    static int C=4;
    static int input[][] = new int[][]
            {{1,0,0,1},
            {0,0,1,0},
            {1,0,0,0},
            {1, 0,1,0}};

    private static int findClusterCount()
    {
        int count =0;

        for(int i=0;i<R;i++)
        {
            for(int j=0;j<C;j++)
            {
                count+=removeCluster(i,j);
            }
        }
         return count;
    }

    private static int removeCluster(int r, int c) {
        int clusterFound = 0;

        if (r >= R || c >= C || input[r][c] == 0) {
            return clusterFound;
        }

        clusterFound = 1;
        input[r][c]=0;
        removeCluster(r + 1, c + 1);
        removeCluster(r + 1, c);
        removeCluster(r, c + 1);

        return clusterFound;

    }

    public static void main(String args[])
    {
        System.out.println(findClusterCount());
    }
}
    
