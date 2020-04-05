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
        GridEdge edge = new GridEdge(second, 1);
        first.neighbors.add(edge);
        GridEdge edge2 = new GridEdge(first, 1);
        second.neighbors.add(edge2);
    }

    public void removeWeightedEdge(final GridNode first, final GridNode second)
    {
        for (GridEdge edge : first.neighbors) {
            if (edge.destination == second) {
                first.neighbors.remove(edge);
                break;
            }
        }
        for (GridEdge edge : second.neighbors) {
            if (edge.destination == first) {
                second.neighbors.remove(edge);
                break;
            }
        }
    }

    public HashSet<GridNode> getAllNodes()
    {
        return allNodes;
    }

    public GridNode get(int element)
    {
        for (GridNode node : allNodes) {
            if (node.value == (element))
                return node;
        }
        return null;
    }

    public GridNode getXY(int x, int y)
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
    int value;
    boolean visited;
    ArrayList<GridEdge> neighbors;
    int x, y;
    public GridNode (int num1, int num2, int val)
    {
        x = num1;
        y=num2;
        neighbors = new ArrayList<>();
        value = val;
        visited = false;
    }
}

class GridEdge
{
    int weight;
    GridNode destination;
    public GridEdge (GridNode second, int num)
    {
        weight = num;
        destination = second;
    }
}