import java.util.HashSet;
import java.util.ArrayList;

public interface Graph {

    HashSet<GraphNode> allNodes = new HashSet<>();
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
    public Edge (GraphNode second)
    {
        weight = 1;
        destination = second;
    }
}