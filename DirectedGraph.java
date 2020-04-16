import java.util.ArrayList;
import java.util.HashSet;
import java.util.Stack;

public class DirectedGraph implements Graph{

    private HashSet<GraphNode> allNodes;

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
            if (node.value == element)
                return node;
        }
        return null;
    }

    static boolean checkCycles(Graph graph, GraphNode node) {
        Stack<GraphNode> stack = new Stack<>();
        GraphNode curr = node;
        boolean cycle = true; // true is no cycle, false is yes cycle

        // Modified DFS that checks for cycles
        do
        {
            // If already visited, continue
            if (curr.visited)
            {
                continue;
            }

            curr.visited = true;
            int count=0;
            // Add each edge to the stack
            for (Edge edge : curr.neighbors)
            {
                if (!edge.destination.visited) {
                    stack.push(edge.destination);
                    count++;
                }
            }
            node.visited = false;
            if (stack.isEmpty())
                break;

            if (stack.contains(node)) {
                cycle = false;
                break;
            }
            curr = stack.pop();
        } while (curr != node);

        return cycle;
    }
}
