import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class LRUCache {

    public static Map<Integer, LRUNode> cacheStore = new HashMap<>();
    public static PriorityQueue<LRUNode> minHeap = new PriorityQueue<>();

    public static int maxcount=5;
    public static int count=0;

    public static class LRUNode implements Comparable<LRUNode>
    {
        public long id;
        public int key;
        public int val;

        public LRUNode(int k, int v)
        {
            id = System.currentTimeMillis();
            val =v;
            key=k;
        }

        public int compareTo(LRUNode node)
        {
            if(node.id>id)
                return 1;
            else
                return -1;
        }
    }

    public static void updateHeap()
    {
        if(count>maxcount)
        {
            System.out.println("Heap full!! Evicting by LFU policy");
           LRUNode temp = minHeap.poll();
           cacheStore.remove(temp.key);
        }

    }

    public static int getValue(int k)
    {
        if(cacheStore.containsKey(k))
        {
            //update the timestamp
            LRUNode node = cacheStore.get(k);
            minHeap.remove(node.id);

            node.id = System.currentTimeMillis();
            minHeap.add(node);

            return node.val;
        }
        return -1;
    }

    public static int insert(int k, int v)
    {
        System.out.println("insert operation key:"+k+" value:"+v);
        LRUNode node;

        if(cacheStore.containsKey(k))
        {
            node = cacheStore.get(k);
            minHeap.remove(node);
            node.val = v;
        }
        else
        {
            node = new LRUNode(k,v);
            count++;
            updateHeap();
        }

        cacheStore.put(k,node);
        minHeap.add(node);
        return 1;
    }

    public static void display()
    {
        System.out.print("Cache Store:");
        for(int nid:cacheStore.keySet())
        {
            System.out.print(nid+":"+cacheStore.get(nid).val+" ");
        }

        /*
        for(Long node:minHeap)
        {
            System.out.print(node);
        }
        */

        System.out.println();
    }

    public static void main(String args[])
    {
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
