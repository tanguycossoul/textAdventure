import java.util.ArrayList;
import java.util.HashMap;

// TODO: move level-1 specifics into a sub-class?

/* Rooms connectivity matrix:
    hall    bedroom closet  dungeon kitchen trap
    hall        0       1       1       0       0       0
    bedroom     0       0       0       1       0       0
    closet      1       0       0       0       0       0
    dungeon     0       0       0       0       1       1
    kitchen     1       0       0       0       0       0
    trap        0       0       0       0       0       0
 */

public class Level {
    private String name;

    private ArrayList<Player> players = new ArrayList<>();
    private ArrayList<Creature> creatures = new ArrayList<>();
    private static HashMap<String, Room> rooms = new HashMap<String, Room>();

    private static String[] room_names = {"hall", "bedroom", "closet", "dungeon", "kitchen", "trap"};
    private static String[] room_descr = {"where you walk", "where you sleep", "where you put your stuff", "where you code", "where you cook", "where you lock your brother"};

    public Level(String name) {
        this.name = name;
        for (int i = 0; i < room_names.length; i++) {
            addRoom(room_names[i], room_descr[i]);
        }

        addEdge("hall", "bedroom");
        addEdgeBidir("hall", "closet");
        addEdge("bedroom", "dungeon");
        addEdge("dungeon", "kitchen");
        addEdge("dungeon", "trap");
        addEdge("kitchen", "hall");

        // Add room items
        getRoom("hall").addItem("knife");
        getRoom("hall").addItem("map");

        // Add creatures
        addCreature("chicken", rooms.get("hall") );
        addCreature("wumpus", rooms.get("bedroom") );
    }

    // Rooms
    public Room getRoom(String name) {
        return rooms.get(name);
    }

    public void addRoom(String name, String description) {
        Room n = new Room(name, description);
        rooms.put(n.getName(), n);
    }

    public void addEdge(Room room1, Room room2) {
        room1.addNeighbor(room2);
    }

    public void addEdge(String name1, String name2) {
        getRoom(name1).addNeighbor( getRoom(name2) );
    }

    public void addEdgeBidir(Room room1, Room room2) {
        room1.addNeighbor(room2);
        room2.addNeighbor(room1);
//        System.out.println("INFO: connecting " + room1.getName() + " to " + room2.getName());
//        System.out.println("INFO: connecting " + room2.getName() + " to " + room1.getName());
    }

    public void addEdgeBidir(String name1, String name2) {
        getRoom(name1).addNeighbor( getRoom(name2) );
        getRoom(name2).addNeighbor( getRoom(name1) );
//        System.out.println("INFO: connecting " + name1 + " to " + name2);
//        System.out.println("INFO: connecting " + name2 + " to " + name1);
    }

    // Creatures
    public ArrayList<Creature> getCreatures(Room room) {
        ArrayList<Creature> output = new ArrayList<>();
        for (Creature c: creatures) {
            if ( c.getCurrentRoom().equals( room )) {
                output.add( c );
            }
        }
        return output;
    }

    public String getCreatureNames(Room room) {
        String output = "";
        for (Creature c : getCreatures( room )) {
            output += c.getName() + " ";
        }
        return output;
    }

    public Creature removeCreature(Creature c) {
        if (c == null) { return null; }
        creatures.remove( c );
        return c;
    }

    public Creature removeCreature(String name, Room room) {
        for (Creature c : creatures) {
            if (c.getName().equals( name ) && c.getCurrentRoom().equals( room )) {
                creatures.remove( c );
                return c;
            }
        }
        System.out.println("WARNING: couldn't remove " + name + " in room " + room.getName());
        return null;
    }

    public void addCreature(String name, Room room) {
        Creature c;
        if (name.toLowerCase().equals("chicken"))      { c = new Chicken( room ); }
        else if (name.toLowerCase().equals("wumpus"))  { c = new Wumpus( room ); }
        else if (name.toLowerCase().equals("popstar")) { c = new Popstar( room ); }
        else {
            System.out.println("WARNING: cannot create creature named: " + name);
            return;
        }
        c.setCurrentRoom( room );
        creatures.add( c );
    }

    public void updateAllCreatures() {
        for (Creature c : creatures) {
            c.act();
        }
    }


    //------------------------------------------------------------------------------------------------------------------
    public class Room {
        private String name;
        private String description;

        private ArrayList<Item> items = new ArrayList<>();
        private ArrayList<Creature> creatures = new ArrayList<>();
        private HashMap<String, Room> neighbors = new HashMap<>();
        private ArrayList<Player> players = new ArrayList<>();

        private Room(String name, String description) {
            this.name = name;
            this.description = description;
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

        private void addNeighbor(Room n) {
            if (n == null) {
                return;
            }
            neighbors.put(n.getName(), n);
        }

        private void addNeighbor(String name) {
            Room n = rooms.get(name);
            if (n == null) {
                return;
            }
            neighbors.put(name, n);
        }

        public String[] getNeighborNames() {
            if (neighbors.size() == 0) {
                return null;
            }

            String[] output = new String[ neighbors.size() ];
            int i = 0;
            for (String name : neighbors.keySet()) {
                output[i++] = neighbors.get(name).getName();
            }
            return output;
        }

        public Room getNeighbor(String name) {
            return neighbors.get(name);
        }

        public ArrayList<Room> getNeighbors() {
            return new ArrayList<>( neighbors.values() );
        }

        public ArrayList<Item> getItems() {
            return items;
        }

        public String displayItems() {
            String output = "";
            if (items == null) {
                return "";
            } else {
                for (int i = 0; i < items.size(); i++) {
                    output += items.get(i).getName();
                    if (i < items.size() - 1) {
                        output += ", ";
                    }
                }
            }
            return output;
        }

        public void addItem(String name) {
            Item item = new Item(name, "");
            items.add(item);
        }

        public void addItem(String name, String description) {
            Item item = new Item(name, description);
            items.add(item);
        }

        // Note: allowing duplicate items
        public void addItem(Item item) {
            items.add(item);
        }

        public Item removeItem(String name) {
            for (Item item : items) {
                if (item.getName().equals(name)) {
                    destroyItem(item);
                    return item;
                }
            }
            return null;
        }

        public boolean destroyItem(Item item) {
            return items.remove(item);
        }

        public Player getRandPlayer() {
            if (players.size() == 0) { return null; }
            return players.get(players.size() * (int) Math.random());
        }

        public int getNumPlayers() {
            return players.size();
        }

        public void addPlayer( Player p ) {
            if (!players.contains( p )) {
                players.add( p );
            };
        }

        public void removePlayer( Player p ) {
            if (players.contains( p )) {
                players.remove( p );
            };
        }

        public boolean hasPlayer() {
            return (players.size() > 0);
        }
    }
}
