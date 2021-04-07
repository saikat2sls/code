/**
 * find shortest path using djkstras algorithm
 * Using adjascency matrix
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * find shortest path using djkstras algorithm
 * Using adjascency matrix
 */


public class graphDjkstra {

    private static int MAX_NODES = 10;
    private static int MAX_DISTANCE =1111;
    private static int graph[][] = new int[MAX_NODES][MAX_NODES];


    private static void addEdge(int n1, int n2, int distance)
    {
        graph[n1][n2]=distance;
        graph[n2][n1]=distance;
    }


    private static int getSmallestDistance(Map<Integer,Integer> distanceMap)
    {
        int min=1111;
        int minNode=0;

        for(Integer node:distanceMap.keySet())
        {
            int dist = distanceMap.get(node);
            if(dist<min)
            {
                minNode = node;
                min =dist;
            }
        }

        return minNode;
    }


    private static Map<Integer,Integer> runDjkstra(int source)
    {
        Map<Integer,Integer> distanceMap = new HashMap<>();
        LinkedList<Integer> unvisited = new LinkedList<>();
        LinkedList<Integer>  visited =  new LinkedList<>();

        for(int i=0;i<MAX_NODES;i++)
        {
            distanceMap.put(i,MAX_DISTANCE);
        }
        distanceMap.put(source,0);


        unvisited.add(source);

        while(!unvisited.isEmpty()) {

                //get smallest distance node
                int selectedNode = getSmallestDistance(distanceMap);

                //get neighbour nodes
                List<Integer> neighbours = getNeighbours(selectedNode);

                unvisited.remove(selectedNode);
                visited.add(selectedNode);
                for (Integer node : neighbours) {
                    //Add ony if the node is selected already
                    if (visited.get(node) == null) {
                        unvisited.add(node);
                    }
                }


                //update distance vector with reference to the selected node
                updateDistance(distanceMap, neighbours, selectedNode);
            }

            return distanceMap;
        }

        private static List<Integer> getNeighbours(int node)
        {
            List<Integer> neighbours = new ArrayList<>();

            for(int i=0;i<MAX_NODES;i++)
            {
                if(i==node)
                    continue;

                if(graph[node][i]>0) {
                    neighbours.add(i);
                }
            }
            return neighbours;
        }

        private static void updateDistance(Map<Integer,Integer> distanceMap,List<Integer> neighbours,int selectedNode)
        {
            int selectedNodeDistance = distanceMap.get(selectedNode);
            int distance;

            for(Integer node: neighbours)
            {
                distance = selectedNodeDistance+graph[selectedNode][node];
                if(distance<distanceMap.get(node))
                {
                    distanceMap.put(node, distance);
                }
            }
        }


        private static void main(String args[])
        {
            int e;  // no of edges

            //forming the graph
            addEdge(1,2,2);

        }

    }
