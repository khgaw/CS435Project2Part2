import java.util.HashSet;

public class unDirectedGraph implements Graph {

    HashSet<GraphNode> allNodes;

    public unDirectedGraph() {
        allNodes = new HashSet<>();
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

    public void addNode(final int nodeVal)
    {
        GraphNode node = new GraphNode(nodeVal);
        allNodes.add(node);
    }

    public void addUndirectedEdge(final GraphNode first, final GraphNode second)
    {
        Edge edge = new Edge(second);
        first.neighbors.add(edge);
        Edge edge2 = new Edge(first);
        second.neighbors.add(edge2);
    }

    public void removeUndirectedEdge(final GraphNode first, final GraphNode second)
    {
        for (Edge edge : first.neighbors) {
            if (edge.destination == second) {
                first.neighbors.remove(edge);
                break;
            }
        }
        for (Edge edge : second.neighbors) {
            if (edge.destination == first) {
                second.neighbors.remove(edge);
                break;
            }
        }
    }
}
