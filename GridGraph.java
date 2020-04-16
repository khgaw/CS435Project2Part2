import java.util.ArrayList;
import java.util.HashSet;

public class GridGraph {
    HashSet<GridNode> allNodes = new HashSet<>();

    public void addGridNode(final int x, final int y, final int nodeVal)
    {
        GridNode node = new GridNode(x, y, nodeVal);
        allNodes.add(node);
    }

    public void addUndirectedEdge(final GridNode first, final GridNode second, final int edgeWeight)
    {
        first.neighbors.add(new GridEdge(second, 1));
        second.neighbors.add(new GridEdge(first, 1));
    }

    public void removeWeightedEdge(final GridNode first, final GridNode second)
    {
        first.neighbors.remove(getEdge(second));
        first.neighbors.remove(getEdge(first));
    }

    public HashSet<GridNode> getAllNodes()
    {
        return allNodes;
    }

    public GridNode getEdge(GridNode first)
    {
        for (GridEdge edge : first.neighbors) {
            if (edge.destination == first) {
                return first;
            }
        }
        return null;
    }

    public GridNode getByCoordinates(int x, int y)
    {
        for (GridNode node : allNodes) {
            if (node.x == x && node.y == y)
                return node;
        }
        return null;
    }
}

class GridNode
{
    boolean visited;
    ArrayList<GridEdge> neighbors;
    int x, y, value;

    public GridNode (int x, int y, int value)
    {
        this.x = x;
        this.y = y;
        neighbors = new ArrayList<>();
        this.value = value;
        visited = false;
    }
}

class GridEdge
{
    int weight;
    GridNode destination;
    public GridEdge (GridNode second, int weight)
    {
        this.weight = weight;
        destination = second;
    }
}