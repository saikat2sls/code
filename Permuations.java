import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * logic : create a resource pool of characters from the sample word.
 * recursively select a character and remove that from resource pool so that a differernt character gets picked up in next iteration
 * when the resource pool is empty it indicates we got a valid permutation/word , backtrace and repeat with other remaining charcters in resource pool
 *
 * e.g : abc
 *       formations of word (temp)
 *       a -> ab -> abc
 *            ac -> acb
 *       b -> ba -> bac
 *            bc -> bca
 *       c -> ca -> cab
 *            cb -> cba
 */

public class Permuations {

    private static void getPermutations(ArrayList<Character> input, Set<String> output, String temp)
    {
        if(input.isEmpty())
        {
            //end word reached
            output.add(temp);
            System.out.println(temp);
        }

        for(Character c : input)
        {
            ArrayList<Character> choices = new ArrayList();
            choices.addAll(input);
            choices.remove(c);
            temp = temp+c;
            getPermutations(choices,output,temp);
            temp = temp.substring(0,temp.length()-1);
        }
    }


    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);
        String in = sc.next();
        Set<String> outputList = new HashSet<>();
        ArrayList<Character> input = new ArrayList<>();
        String temp ="";

        for(int i=0;i<in.length();i++) {
            input.add(in.charAt(i));
        }

        getPermutations(input,outputList,temp);
        System.out.println(outputList);
    }
}