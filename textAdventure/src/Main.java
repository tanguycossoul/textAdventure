import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    /* Rooms connectivity matrix:
                    hall    bedroom closet  dungeon kitchen trap
        hall        0       1       1       0       0       0
        bedroom     0       0       0       1       0       0
        closet      1       0       0       0       0       0
        dungeon     0       0       0       0       1       1
        kitchen     1       0       0       0       0       0
        trap        0       0       0       0       0       0
     */
    private static ArrayList<Node> rooms = new ArrayList<>();
    private static String[] room_names = { "hall", "bedroom", "closet", "dungeon", "kitchen", "trap" };
    private static int[][] connectivity = { { 0, 1, 1, 0, 0, 0},
                                            { 0, 0, 0, 1, 0, 0},
                                            { 1, 0, 0, 0, 0, 0},
                                            { 0, 0, 0, 0, 1, 1},
                                            { 1, 0, 0, 0, 0, 0},
                                            { 0, 0, 0, 0, 0, 0} };


    public static void main(String[] args) {

        // Build up graph of connected node
        for (String name : room_names) {
            rooms.add( new Node(name) );
        }

        for (int i = 0; i < connectivity[0].length; i++) {
            for (int j = 0; j < connectivity.length; j++) {
                if (connectivity[i][j] == 1) {
                    addEdge(room_names[i], room_names[j]);
                }
            }
        }

        // Game loop
        Node currentRoom = rooms.get(0);

        String response = "";
        Scanner in = new Scanner(System.in);
        do {
            System.out.println("You are currently in the " + currentRoom.getName());
            if (currentRoom.getNeighborNames() == null) {
                System.out.println("You're stuck and die there. The end.");
                break;
            }
            else {
                System.out.print("You can go to the " );
                for (int i = 0; i < currentRoom.getNeighborNames().length; i++) {
                    System.out.print(currentRoom.getNeighborNames()[i]);
                    if (i < currentRoom.getNeighborNames().length - 1) {
                        System.out.print(" or ");
                    } else {
                        System.out.println();
                    }
                }
            }

            System.out.print("Type the name of the room you want to go to: ");
            response = in.nextLine();

            Node nextRoom = currentRoom.getNeighbor( response );
            if (nextRoom == null) {
                System.out.println("You can't go to " + response + ". Try again.");
            } else {
                currentRoom = nextRoom;
            }

        } while (!response.equals("quit"));
    }

    private static void addEdge(String name1, String name2) {
        getRoom(name1).addNeighbor( getRoom(name2) );
        System.out.println("INFO: connecting " + name1 + " to " + name2);
    }

    private static Node getRoom(String name) {
        for (Node n : rooms) {
            if (n.getName().equals( name )) { return n; }
        }
        return null;
    }
}
