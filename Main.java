import java.util.*;

public class Main
{
    public static void main(String[] args)
    {
        GridGraph graph = new GridGraph();
        graph = createRandomGridGraph(100);
        GridNode start = graph.getByCoordinates(0,0);
        GridNode end = graph.getByCoordinates(99,99);
        ArrayList<GridNode> path = astar(start, end);

        for (GridNode x : path)
            System.out.println(x.value);
    }

    // UNDIRECTED METHODS
    static unDirectedGraph createRandomUnweightedGraphIter(int n)
    {
        unDirectedGraph graph = new unDirectedGraph();
        for (int i = 0; i < n; i++)
        {
            graph.addNode(i);
        }
        // Between each two nodes, give a 50/50 chance of putting an edge in between
        HashSet<GraphNode> nodes = graph.getAllNodes();
        for(int i = 0; i < nodes.size(); i++) {
            for (int j = i; j < nodes.size(); j++) {
                Random rand = new Random();
                int add = rand.nextInt(2);
                if (add==1) {
                    GraphNode obj1 = null;
                    GraphNode obj2 = null;
                    for (GraphNode node : nodes) {
                        if (node.equals(i))
                            obj1=node;
                    }
                    for (GraphNode node : nodes) {
                        if (node.equals(j))
                            obj2=node;
                    }
                    graph.addUndirectedEdge(obj1, obj2);
                }
            }
        }
        return graph;
    }

    static unDirectedGraph createLinkedList(int n)
    {
        unDirectedGraph graph = new unDirectedGraph();
        for (int i = 0; i < n; i++)
        {
            graph.addNode(i);
        }
        // Attach edge between one node and the next only
        HashSet<GraphNode> nodes = graph.getAllNodes();
        for (int i = 0; i < n-1; i++)
        {
            GraphNode obj1 = null;
            GraphNode obj2 = null;
            obj1 = graph.get(i);
            obj2 = graph.get(i+1);
            graph.addUndirectedEdge(obj1, obj2);
        }

        return graph;
    }

    static ArrayList<GraphNode> BFTRecLinkedList()
    {
        Graph graph = createLinkedList(100);
        return GraphSearch.BFTRec(graph);
    }

    static ArrayList<GraphNode> BFTIterLinkedList()
    {
        Graph graph = createLinkedList(10000);
        return GraphSearch.BFTIter(graph);
    }

