import java.util.LinkedList;
import java.util.List;

public class MergeSort {

    public static void main(String args[])
    {
         int[] input = new int[]{67,23,45,12,87,54,22,89,2,8,5};
         List output = mergeSort(0,input.length-1,input);
         System.out.println(output);
    }

    public static List<Integer> mergeSort(int start, int end, int[] input)
    {
        int mid = (start+end)/2;
        List<Integer> sorted = new LinkedList<>();

        if(start==end)
        {
            sorted.add(input[start]);
            return sorted;
        }

        List leftSort = mergeSort(start,mid,input);
        List rightSort = mergeSort(mid+1,end,input);

        return(merge(leftSort,rightSort));
    }

    public static List<Integer> merge(List<Integer> l1, List<Integer> l2)
    {
        List<Integer> merged = new LinkedList<>();
        int len1=l1.size();
        int len2= l2.size();

        int i=0,j=0;

        while(i<len1 && j<len2)
        {
            if(l1.get(i)<l2.get(j))
            {
                merged.add(l1.get(i++));
            }
            else
            {
                merged.add(l2.get(j++));
            }
        }

        while(i<len1)
        {
            merged.add(l1.get(i++));
        }
        while(j<len2)
        {
            merged.add(l2.get(j++));
        }
        return merged;
    }
}
