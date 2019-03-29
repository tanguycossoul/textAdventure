import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        /* Rooms connectivity matrix:
                        hall    bedroom closet  dungeon kitchen trap
            hall        0       1       1       0       0       0
            bedroom     0       0       0       1       0       0
            closet      1       0       0       0       0       0
            dungeon     0       0       0       0       1       1
            kitchen     1       0       0       0       0       0
            trap        0       0       0       0       0       0
         */
        Graph g = new Graph();
        g.setNodeNames( new String[ ]{ "hall", "bedroom", "closet", "dungeon", "kitchen", "trap" } );
        g.setConnectivity( new int[][] { { 0, 1, 1, 0, 0, 0},
                                         { 0, 0, 0, 1, 0, 0},
                                         { 1, 0, 0, 0, 0, 0},
                                         { 0, 0, 0, 0, 1, 1},
                                         { 1, 0, 0, 0, 0, 0},
                                         { 0, 0, 0, 0, 0, 0} } );

        // Game loop
        Graph.Node currentRoom = g.getNode("hall");

        String response = "";
        Scanner in = new Scanner(System.in);
        Boolean quit = false;
        do {
            if (currentRoom.getNeighborNames() == null) {
                System.out.println("You're stuck and die there. The end.");
                quit = true;
            }
            else {
                System.out.println("What do you want to do?");
                response = in.nextLine();
                String[] words = response.split(" ");

                if (words[0].equals("go")){
                    Graph.Node destRoom = currentRoom.getNeighbor( words[1] );
                    if (destRoom == null) {
                        System.out.println("You can't go to " + words[1] + ". Try again.");
                    } else {
                        currentRoom = destRoom;
                    }
                    System.out.println("You are in the " + currentRoom.getName());
                }
                else if (words[0].equals("look")) {
                    commandLook( currentRoom );
                }
                else if (words[0].equals("add")) {
                    // TODO
                    System.out.println("INFO not implemented yet. Carry on.");
                }
                else if (words[0].equals("quit")) {
                    quit = true;
                }
                else {
                    System.out.println("Hmm, didn't get that. You can ask:");
                    System.out.println("  \"go <roomname>\" to go to a room");
                    System.out.println("  \"look\" to display the neighbors");
                    System.out.println("  \"add\" to add a room");
                    System.out.println("  \"quit\" to leave the game");
                }
            }
        } while (!quit);
    }

    // We know current has neighbors when we call this function
    private static void commandLook(Graph.Node currentRoom) {
        System.out.print("You can go to ");
        for (int i = 0; i < currentRoom.getNeighborNames().length; i++) {
            System.out.print(currentRoom.getNeighborNames()[i]);
            if (i < currentRoom.getNeighborNames().length - 1) {
                System.out.print(" or ");
            } else {
                System.out.println();
            }
        }
    }
}
