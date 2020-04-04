import java.util.HashSet;
import java.util.ArrayList;

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
    ArrayList<Edge> neighbors;;
    public GraphNode (int val)
    {
        neighbors = new ArrayList<>();
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

