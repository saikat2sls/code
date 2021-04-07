import sun.reflect.generics.tree.Tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
                   1
                 /   \
               2       3
              / \       \
            4    5       9
           / \    \       \
          6   7    8      10

          Inorder : 849251637
 */

public class TreeUtil {

    static int diameter=0;
    static List<Integer> diameterPath =new ArrayList<>();

    public static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int value)
        {
            val=value;
        }
    }

    public  static DLLNode start;
    public static DLLNode prev;

    public static class DLLNode{
        int val;
        DLLNode next;
        DLLNode prev;

        public DLLNode(int value)
        {
            next=null;
            prev=null;
            val=value;
        }
    }

    private static void updateDiameter(int nodeId, List<Integer> leftPath, List<Integer> rightPath)
    {
        int size = leftPath.size()+rightPath.size()+1;
        if(size>diameter)
        {
            diameter = size;
            diameterPath.clear();
            diameterPath.addAll(leftPath);
            diameterPath.add(nodeId);
            List<Integer> rightPathReverse = new ArrayList<>();
            rightPathReverse.addAll(rightPath);
            Collections.reverse(rightPathReverse);
            diameterPath.addAll(rightPathReverse);

            //System.out.println(diameterPath);
        }
    }

    private static List<Integer> getLongestBranch(TreeNode start)
    {
        if(start==null)
            return (new ArrayList<Integer>());

        List<Integer>  leftPath = getLongestBranch(start.left);

        List<Integer> rightPath = getLongestBranch(start.right);

        List<Integer> longestPath = rightPath;
        if(leftPath.size()>rightPath.size())
        {
            longestPath = leftPath;
        }

        updateDiameter(start.val, leftPath, rightPath);
        longestPath.add(start.val);
        //System.out.println(longestPath);


        return longestPath;
    }

    private static void processDiameter(TreeNode start)
    {
        getLongestBranch(start);
        System.out.println(diameterPath);
    }

    public static TreeNode findLCA(TreeNode node, int a, int b)
    {
        if(node==null)
            return null;

        if(node.val== a || node.val==b)
            return node;

        TreeNode leftFound=findLCA(node.left,a,b);
        TreeNode rightFound=findLCA(node.right,a,b);

        if(leftFound!=null && rightFound!=null)
        {
            return node;
        }

        if(leftFound!=null)
           return leftFound;
        else
            return rightFound;
    }

    public static void DLLConversion(TreeNode node)
    {
        if(node==null)
            return ;

        if(node.left!=null)
            DLLConversion(node.left);

        DLLNode cur = new DLLNode(node.val);

        if(prev==null)
        {
            start = cur;
        }
        else
        {
            prev.next = cur;
            cur.prev=prev;
        }
        prev=cur;

        if(node.right!=null)
            DLLConversion(node.right);
    }

    public static void printDLL()
    {
        DLLNode temp = start;
        System.out.println("Double link list format of tree");
        while (temp!=null)
        {
            System.out.print(temp.val+" ");
            temp=temp.next;
        }
    }

    public static void main(String args[])
    {
        TreeNode root =new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.left.right.right = new TreeNode(8);
        root.left.left.left = new TreeNode(6);
        root.left.left.right = new TreeNode(7);
        root.right.right = new TreeNode(9);
        root.right.right.right = new TreeNode(10);

        processDiameter(root);

        System.out.println("LCA:"+findLCA(root,6,7).val);
        DLLConversion(root);
        printDLL();
    }
}
