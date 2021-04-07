/*
 binary search application of find the next higher and next lower element in an array of sorted numbers
 2. use binary search to get the Mth root of N
 */


public class BinarySearchApplications {

   public static int getNextLower(int num, int start, int end, int[] array)
   {
       int mid = (start+end)/2;

       if(start<=end) {
           if (num <= array[mid]) {
               return getNextLower(num, start, mid-1, array);
           } else {
               return getNextLower(num, mid+1, end, array);
           }
       }
       return array[end];
   }

    public static int getNextHigher(int num, int start, int end, int[] array)
    {
        int mid = (start+end)/2;

        //System.out.println(start+":"+mid+":"+end);

        if(start<=end)
        {
            if(num>=array[mid])
            {
                return getNextHigher(num,mid+1,end,array);
            }
            else
            {
                return getNextHigher(num,start,mid-1,array);
            }
        }
        return array[start];
    }

    public static void main(String args[])
    {
        int arr[] = new int[]{2,5,8,12,14,17,19,20};
        System.out.println("next higher"+getNextHigher(12,0,arr.length, arr));
        System.out.println("next lower"+getNextLower(12,0,arr.length, arr));
    }
}
