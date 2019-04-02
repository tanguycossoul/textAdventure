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

        Level l = new Level();
        for (int i = 0; i < rooms.length; i++) {
            l.addRoom(rooms[i], descr[i]);
        }
        l.addEdge("hall", "bedroom");
        l.addEdgeBidir("hall", "closet");
        l.addEdge("bedroom", "dungeon");
        l.addEdge("dungeon", "kitchen");
        l.addEdge("dungeon", "trap");
        l.addEdge("kitchen", "hall");

        // Add room items
        l.getRoom("hall").addItem("knife");
        l.getRoom("hall").addItem("map");

        // Game loop
        Player player = new Player("Tanguy", "the Tan-est of 'em all");
        player.setCurrentRoom( l.getRoom("hall") );
        System.out.println("You are in the " + player.getCurrentRoom().getName());

        String response = "";
        Scanner in = new Scanner(System.in);
        Boolean quit = false;
        do {
            if (player.getCurrentRoom().getNeighborNames() == null) {
                System.out.println("You're stuck and die there. The end.");
                quit = true;
            }
            else {
                System.out.println("\nWhat do you want to do?");
                response = in.nextLine();
                String[] words = response.split(" ");

                if (words[0].equals("go")) {
                    if (words.length < 2) {
                        displayHelp();
                    } else {
                        Level.Room destRoom = player.getCurrentRoom().getNeighbor(words[1]);
                        if (destRoom == null) {
                            System.out.println("You can't go to " + words[1] + ". Try again.");
                        } else {
                            player.setCurrentRoom( destRoom );
                            System.out.println("You are now in the " + player.getCurrentRoom().getName());
                        }
                    }
                }

                else if (words[0].equals("look")) {
                    System.out.println("You are in the " + player.getCurrentRoom().getName() + ". You can go to: ");
                    for (String name : player.getCurrentRoom().getNeighborNames() ) {
                        System.out.println("- " + name + " (" + l.getRoom(name).getDescription() + ")");
                    }
                    if (player.getCurrentRoom().getItems().size() != 0) {
                        System.out.print("The room contains: " + player.getCurrentRoom().displayItems());
                    }
                    if (player.getItems().size() != 0) {
                        System.out.print("You have: ");
                        player.displayInventory();
                    }
                }

                else if (words[0].equals("add")) {
                    if (words.length < 3) {
                        displayHelp();
                    } else if (l.getRoom(words[1]) != null) {
                        System.out.println("Hmm. A room called " + words[1] + " already exists. Try another name.");
                    } else {
                        String description = "";
                        for (int i = 2; i < words.length; i++) {
                            description += words[i] + " ";
                        }
                        l.addRoom(words[1], description);
                        l.addEdgeBidir(player.getCurrentRoom(), l.getRoom(words[1]));
                        System.out.println("You can now go to: ");
                        for (String name : player.getCurrentRoom().getNeighborNames() ) {
                            System.out.println("- " + name + " (" + l.getRoom(name).getDescription() + ")");
                        }
                    }
                }

                else if (words[0].equals("take")) {
                    if (words.length < 2) {
                        displayHelp();
                    } else {
                        Item item = player.getCurrentRoom().removeItem( words[1] );
                        if (item == null) {
                            System.out.print("Hmm. Coulnd't remove " + words[1] + ". Here's what the room contains: " + player.getCurrentRoom().displayItems());
                        } else {
                            player.addItem( item );
                            player.getCurrentRoom().removeItem( item.getName() );
                            System.out.print("You now have: ");
                            player.displayInventory();
                        }
                    }
                }

                else if (words[0].equals("drop")) {
                    if (words.length < 2) {
                        displayHelp();
                    } else {
                        Item item = player.removeItem( words[1] );
                        if (item == null) {
                            System.out.print("Hmm. Coulnd't remove " + words[1] + ". Here's what you have: ");
                            player.displayInventory();
                        } else {
                            player.getCurrentRoom().addItem( item );
                            player.removeItem( item.getName() );
                            System.out.print("The " + player.getCurrentRoom().getName() + " know contains: " + player.getCurrentRoom().displayItems());
                        }
                    }
                }

                else if (words[0].equals("quit")) {
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