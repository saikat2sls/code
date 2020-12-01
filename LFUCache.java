/*
https://leetcode.com/problems/lfu-cache/

keep cache of items sorted by frequency

apis (functional requirement)
LFUCache(n)
insert(key, val)
get(key)
displayCache()

non-functional requirment:

//size of cache cannot exceed n
//store the n most frequently accessed items in min-heap
//when item is removed its count becomes zero


Implementation:
keep map of the count of the items
keep min-heap to track the top n pages/items

on every insert search for the item-id in the cache list and update the count in the min-heap

TimeComplexity
insert: O(1) + O(log n)  + O(log n) + O(1)  => O(log n)

If Id is generated using the time-stamp then for clashes LRU can be used as tie-breaker
 */

import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class LFUCache {
    public static Map<Integer,LFUNode>  nodeMap =new HashMap<>();
    public static PriorityQueue<LFUNode> minHeap = new PriorityQueue<>();
    public static int max;
    public static int lfuCount;

    public LFUCache(int capacity)
    {
        max = capacity;
    }

    static class LFUNode implements Comparable<LFUNode>{
        int nid;
        int frequency;
        int value;

        public LFUNode(int id, int fr, int val)
        {
            nid=id;
            frequency=fr;
            value = val;
        }

        @Override
        public int compareTo(LFUNode a)
        {
            if(frequency>a.frequency)
                return 1;
            else if(frequency==a.frequency)
                return 0;
            else
                return -1;
        }
    }

    public static boolean insert(int key, int val)
    {
        System.out.println("Insert:"+key);
        //check if cache is already filled
        if(lfuCount==max)
        {
            //empty the LFU item from cache
            LFUpop();
        }
        else
        {
            lfuCount++;
        }

        //update count map
        int cnt=0;
        if(nodeMap.containsKey(key))
        {
            LFUNode temp = nodeMap.get(key);
            //remove the item from heap
            minHeap.remove(temp);
            cnt = temp.frequency;
        }

        cnt++;
        LFUNode node =new LFUNode(key,cnt,val);

        //update the heap
        minHeap.add(node);
        nodeMap.put(key, node);

        return true;
    }


    public static int LFUpop()
    {
        int ret=-1;
        //TODO
        LFUNode temp = minHeap.poll();

        //ret = getItemWithCount();
        ret = temp.nid;
        System.out.println(ret+"removed from LFU");
        if(ret!=-1)
        {
           nodeMap.remove(temp);
        }
        return ret;
    }



    public static void display()
    {
        for(int nid:nodeMap.keySet())
        {
            System.out.print(nid+":"+nodeMap.get(nid).value+" ");
        }
        System.out.println();

        for(LFUNode node:minHeap)
        {
            System.out.print(node.nid+":"+node.frequency+"  ");
        }
        System.out.println();
    }

    public static void main(String arg[])
    {
        max=5;
        insert(1,424134);
        insert(2,43432);
        insert(3,432434);
        //display();
        insert(2,5454);
        display();
        insert(4,5545);
        display();
        insert(2,643643);
        display();
        insert(5,534);
        display();
        insert(6,544);
        display();
        insert(7,545);
        insert(8,5445);
        display();
    }
}
