import java.util.LinkedList;
import java.util.Queue;

/*
  push(1,2,3,4,5) -> 1,2,3,4,5

  pop() -> 5

  push(6) -> 1,2,3,4,6

 */


public class StackofQ {

    public static Integer INVALID_OPERATION = -555;
    static class Stack{
        Queue<Integer> qInUse = new LinkedList<Integer>();

        public int pop()
        {
            if(!qInUse.isEmpty())
                return qInUse.poll();

            return INVALID_OPERATION;
        }

        public void push(int val)
        {
            Queue<Integer> temp = new LinkedList<Integer>();
            temp.add(val);
            while(!qInUse.isEmpty())
            {
                int n = qInUse.poll();
                temp.add(n);
            }
            qInUse = temp;
        }
    }

    public static void main(String arv[])
    {
        Stack stk = new Stack();
        stk.push(1);
        stk.push(2);
        System.out.println(stk.pop());
        stk.push(3);
        stk.push(4);
        System.out.println(stk.pop());
        stk.push(5);
        System.out.println(stk.pop());
        System.out.println(stk.pop());
        System.out.println(stk.pop());
        System.out.println(stk.pop());

    }
}
