import java.util.*;

public class Main
{
    public static void main(String[] args)
    {
        DirectedGraph graph = new DirectedGraph();
        graph = createRandomDAGIter(1000);

        //graph.addNode(0);
        //graph.addNode(1);
        //graph.addNode(2);
        //graph.addNode(3);
        //graph.addNode(4);
        //graph.addNode(5);
        //graph.addNode(6);
        //graph.addNode(7);
        //graph.addDirectedEdge(graph.get(0), graph.get(1));
        //graph.addDirectedEdge(graph.get(0), graph.get(3));
        //graph.addDirectedEdge(graph.get(2), graph.get(3));
        //graph.addDirectedEdge(graph.get(2), graph.get(6));
        //graph.addDirectedEdge(graph.get(2), graph.get(7));
        //graph.addDirectedEdge(graph.get(3), graph.get(6));
        //graph.addDirectedEdge(graph.get(7), graph.get(5));
        //graph.addDirectedEdge(graph.get(7), graph.get(4));
        //graph = createRandomUnweightedGraphIter(10);
        ArrayList<GraphNode> path = TopSort.mDFS(graph);

        System.out.println("************");
        //System.out.println(graph.checkCycles(graph, graph.get(0)));
        for (GraphNode x : path)
            System.out.println(x.value);
    }

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

}