import java.util.HashSet;

public class WeightedGraph implements Graph {

    HashSet<GraphNode> allNodes = new HashSet<>();

    public void addNode(final int nodeVal)
    {
        GraphNode node = new GraphNode(nodeVal);
        allNodes.add(node);
    }

    public void addWeightedEdge(final GraphNode first, final GraphNode second, final int edgeWeight)
    {
        Edge edge = new Edge(second, edgeWeight);
        first.neighbors.add(edge);
    }

    public void removeWeightedEdge(final GraphNode first, final GraphNode second)
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

}
