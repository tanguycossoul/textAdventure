import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Main {
//TODO: should the map not be in level?!!!!???

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

        Level l = new Level("level 1");

        // Game loop
        Player player = new Player("Tanguy", "the Tan-est of 'em all", l.getRoom("hall"));
        System.out.println("You are in the " + player.getCurrentRoom().getName());

        String response;
        Scanner in = new Scanner(System.in);
        boolean quit = false;
        do {
            if (player.getCurrentRoom().getNeighborNames() == null) {
                System.out.println("You're stuck and die there. The end.");
                quit = true;
            }

            else {
                System.out.println("\nWhat do you want to do?");
                response = in.nextLine();
//                System.out.println("SIMULATED RESPONSE: look"); response = "look"; // DEBUG
//                player.addItem( new Item("calculator") ); // DEBUG
                String[] words = response.split(" ");

                if (words[0].equals("go")) {
                    if (words.length < 2) {
                        displayHelp();
                    } else {
                        Level.Room destRoom = player.getCurrentRoom().getNeighbor(words[1]);
                        if (destRoom == null) {
                            System.out.println("You can't go to " + words[1] + ". Try again.");
                        } else {
                            player.moveToRoom( destRoom );
                            System.out.println("You are now in the " + player.getCurrentRoom().getName());
                        }
                    }
                }

                else if (words[0].equals("look")) {
                    System.out.println("You are in the " + player.getCurrentRoom().getName() + ". You can go to: ");
                    for (String name : player.getCurrentRoom().getNeighborNames() ) {
                        System.out.println("  - " + name + " (" + l.getRoom(name).getDescription() + ")");
                    }
                    if ( l.getCreatureNames( player.getCurrentRoom() ).length() != 0) {
                        System.out.println("Creatures in the room: " + l.getCreatureNames( player.getCurrentRoom() ));
                    }
                    if (player.getCurrentRoom().getItems().size() != 0) {
                        System.out.println("Items in the room: " + player.getCurrentRoom().displayItems());
                    }
                    if (player.getItems().size() != 0) {
                        System.out.print("You have: ");
                        player.displayInventory();
                    }
                }
                //TODO: add addedge() method to
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
                            System.out.print("Hmm. Coulnd't remove " + words[1] + ". The room contains: " + player.getCurrentRoom().displayItems());
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
                            System.out.println("The " + player.getCurrentRoom().getName() + " now contains: " + player.getCurrentRoom().displayItems());
                        }
                    }
                }

                else if (words[0].equals("quit")) {
                    quit = true;
                } else {
                    displayHelp();
                }

                // Creatures
                l.updateAllCreatures();
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