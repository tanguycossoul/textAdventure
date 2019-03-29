import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Graph {

    private static HashMap<String, Node> nodes = new HashMap<String, Node>();

    public Node getNode(String name) {
        return nodes.get(name);
    }

    public void addNode(String name, String description) {
        Node n = new Node(name, description);
        nodes.put(n.getName(), n);
    }

    public void addEdge(Node node1, Node node2) {
        node1.addNeighbor( node2 );
        System.out.println("INFO: connecting " + node1.getName() + " to " + node2.getName());
    }

    public void addEdge(String name1, String name2) {
        getNode(name1).addNeighbor( getNode(name2) );
        System.out.println("INFO: connecting " + name1 + " to " + name2);
    }

    public void addEdgeBidir(Node node1, Node node2) {
        node1.addNeighbor( node2 );
        node2.addNeighbor( node1 );
        System.out.println("INFO: connecting " + node1.getName() + " to " + node2.getName());
        System.out.println("INFO: connecting " + node2.getName() + " to " + node1.getName());
    }

    public void addEdgeBidir(String name1, String name2) {
        getNode(name1).addNeighbor( getNode(name2) );
        getNode(name2).addNeighbor( getNode(name1) );
        System.out.println("INFO: connecting " + name1 + " to " + name2);
        System.out.println("INFO: connecting " + name2 + " to " + name1);
    }


    //------------------------------------------------------------------------------------------------------------------
    public class Node {
        private String name;
        private String description;
        private HashMap<String, Node> neighbors;

        private Node(String name, String description) {
            this.name = name;
            this.description = description;
            neighbors = new HashMap<String, Node>();
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getName() {
            return name;
        }

        private void addNeighbor(Node n) {
            if (n == null) { return; }
            neighbors.put( n.getName(), n);
        }

        private void addNeighbor(String name) {
            Node n = nodes.get(name);
            if (n == null) { return; }
            neighbors.put(name, n);
        }

        public String[] getNeighborNames() {
            if (neighbors.size() == 0) { return null; }

            String[] output = new String[ neighbors.size() ];
            int i=0;
            for (String name : neighbors.keySet()) {
                output[i++] = neighbors.get(name).getName();
            }
            return output;
        }

        public Node getNeighbor(String name) {
            return neighbors.get( name );
        }

    }
}
