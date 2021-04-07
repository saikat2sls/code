import java.util.LinkedList;

class TreeNode
{
    TreeNode left;
    TreeNode right;
    int val;

    TreeNode(int value)
    {
        left=null;
        right=null;
        val = value;
    }
}



public class PractiseTree
{
    static int maxValue = 0;
    static TreeNode maxValuePivot =null;

    public static void main(String args[])
    {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right =  new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right =  new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right =  new TreeNode(7);

        getMaxValuePath(root);
        System.out.println(maxValue);
        traverseZigZag(root);
    }

    private static void traverseZigZag(TreeNode node)
    {
        boolean isOdd = true;
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(node);
        TreeNode temp;

        while(!queue.isEmpty())
        {
            int l = queue.size();

            for(int i =0; i<l;i++)
            {
                if(isOdd)
                {
                    temp = queue.removeLast();
                }
                else
                {
                    temp = queue.removeFirst();
                }

                System.out.println(temp.val);
                if(temp.left!=null)
                {
                    queue.add(temp.left);
                }
                if(temp.right!=null)
                {
                    queue.add(temp.right);
                }
            }

            //toggle the isOdd flag
            isOdd = !isOdd;
        }
    }

    private static void printLongestValueBranch(TreeNode start)
    {

    }

    private static int getMaxValuePath(TreeNode node)
    {
        if(node==null)
            return 0;

        int l = getMaxValuePath(node.left);
        int r = getMaxValuePath(node.right);
        int maxValueBranch = node.val + Math.max(l,r);


        //Doesnot work for negative values
        if((Math.max(node.val +l+r,maxValueBranch))>maxValue)
        {
            maxValue= node.val+l+r;
            maxValuePivot = node;
        }

        return (node.val + Math.max(l,r));
    }

}  

 

  