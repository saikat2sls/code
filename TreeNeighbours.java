import java.util.LinkedList;
import java.util.List;

/**
 * given a binary tree and a node find all neighbours of the node at distance K
 * i/p: K : distance from search node
 *      N: search node value
 *
 * o/p: All nodes at distance K from N
 *
 */


public class TreeNeighbours{

public static class TreeNode
{
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int value)
    {
        val = value;
        left=null;
        right=null;
    }
}


    static TreeNode root;
    static List<TreeNode> path = new LinkedList<>();

    public static void main(String args[])
    {
        root = new TreeNode(1);
        root.left =  new TreeNode(2);
        root.right =  new TreeNode(3);
        root.left.left =  new TreeNode(4);
        root.left.right =  new TreeNode(5);
        root.right.left =  new TreeNode(6);
        root.right.right =  new TreeNode(7);
        root.left.left.left =  new TreeNode(8);
        root.left.left.right =  new TreeNode(9);

        //find neighbour of 5 at distance 2
        findNeighbour(5,3);

    }

    public static List<Integer> findNeighbour(int nodeVal, int distance)
    {
        //List<TreeNode> path = new LinkedList<>();
        findPath(root, nodeVal, path);
        //printPath
        for(TreeNode temp:path)
        {
            System.out.print(temp.val+" : ");
        }

        List<Integer> output = new LinkedList<>();
        int count=0;
        //find nodes at distance
        for(TreeNode parent: path)
        {
            if(count>distance)
                continue;
            findChildNodes(parent,distance-count,output);
            count++;
        }

        System.out.println(output);
        return output;
    }


    public static boolean findPath(TreeNode start, int value, List<TreeNode> path)
    {
        if(start==null)
            return false;

        if(start.val==value)
        {
            path.add(start);
            return true;
        }

        if(findPath(start.left, value, path)==true)
        {
            path.add(start);
            return true;
        }

        if(findPath(start.right, value, path)==true)
        {
            path.add(start);
            return true;
        }
        return false;
    }

    public static void findChildNodes(TreeNode start, int distance, List<Integer> output)
    {

        if(start==null)
            return;

        System.out.println(start.val+" value:"+distance);

        if(distance==0)
        {
            output.add(start.val);
            return;
        }

        if(!path.contains(start.left))
        findChildNodes(start.left, distance-1, output);
        if(!path.contains(start.right))
        findChildNodes(start.right, distance-1, output);
    }

}