    // DIRECTED METHOD
    static DirectedGraph createRandomDAGIter(final int n)
    {
        DirectedGraph graph = new DirectedGraph();
        for (int i = 0; i < n; i++)
        {
            graph.addNode(i);
        }
        // Between each two nodes, give a 50% chance of putting an edge in between
        //HashSet<GraphNode> nodes = graph.getAllNodes();
        for(int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                Random rand = new Random();
                int add = rand.nextInt(10);
                if (add>5 && graph.checkCycles(graph, graph.get(j))) {
                    graph.addDirectedEdge(graph.get(i), graph.get(j));
                }
            }
        }
        return graph;
    }

    // WEIGHTED METHODS
    static WeightedGraph createRandomCompleteWeightedGraph(final int n) {
        WeightedGraph graph = new WeightedGraph();
        for (int i = 0; i < n; i++) {
            graph.addNode(i);
        }
        HashSet<GraphNode> nodes = graph.getAllNodes();

        for (int i = 0; i < nodes.size(); i++) {
            for (int j = 0; j < nodes.size(); j++) {
                if (i == j)
                    continue;
                Random rand = new Random();
                // Creates a random weight from 1-10
                int weight = rand.nextInt(10) + 1;
                GraphNode obj1 = graph.get(i);
                GraphNode obj2 = graph.get(j);
                graph.addWeightedEdge(obj1, obj2, weight);
            }
        }
        return graph;
    }

    static WeightedGraph createWeightedLinkedList(int n)
    {
        WeightedGraph graph = new WeightedGraph();
        ArrayList<GraphNode> ordered = new ArrayList<>();
        for (int i = 0; i < n; i++)
        {
            graph.addNode(i);
            ordered.add(graph.get(i));
        }
        // Attach edge between one node and the next only
        HashSet<GraphNode> nodes = graph.getAllNodes();
        for (int i = 0; i < n-1; i++)
        {
            graph.addWeightedEdge(ordered.get(i), ordered.get(i+1), 1);
        }
        return graph;
    }

    static HashMap<GraphNode, Integer> dijkstras (final GraphNode start)
    {
        HashMap<GraphNode, Integer> finList = new HashMap<>();
        ArrayList<GraphNode> values = new ArrayList<>();
        ArrayList<GraphNode> parents = new ArrayList<>();
        ArrayList<Integer> distance = new ArrayList<>();

        // Add the starting node
        values.add(start);
        parents.add(null);
        distance.add(0);
        GraphNode curr = start;
        ArrayList<GraphNode> visited = new ArrayList<>();
        boolean allVisited = false;

        while (!allVisited)
        {
            visited.add(curr);
            for (Edge edge : curr.neighbors)
            {
                GraphNode dest = edge.destination;
                // If the node is not in values, add it
                if (!values.contains(dest)) {
                    values.add(dest);
                    int dist = distance.get(values.indexOf(curr));
                    distance.add(edge.weight + dist);
                    parents.add(curr);
                }
                else // If node already in values, compare current distance to last dist
                {
                    int index = values.indexOf(dest);
                    int currDist = distance.get(index);
                    int newDist = distance.get(values.indexOf(curr)) + edge.weight;

                    if (currDist < newDist)
                        continue;
                    else
                    {
                        distance.set(values.indexOf(dest), newDist);
                    }
                }
            }
            // Find the next min that is not visited
            while(!allVisited) {
                int minIndex = findMin(distance, visited, values);

                if (minIndex == -1)
                    allVisited = true;
                else {
                    curr = values.get(minIndex);
                    if (!visited.contains(curr))
                        break;
                }
            }

            for (GraphNode node : values)
            {
                if (!visited.contains(node)) {
                    allVisited = false;
                    break;
                }
                allVisited = true;
            }
        }

        // Add everything to the HashMap
        for (int i = 0; i < distance.size(); i++)
        {
            finList.put(values.get(i), distance.get(i));
        }
        return finList;
    }

    static int findMin(final ArrayList<Integer> distance, final ArrayList<GraphNode> visited, final ArrayList<GraphNode> values)
    {
        int min = Integer.MAX_VALUE;
        int minIndex = -1;

        for (int i = 0; i < distance.size(); i++) {
            if (distance.get(i) == 0)
                continue;
            if (distance.get(i) < min && !visited.contains(values.get(i))) {
                min = distance.get(i);
                minIndex = i;
            }
        }
        return minIndex;
    }

    // GridNode Methods
    static GridGraph createRandomGridGraph(int n)
    {
        GridGraph graph = new GridGraph();
        int val = 0;
        for (int i = 0; i < n*n; i++)
        {
            graph.addGridNode(i%n, i/n, val);
            val++;
        }

        for (GridNode node : graph.getAllNodes())
        {
            int x = node.x;
            int y = node.y;

            // Check down and right neighbors in grid
            Random rand = new Random();
            // Made it 75% chance of having an edge because 50% did not give many good graphs
            int add = rand.nextInt(4);
            if (add < 3 && x+1 < n)
            {
                GridNode temp = graph.getByCoordinates(x+1, y);
                graph.addUndirectedEdge(node, temp, 1);
            }

            add = rand.nextInt(4);
            if (add < 3 && y+1 < n)
            {
                GridNode temp = graph.getByCoordinates(x, y+1);
                graph.addUndirectedEdge(node, temp, 1);
            }
        }
        return graph;
    }

    static ArrayList<GridNode> astar(final GridNode sourceNode, final GridNode destNode)
    {
        ArrayList<GridNode> path = new ArrayList<>();
        ArrayList<GridNode> values = new ArrayList<>();
        ArrayList<GridNode> parents = new ArrayList<>();
        ArrayList<Integer> distance = new ArrayList<>();
        ArrayList<GridNode> visited = new ArrayList<>();

        // Add the starting node
        values.add(sourceNode);
        parents.add(null);
        distance.add(0);
        GridNode curr = sourceNode;
        boolean allVisited = false;

        while (curr != destNode && !allVisited)
        {
            curr.visited = true;
            visited.add(curr);
            for (GridEdge edge : curr.neighbors)
            {
                GridNode temp = edge.destination;
                if (temp.visited)
                    continue;

                // If the node is not in values, add it
                if (!values.contains(temp)) {
                    values.add(temp);
                    int dist = distance.get(values.indexOf(curr)) + 1;
                    distance.add(dist + manhattanDist(temp, destNode));
                    parents.add(curr);
                }
                else // If node already in values, compare current distance to last dist
                {
                    int index = values.indexOf(temp);
                    int currDist = distance.get(index) + manhattanDist(temp, destNode);
                    int newDist = distance.get(values.indexOf(curr));

                    if (currDist < newDist)
                        continue;
                    else
                    {
                        distance.set(values.indexOf(temp), newDist);
                    }
                }
            }

            // Find the next min that is not visited
            do{
                int minIndex = findMinAStar(distance, values);

                if (minIndex == -1) {
                    break;
                }
                curr = values.get(minIndex);
            } while(curr.visited);

            for (GridNode node : values)
            {
                if (!node.visited) {
                    allVisited = false;
                    break;
                }
                allVisited = true;
            }
        }
        // Add everything to the HashMap
        while (curr != sourceNode)
        {
            path.add(0, curr);
            int index = values.indexOf(curr);
            curr = parents.get(index);
        }

        path.add(0, sourceNode);
        if (path.get(path.size()-1) != destNode)
            return null;
        return path;
    }

    static int findMinAStar(final ArrayList<Integer> distance, final ArrayList<GridNode> values)
    {
        int min = Integer.MAX_VALUE;
        int minIndex = -1;

        for (int i = 0; i < distance.size(); i++) {
            if (distance.get(i) == 0)
                continue;
            if (distance.get(i) < min && !values.get(i).visited) {
                min = distance.get(i);
                minIndex = i;
            }
        }
        return minIndex;
    }

    static int manhattanDist(GridNode start, GridNode dest)
    {
        return Math.abs(dest.x-start.x) + Math.abs(dest.y-start.y);
    }
}