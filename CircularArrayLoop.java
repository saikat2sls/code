import java.util.ArrayList;

/**
 * You are given a circular array nums of positive and negative integers. If a number k at an index is positive, then move forward k
 * steps. Conversely, if it's negative (-k), move backward k steps. Since the array is circular, you may assume that the
 * last element's next element is the first element, and the first element's previous element is the last element.
 *
 * Determine if there is a loop (or a cycle) in nums. A cycle must start and end at the same index and the cycle's length > 1.
 * Furthermore, movements in a cycle must all follow a single direction. In other words, a cycle must not consist of both forward
 * and backward movements.
 *
 *
 *
 * Example 1:
 *
 * Input: [2,-1,1,2,2]
 * Output: true
 * Explanation: There is a cycle, from index 0 -> 2 -> 3 -> 0. The cycle's length is 3.
 *
 * Example 2:
 *
 * Input: [-1,2]
 * Output: false
 * Explanation: The movement from index 1 -> 1 -> 1 ... is not a cycle, because the cycle's length is 1.
 * By definition the cycle's length must be greater than 1.
 *
 * Example 3:
 *
 * Input: [-2,1,-1,-2,-2]
 * Output: false
 * Explanation: The movement from index 1 -> 2 -> 1 -> ... is not a cycle, because movement from index 1 -> 2 is a forward movement,
 * but movement from index 2 -> 1 is a backward movement. All movements in a cycle must follow a single direction.
 */


public class CircularArrayLoop {
    public static boolean circularArrayLoop(int[] nums) {

        ArrayList<Integer> visited = new ArrayList<Integer>();
        int sz = nums.length;
        int flag=1;int dir=0;int nextIndex,prev;
        boolean isCycle=false;
        boolean shouldContinue = true;

        for(int i=0;i<sz;i++)
        {
            // System.out.println(visited);
            shouldContinue=true;

            if(visited.contains(i))
                continue;

            if(nums[i]>=0)
            {
                dir=1;
            }
            else
                dir=-1;

            nextIndex=i;
            ArrayList<Integer> temp = new ArrayList<Integer>();

            while (shouldContinue)
            {
                temp.add(nextIndex);
                prev= nextIndex;
                if(dir==1)
                {
                    nextIndex = (nums[nextIndex]+nextIndex) % sz;
                }
                else
                {
                    nextIndex = (nextIndex + sz + nums[nextIndex]%sz)%sz;
                }

                if(visited.contains(nextIndex) || nums[nextIndex]*dir<1 || prev==nextIndex)
                {
                    shouldContinue=false;
                    continue;
                }

                if(temp.contains(nextIndex))
                {
                    shouldContinue=false;
                    isCycle=true;
                }
            }

            visited.addAll(temp);
            if(isCycle)
                break;
        }

        return isCycle;

    }

    public static void main(String args[])
    {
        int arr[]= new int[]{3,1,2};
        System.out.println(circularArrayLoop(arr));
    }
}
