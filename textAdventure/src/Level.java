import java.util.ArrayList;
import java.util.HashMap;

public class Level {

    public static Level self = new Level();
    private static HashMap<String, Room> rooms = new HashMap<String, Room>();

    public Room getRoom(String name) {
        return rooms.get(name);
    }

    public void addRoom(String name, String description) {
        Room n = new Room(name, description);
        rooms.put(n.getName(), n);
    }

    public void addEdge(Room room1, Room room2) {
        room1.addNeighbor(room2);
//        System.out.println("INFO: connecting " + room1.getName() + " to " + room2.getName());
    }

    public void addEdge(String name1, String name2) {
        getRoom(name1).addNeighbor( getRoom(name2) );
//        System.out.println("INFO: connecting " + name1 + " to " + name2);
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


    //------------------------------------------------------------------------------------------------------------------
    public class Room {
        private String name;
        private String description;
        private ArrayList<Item> items;
        private HashMap<String, Room> neighbors;

        private Room(String name, String description) {
            this.name = name;
            this.description = description;
            neighbors = new HashMap<String, Room>();
            items = new ArrayList<>();
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
            if (n == null) { return; }
            neighbors.put( n.getName(), n);
        }

        private void addNeighbor(String name) {
            Room n = rooms.get(name);
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

        public Room getNeighbor(String name) {
            return neighbors.get( name );
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
            items.add( item );
        }

        public void addItem(String name, String description) {
            Item item = new Item(name, description);
            items.add( item );
        }

        // Note: allowing to have duplicate items
        public void addItem(Item item) {
            items.add(item);
        }

        public Item removeItem(String name) {
            for (Item item : items) {
                if (item.getName().equals( name )) {
                    destroyItem( item );
                    return item;
                }
            }
            return null;
        }

        public boolean destroyItem(Item item) {
            return items.remove(item);
        }

    }
}
