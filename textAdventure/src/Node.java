import org.omg.CORBA.PUBLIC_MEMBER;

import java.util.ArrayList;

public class Node {
    private String name;
    private ArrayList<Node> neighbors;

    public Node(String name) {
        neighbors = new ArrayList<Node>();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addNeighbor(Node n) {
        for (Node node : neighbors) {
            if (node.getName().equals( n.getName() ) ) { return; }
        }
        neighbors.add(n);
    }

    public void addNeighbor(String name) {
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

    public void addTwoDirectionNeighbor(Node n) {
        if (n == null) { return; }

        this.addNeighbor( n );
        n.addNeighbor( this );
    }

}
