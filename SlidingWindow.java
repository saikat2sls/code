import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


/**
 * Problem Statement
 * Given an array of characters where each character represents a fruit tree, you are given two baskets and your goal is to put maximum number of fruits in each basket. The only restriction is that each basket can have only one type of fruit.
 * You can start with any tree, but once you have started you can’t skip a tree. You will pick one fruit from each tree until you cannot, i.e., you will stop when you have to pick from a third fruit type.
 * Write a function to return the maximum number of fruits in both the baskets.
 * Example 1:
 * Input: Fruit=['A', 'B', 'C', 'A', 'C']
 * Output: 3
 * Explanation: We can put 2 'C' in one basket and one 'A' in the other from the subarray ['C', 'A', 'C']
 * Example 2:
 * Input: Fruit=['A', 'B', 'C', 'B', 'B', 'C']
 * Output: 5
 * Explanation: We can put 3 'B' in one basket and two 'C' in the other basket.
 * This can be done if we start with the second letter: ['B', 'C', 'B', 'B', 'C']
 */


public class SlidingWindow
{
    static int CHOICE_TYPES=2;

    public static void main(String args[])
    {
        String input ="abcbbc";
        System.out.println(findMaxSubstring(input));

    }

    private static int findMaxSubstring(String input)
    {
     Set<Character> choiceSet = new HashSet<>();
     Map<Character,Integer> countMap = new HashMap<>();

    int l=input.length();
    int head=0;
    int end=1;
    int maxCount=1;
    int tempHead=0;   //can be use if want to know the set to be displayed as well
    int presentCount=1;
    choiceSet.add(input.charAt(head));
    countMap.put(input.charAt(head),1);

    while(end<l)
    {
        Character c = input.charAt(end);
        if(choiceSet.contains(c))
        {
            countMap.put(c, countMap.get(c)+1);
        }
        else {
            if (choiceSet.size() == CHOICE_TYPES) {
                // need to remove one type
                Character toRemove = input.charAt(head);
                if (presentCount > maxCount) {
                    maxCount = presentCount;
                }
                presentCount = presentCount - countMap.get(toRemove);
                countMap.remove(toRemove);
                choiceSet.remove(toRemove);


                //move the head to a different type
                while (input.charAt(head) == toRemove) {
                    head++;
                }
            }
            choiceSet.add(c);
            countMap.put(c,0);
          }
            countMap.put(c, countMap.get(c)+1);
            presentCount++;
            end++;
        }

        if(presentCount>maxCount)
        {
            maxCount=presentCount;
        }

        return maxCount;
    }
}
          
           


      

    