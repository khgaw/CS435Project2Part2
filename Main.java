import java.util.*;

public class Main
{
    public static void main(String[] args)
    {
        unDirectedGraph graph = new unDirectedGraph();
        //graph = createLinkedList(10);

        graph.addNode(0);
        graph.addNode(1);
        graph.addNode(2);
        graph.addNode(3);
        graph.addNode(4);
        graph.addNode(5);
        graph.addUndirectedEdge(graph.get(0), graph.get(1));
        graph.addUndirectedEdge(graph.get(0), graph.get(2));
        graph.addUndirectedEdge(graph.get(1), graph.get(3));
        graph.addUndirectedEdge(graph.get(3), graph.get(5));
        graph.addUndirectedEdge(graph.get(4), graph.get(5));
        //graph = createRandomUnweightedGraphIter(10);
        ArrayList<GraphNode> path = GraphSearch.DFSRec(graph.get(1), graph.get(4));
        System.out.println("************");
        for (GraphNode x : path)
            System.out.println(x.value);
    }

    static Graph createRandomUnweightedGraphIter(int n)
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

    static Graph createLinkedList(int n)
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
}