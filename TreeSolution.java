/*
 * Click `Run` to execute the snippet below!
 */

import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *

           1
        2     3
      4   5  6 7

 * If you need more classes, simply define them inline.
 */

class TreeSolution {

    static int maxDia=0;
    public static void main(String[] args) {
        ArrayList<String> strings = new ArrayList<String>();
        strings.add("Hello, World!");
        strings.add("Welcome to CoderPad.");

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        System.out.println("Average by level");
        printLevelAverage(root);
        findDiameter(root);
        System.out.println("Maximum Diameter: "+maxDia);

    }

    public static class TreeNode
    {
        int value;
        TreeNode left;
        TreeNode right;

        TreeNode(int v)
        {
            this.value= v;
        }
    }

    public static void printLevelAverage(TreeNode start)
    {
        LinkedList<TreeNode> q= new LinkedList<>();

        q.add(start);
        int level=0;
        int count =0;
        double sum=0;

        while(!q.isEmpty())
        {
            count = q.size();

            //calculate average
            for(int i=0;i<count;i++)
            {
                TreeNode temp = q.poll();

                sum+=temp.value;

                if(temp.left != null)
                {
                    q.add(temp.left);
                }
                if(temp.right != null)
                {
                    q.add(temp.right);
                }
            }

            double average = sum/count;
            System.out.println(average);
            sum =0;
            count=0;
        }
    }


    public static int findDiameter(TreeNode node)
    {
        //at each node check max(node.diameter, node.maxlength)
        //diameter = left.length + 1(node) +right.length

        int leftDepth = 0 ;
        int rightDepth =0;

        if(node == null)
        {
            return 0;
        }

        if(node.left!=null)
        {
            leftDepth = findDiameter(node.left);
        }

        if(node.right!=null)
        {
            rightDepth = findDiameter(node.right);
        }

        int max  = Math.max(leftDepth, rightDepth);

        int diameter = 1 + leftDepth + rightDepth;
        if(diameter>maxDia)
        {
            maxDia= diameter;
        }

        //System.out.println(diameter);
        return max+1;
    }



    public static int findMaxDepth(TreeNode node)
    {
        int leftDepth=0;
        int rightDepth=0;
        if(node.left!=null)
        {
            leftDepth= findMaxDepth(node.left);
        }

        if(node.right!=null)
        {
            rightDepth = findMaxDepth(node.right);
        }

        return (1+ Math.max(leftDepth,rightDepth));

    }


}
