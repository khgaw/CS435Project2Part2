import java.util.HashSet;

public class DirectedGraph implements Graph{

    private HashSet<GraphNode> allNodes;
    //private ArrayList<ArrayList<Integer>> adjMatrix = new ArrayList<>();

    public DirectedGraph() {
        allNodes = new HashSet<>();
    }

    public void addNode(final int nodeVal)
    {
        GraphNode node = new GraphNode(nodeVal);
        allNodes.add(node);
    }

    public void addDirectedEdge(final GraphNode first, final GraphNode second)
    {
        Edge edge = new Edge(second, 1);
        first.neighbors.add(edge);
    }

    public void removeDirectedEdge(final GraphNode first, final GraphNode second)
    {
        for (Edge edge : first.neighbors) {
            if (edge.destination == second) {
                first.neighbors.remove(edge);
                break;
            }
        }
    }

    public HashSet<GraphNode> getAllNodes()
    {
        return allNodes;
    }

    public GraphNode get(int element)
    {
        for (GraphNode node : allNodes) {
            if (node.value == (element))
                return node;
        }
        return null;
    }

    static boolean checkCycles(Graph graph, GraphNode node) {
        // Check if anything has a destination of node
        for (GraphNode temp : graph.getAllNodes())
        {
            for (Edge edge : temp.neighbors)
            {
                if (edge.destination == node)
                    return false;
            }
        }
        return true; // No cycle
    }
}
