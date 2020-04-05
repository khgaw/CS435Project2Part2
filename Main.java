import java.util.*;

public class Main
{
    public static void main(String[] args)
    {
        //WeightedGraph graph = new WeightedGraph();
        //graph = createRandomCompleteWeightedGraph(10);
        //graph.addNode(0);
        //graph.addNode(1);
        //graph.addNode(2);
        //graph.addNode(3);
        //graph.addNode(4);
        //graph.addNode(5);
        //graph.addNode(6);
        //graph.addNode(7);
        //graph.addWeightedEdge(graph.get(0), graph.get(1), 2);
        //graph.addWeightedEdge(graph.get(1), graph.get(2), 3);
        //graph.addWeightedEdge(graph.get(0), graph.get(2), 1);
        //graph.addWeightedEdge(graph.get(1), graph.get(3), 2);
        //graph.addWeightedEdge(graph.get(0), graph.get(3), 10);
        //graph.addWeightedEdge(graph.get(3), graph.get(4), 2);
        //graph.addDirectedEdge(graph.get(7), graph.get(5));
        //graph.addDirectedEdge(graph.get(7), graph.get(4));
        //graph = createRandomUnweightedGraphIter(10);
        //ArrayList<GraphNode> path = TopSort.mDFS(graph);
        //HashMap<GraphNode, Integer> vals = dijkstras(graph.get(0));

        //System.out.println("************");
        //System.out.println(graph.checkCycles(graph, graph.get(0)));
        //vals.foreach()
            //System.out.println(x.value);

        GridGraph graph = createRandomGridGraph(100);
        //GridGraph graph = new GridGraph();
        //graph.addGridNode(0, 0, 0);
        //graph.addGridNode(0, 1, 1);
        //graph.addGridNode(1, 0, 2);
        //graph.addGridNode(1, 1, 3);
        //graph.addGridNode(1, 1, 3);
        //graph.addUndirectedEdge(graph.getXY(0,0), graph.getXY(0, 1), 1);
        //graph.addUndirectedEdge(graph.getXY(0,1), graph.getXY(1, 1), 1);
        //graph.addUndirectedEdge(graph.getXY(0,0), graph.getXY(1, 0), 1);
        GridNode start = graph.getXY(0,0);
        GridNode end = graph.getXY(99,99);
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
            for (GraphNode node : nodes) {
                if (node.equals(i))
                    obj1=node;
            }
            for (GraphNode node : nodes) {
                if (node.equals(i+1))
                    obj2=node;
            }
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
            for (GraphNode node : nodes) {
                if (node.equals(i))
                    obj1=node;
            }
            for (GraphNode node : nodes) {
                if (node.equals(i+1))
                    obj2=node;
            }
            graph.addWeightedEdge(obj1, obj2, 1);
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
            curr.visited = true;
            for (Edge edge : curr.neighbors)
            {
                GraphNode temp = edge.destination;
                // If the node is not in values, add it
                if (!values.contains(temp)) {
                    values.add(temp);
                    int dist = distance.get(values.indexOf(curr));
                    distance.add(edge.weight + dist);
                    parents.add(curr);
                }
                else // If node already in values, compare current distance to last dist
                {
                    int index = values.indexOf(temp);
                    int currDist = distance.get(index);
                    int newDist = distance.get(values.indexOf(curr)) + edge.weight;

                    if (currDist < newDist)
                        continue;
                    else
                    {
                        distance.set(values.indexOf(temp), newDist);
                    }
                }
            }
            // Find the next min that is not visited
            while(true) {
                int min = 100;
                int i = 0;
                int minIndex = -1;
                for (; i < distance.size(); i++) {
                    if (distance.get(i) == 0)
                        continue;
                    if (distance.get(i) < min && !visited.contains(values.get(i))) {
                        min = distance.get(i);
                        minIndex = i;
                    }
                }
                if (minIndex == -1) {
                    allVisited = true;
                    break;
                }
                curr = values.get(minIndex);
                if (!curr.visited)
                    break;
            }
            for (GraphNode node : values)
            {
                if (!node.visited) {
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

    // GridNode Methods
    static GridGraph createRandomGridGraph(int n)
    {
        GridGraph graph = new GridGraph();
        int val = 0;
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                graph.addGridNode(i, j, val);
                val++;
            }
        }

        for (GridNode node : graph.getAllNodes())
        {
            int x = node.x;
            int y = node.y;

            // Check down and right neighbors in grid
            Random rand = new Random();
            int add = rand.nextInt(2);
            if (add==1 && x+1 < n)
            {
                GridNode temp = graph.getXY(x+1, y);
                graph.addUndirectedEdge(node, temp, 1);
            }

            add = rand.nextInt(2);
            if (add==1 && y+1 < n)
            {
                GridNode temp = graph.getXY(x, y+1);
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
            visited.add(curr);
            curr.visited = true;
            for (GridEdge edge : curr.neighbors)
            {
                GridNode temp = edge.destination;
                if (temp.visited)
                    continue;

                // If the node is not in values, add it
                if (!values.contains(temp)) {
                    values.add(temp);
                    int dist = distance.get(values.indexOf(curr));
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
            while(true) {
                int min = 1000000;
                int minIndex = -1;
                for (int i = 0; i < distance.size(); i++) {
                    if (distance.get(i) == 0)
                        continue;
                    if (distance.get(i) < min && !visited.contains(values.get(i))) {
                        min = distance.get(i);
                        minIndex = i;
                    }
                }
                if (minIndex == -1) {
                    break;
                }
                curr = values.get(minIndex);
                if (!curr.visited)
                    break;
            }
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

    static int manhattanDist(GridNode start, GridNode dest)
    {
        int destX = dest.x;
        int destY = dest.y;
        int startX = start.x;
        int startY = start.y;

        return Math.abs(destX-startX) + Math.abs(destY-startY);
    }
}