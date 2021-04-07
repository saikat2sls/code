import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * starting from top left cornet need to reach bottom righ corner
 * use back-tracking principle
 */

public class MazeTraversal {

    public static LinkedList<Point> outputPath = new LinkedList<>();
    public static void main(String args[])
    {
        int maze[][]= new int[][]{{1,0,0,0},
                      {1,1,0,0},
                      {1,0,1,0},
                      {1,1,1,1}};


          mazeTraverse(maze, 0,0,3,3);

    }

    private static  void printOutput()
    {
        for(Point p : outputPath)
        {
            System.out.println(p.x+" "+p.y);
        }
    }

    public static void mazeTraverse(int maze[][], int startX, int startY, int endX, int endY) {


       if(startX==endX && startY==endY) {
           System.out.print("YAY!!");
           Point pt = new Point(startX,startY);
           outputPath.add(pt);
           printOutput();
           outputPath.pollLast();
           return;
           //reached a potential solution
       }
       else if(startX>endX || startY>endY || maze[startX][startY]!=1) {
           return;
       }


       Point pt = new Point(startX,startY);
       outputPath.add(pt);

        mazeTraverse(maze, startX , startY + 1, endX, endY);
        mazeTraverse(maze, startX + 1, startY + 1, endX, endY);
        mazeTraverse(maze, startX + 1, startY , endX, endY);

       outputPath.pollLast();
    }
}
