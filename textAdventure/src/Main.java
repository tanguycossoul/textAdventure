import java.util.Scanner;

public class Main {

    private static String[] rooms = {"hall", "bedroom", "closet", "dungeon", "kitchen", "trap"};
    private static String[] descr = {"where you walk", "where you sleep", "where you put your stuff", "where you code", "where you cook", "where you lock your brother"};

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
        for (int i = 0; i < rooms.length; i++) {
            g.addNode(rooms[i], descr[i]);
        }
        g.addEdge("hall", "bedroom");
        g.addEdgeBidir("hall", "closet");
        g.addEdge("bedroom", "dungeon");
        g.addEdge("dungeon", "kitchen");
        g.addEdge("dungeon", "trap");
        g.addEdge("kitchen", "hall");

        // Game loop
        Graph.Node currentRoom = g.getNode("hall");

        String response = "";
        Scanner in = new Scanner(System.in);
        Boolean quit = false;
        do {
            if (currentRoom.getNeighborNames() == null) {
                System.out.println("You're stuck and die there. The end.");
                quit = true;
            } else {
                System.out.println("\nWhat do you want to do?");
                response = in.nextLine();
                String[] words = response.split(" ");

                if (words[0].equals("go")) {
                    if (words.length < 2) {
                        displayHelp();
                    } else {
                        Graph.Node destRoom = currentRoom.getNeighbor(words[1]);
                        if (destRoom == null) {
                            System.out.println("You can't go to " + words[1] + ". Try again.");
                        } else {
                            currentRoom = destRoom;
                        }
                        System.out.println("You are in the " + currentRoom.getName());
                    }
                }

                else if (words[0].equals("look")) {
                    System.out.println("You are in the " + currentRoom.getName() + ". You can go to: ");
                    for (String name : currentRoom.getNeighborNames() ) {
                        System.out.println("- " + name + " (" + g.getNode(name).getDescription() + ")");
                    }
                }

                else if (words[0].equals("add")) {
                    if (words.length < 3) {
                        displayHelp();
                    } else if (g.getNode(words[1]) != null) {
                        System.out.println("Hmm. A room called " + words[1] + " already exists. Try another name.");
                    } else {
                        String description = "";
                        for (int i = 2; i < words.length; i++) {
                            description += words[i] + " ";
                        }
                        g.addNode(words[1], description);
                        g.addEdgeBidir(currentRoom, g.getNode(words[1]));
                    }
                } else if (words[0].equals("quit")) {
                    quit = true;
                } else {
                    displayHelp();
                }
            }
        } while (!quit);
    }

    private static void displayHelp() {
        System.out.println("Hmm, I didn't get that. You can ask:");
        System.out.println("  \"go <roomname>\" to go to a room");
        System.out.println("  \"look\" to display the neighbors");
        System.out.println("  \"add <room> <description>\" to add a room");
        System.out.println("  \"quit\" to leave the game");
    }
}