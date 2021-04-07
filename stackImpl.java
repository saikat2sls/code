import java.util.ArrayList;
import java.util.List;

public class stackImpl {

    public  List<Integer> stack = new ArrayList<Integer>();
    public  int top;
    public  int size;

    public stackImpl(int max)
    {
        top=-1;
        size=max;
    }

    public boolean isEmpty()
    {
        if(top==-1)
        {
          return true ;
        }
        return false;
    }

    public boolean isFull()
    {
        if(top==size-1)
        {
            return true;
        }
        return false;
    }

    public int top()
    {
        if(top!=-1)
        {
            return stack.get(top);
        }
        return -1;
    }

    public int pop()
    {
        if(top!=-1)
        {
            int ret = stack.get(top);
            stack.remove(top);
            top--;
            return ret;
        }
        return -1;
    }

    public  void push(int input)
    {
        if(top<size-1) {
            stack.add(input);
            top++;
        }

        //TODO overflow
    }

    public void printStack()
    {
        for(int i=0;i<=top;i++)
        {
            System.out.print(" "+stack.get(i));
        }
        System.out.println();
    }

    public static void main(String args[])
    {
        stackImpl myStack = new stackImpl(5);
        myStack.push(3);
        myStack.push(10);
        myStack.push(31);
        myStack.push(130);
        myStack.printStack();
        myStack.pop();
        myStack.printStack();
        myStack.push(13);
        myStack.push(150);
        myStack.push(160);
        myStack.printStack();
        myStack.pop();
        myStack.printStack();
        myStack.pop();
        myStack.printStack();
        myStack.pop();
        myStack.pop();
        myStack.printStack();
        myStack.pop();
        myStack.printStack();
    }
}
