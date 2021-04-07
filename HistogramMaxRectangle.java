import java.util.Stack;

public class HistogramMaxRectangle
{

    public static void main(String args[])
    {
        int[] input= new int[]{12,1,3,12,12,4,4,2,5};
        System.out.println(findMaxRectangle(input));
    }

    public static int findMaxRectangle(int[] input)
    {
        int maxRect=0;
        Stack<Integer> stack =new Stack();
        for(int i=0;i<input.length;i++)
        {
            if(stack.isEmpty())
            {
            stack.push(input[i]);
            continue;
            }

            int min = stack.peek();
            int top;
            int count=0;
            while(!stack.isEmpty() && stack.peek()>input[i])
            {
            top=stack.pop();
            count++;

            if(top*count>maxRect)
                maxRect=min*count;
            }



            for(int j=0;j<=count;j++) {
                stack.push(input[i]);
            }
            //System.out.println(stack);
        } //end of for

        System.out.println(stack);

        //Process the stack

        int count=1;
        while(!stack.isEmpty())
        {
        int temp = stack.pop();
        int area = temp*count;

        if(area>maxRect)
        maxRect=area;
        count++;
        }

        return maxRect;
    }
}
