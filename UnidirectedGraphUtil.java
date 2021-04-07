import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class UnidirectedGraphUtil
{
    public static HashMap<Integer, Set<Integer>> connectionMap = new HashMap<>();
    public static HashSet<Integer> nodes = new HashSet<>();
    public static HashSet<Integer> processedNodes = new HashSet<>();

    public static void main(String args[])
    {
        int edges[][] = {{1,2},{2,3},{3,4},{1,4},{3,5},{5,4},{6,7}};
        preProcess(edges);
        System.out.println(connectionMap);
        System.out.println(findLoop());
        System.out.println(findNumberOfClusters());
        topologicalSort(edges);
    }

    private static void preProcess(int[][] edges)
    {
        //Pre-processing to form graph structure
        for(int i=0;i<edges.length;i++)
        {
            nodes.add(edges[i][0]);
            nodes.add(edges[i][1]);
            Set connections =  connectionMap.getOrDefault(edges[i][0],new HashSet<Integer>());
            connections.add(edges[i][1]);
            connectionMap.put(edges[i][0], connections);
        }
    }

    private static boolean findLoop()
    {
        Set<Integer> visited = new HashSet<Integer>();
        processedNodes.clear();

        for(Integer nodeId: nodes)
        {
            //search only unvisted nodes
            if(!processedNodes.contains(nodeId))
            {
                if(isLoopFound(nodeId, visited))
                    return true;
            }
        }

        return false;
    }

    private static boolean isLoopFound(int nodeId, Set<Integer> visited)
    {
        visited.add(nodeId);
        processedNodes.add(nodeId);
        //System.out.println(nodeId+" "+connectionMap.get(nodeId));
        if(connectionMap.containsKey(nodeId)) {

            for (Integer connection : connectionMap.get(nodeId)) {
                //System.out.println(nodeId);
                if (!visited.contains(connection)) {
                    isLoopFound(connection, visited);
                } else {
                    //System.out.println(nodeId+"+");
                    return true;
                }
            }
        }
        visited.remove(nodeId);
        return false;
    }

    private static int findNumberOfClusters()
    {
        Set<Integer> visited = new HashSet<Integer>();
        int clusterCount=0;
        processedNodes.clear();

        for(Integer nodeId: nodes)
        {
            //search only unvisted nodes
            if(!processedNodes.contains(nodeId))
            {
                isLoopFound(nodeId, visited);
                clusterCount++;
            }
        }
        return clusterCount;
    }

    private static void topologicalSort(int edges[][])
    {
        Set<Integer> dependencyNodes = new HashSet<>();
        Stack<Integer> dependencyOutput = new Stack<>();
        Set<Integer> visited = new HashSet<Integer>();

        //find node set with have no reverse associations
        for(int i=0;i<edges.length;i++)
        {
            dependencyNodes.add(edges[i][1]);
        }

        Set<Integer> independentNodes = new HashSet<>();
        independentNodes.addAll(nodes);
        independentNodes.removeAll(dependencyNodes);

        for(Integer cur: independentNodes)
        {
            topologicalTraverse(cur, dependencyOutput,visited);
        }

        //print Stack

        while(!dependencyOutput.isEmpty())
        {
            System.out.print(dependencyOutput.pop()+"->");
        }

    }


    private static void topologicalTraverse(int node, Stack output, Set<Integer> visited)
    {
        output.add(node);
        visited.add(node);
        //System.out.println(node+":"+connectionMap.get(node)+"v:"+visited+"o:"+output);

        if(connectionMap.containsKey(node)) {
            for (Integer connection : connectionMap.get(node)) {
                if (!visited.contains(connection)) {
                    topologicalTraverse(connection, output, visited);
                } else {
                    return;
                }
            }
        }

        return;
    }
}