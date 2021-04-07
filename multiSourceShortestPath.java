/**
 Given a list of cities and connecting roads between the cities.
 Only a few cities have police stations given as a list

 Write an algorithm to figure out the cities managed by the policeStation jurisdiction
 ouput : Map<city(with policeStation), list of cities managed>

 **/

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/**
 Brute force for each city find the nearest police station and add to its list
 Complexity O(n2)

 optimised Solution: Multi-source BFS
 algorith:
 > Add the police-station to the list Queue
 > Keep a visitted list and

 **/



public class multiSourceShortestPath
{
    static Map<String, Vertex> nodes= new HashMap<>();
    static Set<String> controllers = new HashSet<>();


    public static void main(String args[])
    {

        //input graph nodes and edges
        addEdge("a","b",1);
        addEdge("c","b",2);
        addEdge("c","g",5);
        addEdge("b","d",4);
        addEdge("d","e",2);
        addEdge("d","f",3);

        controllers.add("a");
        controllers.add("f");

        formShortestPathCoverage();
    }


    public static void formShortestPathCoverage()
    {

        Map<String,Integer> visitedCost = new HashMap<>();
        PriorityQueue<Vertex> candidates =new PriorityQueue<>();


        for(String node: controllers)
        {
            Vertex v = nodes.get(node);
            v.cost=0;   //the controllers has distance 0 from itself
            v.controller=v;
            candidates.add(v);
        }

        while(!candidates.isEmpty())
        {
            Vertex selected = candidates.poll();
            if(selected.controller==null) {
                selected.controller = selected.predecessor.controller;
            }
            visitedCost.put(selected.nodeId, selected.cost);
            // add neighbours of the visited node to the priority q

            System.out.println("selected:"+selected.nodeId+"cost:"+selected.cost);

            int netcost;
            Vertex cand;
            for(Edge link:selected.links)
            {
                netcost = link.cost + visitedCost.get(selected.nodeId);
                cand = link.dest;
                if(cand.cost>netcost)
                {
                    cand.cost = netcost;
                    cand.predecessor = link.source;
                }
                if(candidates.contains(cand))
                {
                    candidates.remove(cand);
                }
                //new candidate shouldnot be already visited
                if(visitedCost.get(cand.nodeId)==null)
                {
                    candidates.add(cand);
                }
            } // end of for
        } // end of while
    }


    public static void addEdge(String srcId, String dstId, int cost)
    {
        Vertex src,dst;
        if(nodes.containsKey(srcId))
        {
            src = nodes.get(srcId);
        }
        else
        {
            src = new Vertex(srcId);
            nodes.put(srcId,src);
        }


        //since disirectional
        if(nodes.containsKey(dstId))
        {
            dst = nodes.get(dstId);
        }
        else
        {
            dst = new Vertex(dstId);
            nodes.put(dstId,dst);
        }

        Edge e1 = new Edge(src,dst,cost);
        Edge e2 = new Edge(dst,src,cost);
        src.addLink(e1);
        dst.addLink(e2);
    }

}

class Vertex implements Comparable<Vertex>
{
    String nodeId;
    List<Edge> links;
    Vertex controller;
    Vertex predecessor;
    int cost;

    public Vertex(String id)
    {
        nodeId=id;
        cost = 11111;  //infinity
        links= new LinkedList<>();
        controller=null;
    }

    public int compareTo(Vertex node2)
    {
        return (cost-node2.cost);
    }

    public void addLink(Edge e)
    {
        links.add(e);
    }
}

class Edge implements Comparable<Edge>
{
    Vertex source;
    Vertex dest;
    int cost;

   public Edge(Vertex s, Vertex d, int val)
   {
        source =s;
        dest =d;
        cost = val;
   }

   public int compareTo( Edge e2)
   {
       return (cost-e2.cost);
   }
}


