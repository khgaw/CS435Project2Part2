import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

public class TopSort {
    static ArrayList<GraphNode> allNodes = new ArrayList<>();
    static ArrayList<Integer> getInDegrees(DirectedGraph graph)
    {
        ArrayList<Integer> degrees = new ArrayList<>();
        for (int i = 0; i < allNodes.size(); i++)
            degrees.add(0);
        for (GraphNode node : allNodes)
        {
            for (Edge edge : node.neighbors) {
                GraphNode dest = edge.destination;
                degrees.set(dest.value, degrees.get(dest.value)+1);
            }
            allNodes.set(node.value, node);
        }
        return degrees;
    }

    static ArrayList<GraphNode> Kahns(final DirectedGraph graph)
    {
        ArrayList<GraphNode> path = new ArrayList<>();
        LinkedList<GraphNode> queue = new LinkedList<>();


        for (GraphNode node : graph.getAllNodes())
        {
            allNodes.add(null);
        }
        // Find degree of each node
        ArrayList<Integer> degrees = getInDegrees(graph);

        Boolean allVisited = false;
        while (!allVisited) {
            // Check to see if all nodes have been visited
            for (int degree : degrees)
            {
                if (degree != -1) {
                    allVisited = false;
                    break;
                }
                allVisited = true;
            }

            // Find all degrees = 0
            for (int x = 0; x < degrees.size(); x++) {
                if (degrees.get(x) == 0) {
                    // Add it to queue
                    queue.push(allNodes.get(x));
                    // Decrease node's degree to -1
                    degrees.set(x, -1);
                }
            }
            while(!queue.isEmpty()) {
                GraphNode node = queue.poll();
                // Decrease neighbor's degree
                if (node.neighbors != null) {
                    for (Edge edge : node.neighbors) {
                        int index = edge.destination.value;
                        degrees.set(index, degrees.get(index) - 1);
                    }
                }
                // Add node to path
                path.add(node);
            }
        }
        return path;
    }

    static ArrayList<GraphNode> mDFS(final DirectedGraph graph)
    {
        ArrayList<GraphNode> path = new ArrayList<>();
        Stack<GraphNode> stack = new Stack<>();
        Stack<GraphNode> fullStack = new Stack<>();

        GraphNode curr = graph.get(0);

        while (fullStack.size() != graph.getAllNodes().size())
        {
            if ((!curr.visited && curr.neighbors.size() != 0))// || (!curr.visited && curr.neighbors.size() != 0))
            {
                stack.add(curr);
            }
            curr.visited = true;
            int count=0;
            // Add each edge to the stack
            for (Edge edge : curr.neighbors)
            {
                if (!edge.destination.visited) {
                    stack.push(edge.destination);
                    count++;
                    //continue;
                }
            }
            if (count==0)
                fullStack.add(curr);
            if (stack.isEmpty())
            {
                for (GraphNode temp : graph.getAllNodes())
                {
                    if (!temp.visited) {
                        curr = temp;
                        break;
                    }
                }
            }
            else {
                curr = stack.pop();
            }
        }

        while (!fullStack.isEmpty())
            path.add(fullStack.pop());
        return path;
    }
}
