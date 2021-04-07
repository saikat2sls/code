import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;


/*
Given a collection of numbers that might contain duplicates, return all possible unique permutations.

Example :
[1,1,2] have the following unique permutations:

[1,1,2]
[1,2,1]
[2,1,1]
 */
public class Permutations {
    //public static Set<List<Integer>> output = new LinkedHashSet<>();
    public static void main(String args[]) throws Exception {

        int N;  //no
        Scanner in = new Scanner(System.in);
        List<Integer> inputs = new ArrayList<Integer>();
        Set<List<Integer>> permutes = new LinkedHashSet<>();

        N = in.nextInt();   //No of numbers
        for (int i = 0; i < N; i++) {
            int x = in.nextInt();
            inputs.add(x);
        }

        findPermutes(inputs,null,permutes);
        System.out.println(permutes);
    }

    private static void findPermutes(List<Integer> inputs, Stack<Integer> currentList, Set<List<Integer>> permutes)
    {
        //System.out.println(currentList);
        if(inputs.isEmpty())
        {
            List<Integer> tempList = new ArrayList<>(currentList);
            permutes.add(tempList);
            //System.out.println(tempList);
        }

        if(currentList==null)
        {
            currentList =new Stack<Integer>();
        }

        for(Integer n: inputs)
        {
            List<Integer> numberList=new ArrayList<>(inputs);
            numberList.remove(n);
            currentList.push(n);
            findPermutes(numberList, currentList, permutes);
            currentList.pop();
        }
    }
}
