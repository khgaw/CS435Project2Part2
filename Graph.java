import java.util.HashSet;

public interface Graph {

    HashSet<GraphNode> allNodes = new HashSet<>();
    void addNode(final int nodeVal);
    HashSet<GraphNode> getAllNodes();
    GraphNode get(int element);
}

class GraphNode
{
    int value;
    boolean visited;
    HashSet<Edge> neighbors;
    public GraphNode (int val)
    {
        neighbors = new HashSet<>();
        value = val;
        visited = false;
    }
}

class Edge
{
    int weight;
    GraphNode destination;
    public Edge (GraphNode second, int num)
    {
        weight = num;
        destination = second;
    }
}

