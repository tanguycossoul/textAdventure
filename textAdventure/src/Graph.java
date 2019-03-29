import java.util.ArrayList;
import java.util.List;

public class Graph {

    private static List<Node> nodes = new ArrayList<>();
    private static String[] nodeNames;
    private static int[][] connectivity;


    public Node getNode(String name) {
        for (Node n : nodes) {
            if (n.getName().equals( name )) { return n; }
        }
        return null;
    }


    private void addEdge(String name1, String name2) {
        getNode(name1).addNeighbor( getNode(name2) );
        System.out.println("INFO: connecting " + name1 + " to " + name2);
    }


    // Getters and setters
    public String[] getNodeNames() {
        return nodeNames;
    }

    public void setNodeNames(String[] node_names) {
        Graph.nodeNames = node_names;
    }

    public int[][] getConnectivity() {
        return connectivity;
    }

    public void setConnectivity(int[][] connectivity) {
        Graph.connectivity = connectivity;

        // Build up graph of connected node
        for (String name : nodeNames) {
            nodes.add( new Node(name) );
        }

        for (int i = 0; i < connectivity[0].length; i++) {
            for (int j = 0; j < connectivity.length; j++) {
                if (connectivity[i][j] == 1) {
                    addEdge(nodeNames[i], nodeNames[j]);
                }
            }
        }
    }

    public class Node {
        private String name;
        private ArrayList<Node> neighbors;

        private Node(String name) {
            neighbors = new ArrayList<Node>();
            this.name = name;
        }

        public String getName() {
            return name;
        }

        private void addNeighbor(Node n) {
            for (Node node : neighbors) {
                if (node.getName().equals( n.getName() ) ) { return; }
            }
            neighbors.add(n);
        }

        private void addNeighbor(String name) {
            for (Node node : neighbors) {
                if (node.getName().equals( name ) ) { return; }
            }
            neighbors.add(new Node( name ));
        }

        public String[] getNeighborNames() {
            if (neighbors.size() == 0) { return null; }

            String[] arr = new String[ neighbors.size() ];
            for (int i = 0; i < neighbors.size(); i++) {
                arr[i] = neighbors.get(i).getName();
            }
            return arr;
        }

        public Node getNeighbor(String name) {
            for (int i = 0; i < neighbors.size() ; i++) {
                if (neighbors.get(i).getName().equals(name)) {
                    return neighbors.get(i);
                }
            }
            return null;
        }

        private void addTwoDirectionNeighbor(Node n) {
            if (n == null) { return; }

            this.addNeighbor( n );
            n.addNeighbor( this );
        }
    }
}
