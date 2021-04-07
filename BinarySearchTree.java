import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BinarySearchTree {

    static class TreeNode
    {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int v)
        {
            val=v;
            left=null;
            right=null;
        }
    }

    static TreeNode root =null;


    private static TreeNode insertNode(TreeNode parent, TreeNode node, int val)
    {
        if(node == null)
        {
            TreeNode temp = new TreeNode(val);
            if(val<parent.val)
                parent.left=temp;
            else
                parent.right=temp;

            return temp;
        }

        if(node.val== val)
            return node;
        else if(node.val<val)
        {
            return (insertNode(node, node.right, val));
        }
        else
        {
            return (insertNode(node, node.left,val));
        }
    }

    private static TreeNode searchNode(TreeNode node,int val)
    {
        if(node == null)
            return null;

        if(node.val== val)
            return node;
        else if(node.val<val)
        {
            return (searchNode(node.right, val));
        }
        else
        {
            return (searchNode(node.left,val));
        }
    }

    private static void DFSTraversal(TreeNode node, List<Integer> output)
    {
        if(node==null)
            return;

        DFSTraversal(node.left,output);
        DFSTraversal(node.right,output);
        output.add(node.val);

        return;
    }

    private static void BFSTraversal(TreeNode node, List<Integer> output)
    {
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(node);
        int level =0;

        while(!queue.isEmpty())
        {
            int l = queue.size();

            for(int i=0;i<l;i++)
            {
                //System.out.println(level);

                TreeNode temp = queue.remove();
                output.add(temp.val);

                if(temp.left!=null)
                    queue.add(temp.left);

                if(temp.right!=null)
                    queue.add(temp.right);
            }
            level++;
        }
        return;
    }

    public static void main(String arg[])
    {
        root = new TreeNode(11);
        insertNode(null,root,7);
        insertNode(null,root,17);
        insertNode(null,root,5);
        insertNode(null,root,8);
        insertNode(null,root,15);
        insertNode(null,root,18);



        List<Integer> dfsTraversed = new ArrayList<>();
        DFSTraversal(root,dfsTraversed);
        System.out.println(dfsTraversed);

        List<Integer> bfsTraversed = new ArrayList<>();
        BFSTraversal(root,bfsTraversed);
        System.out.println(bfsTraversed);

    }
}
