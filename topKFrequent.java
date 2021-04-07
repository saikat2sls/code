import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Given a non-empty array of integers, return the k most frequent elements.
 *
 * Example 1:
 *
 * Input: nums = [1,1,1,2,2,3], k = 2
 * Output: [1,2]
 * Example 2:
 *
 * Input: nums = [1], k = 1
 * Output: [1]
 * Note:
 *
 * You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
 * Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
 * It's guaranteed that the answer is unique, in other words the set of the top k frequent elements is unique.
 * You can return the answer in any order.
 */


class topKFrequent {

    public int[] topKFrequentUsingHeap(int[] nums, int k) {
        // O(1) time
        if (k == nums.length) {
            return nums;
        }

        // 1. build hash map : character and how often it appears
        // O(N) time
        Map<Integer, Integer> count = new HashMap();
        for (int n: nums) {
            count.put(n, count.getOrDefault(n, 0) + 1);
        }

        // init heap 'the less frequent element first'
        Queue<Integer> heap = new PriorityQueue<>((n1, n2) -> count.get(n1) - count.get(n2));

        // 2. keep k top frequent elements in the heap
        // O(N log k) < O(N log N) time
        for (int n: count.keySet()) {
            heap.add(n);
            if (heap.size() > k) heap.poll();
        }

        // 3. build an output array
        // O(k log k) time
        int[] top = new int[k];
        for(int i = k - 1; i >= 0; --i) {
            top[i] = heap.poll();
        }
        return top;
    }

    public int[] topKFrequentUsingSort(int[] nums, int k) {
        HashMap<Integer, Integer> countMap = new HashMap<>();
        HashMap<Integer, Integer> sortedCountMap =new HashMap<>();
        int[] output =new int[k];

        for(int i=0;i<nums.length;i++)
        {
            countMap.put(nums[i], countMap.getOrDefault(nums[i],0)+1);
        }

        List<Map.Entry<Integer,Integer>> sortedList = new ArrayList<>(countMap.entrySet());
        Collections.sort(sortedList, (o1, o2) -> o2.getValue() - o1.getValue());

        int count=0;
        for(Map.Entry<Integer,Integer> set: sortedList)
        {
            if(count<k)
            {
                output[count++]=set.getKey();
            }
            else
                break;
        }
        return output;
    }
}