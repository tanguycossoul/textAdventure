import java.util.ArrayList;

public class Player {

    private String name;
    private String description;
    private ArrayList<Item> items = new ArrayList<>();
    private Level.Room currentRoom;

    public Player(String name, String description, Level.Room room) {
        this.name = name;
        this.description = description;
        setCurrentRoom( room );
        room.incNumPlayers();
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public Item removeItem( String name ) { // remove item and return it
        for (Item item : items) {
            if (item.getName().equals( name )) {
                items.remove( item );
                return item;
            }
        }
        return null;
    }

    public boolean destroyItem( String name ) { // remove item without returning it
        return removeItem(name) != null;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void displayInventory() { // display items you’re carrying
        if (items == null) {
            System.out.println("none");
        } else {
            for (int i = 0; i < items.size(); i++) {
                System.out.print(items.get(i).getName());
                if (i < items.size() - 1) {
                    System.out.print(", ");
                } else {
                    System.out.println();
                }
            }
        }
    }

    public Level.Room getCurrentRoom() { // return room player is currently in
        return this.currentRoom;
    }

    public void setCurrentRoom(Level.Room newroom) { // set player’s current room to a new room
        this.currentRoom = newroom;
    }

    public boolean moveToRoom(Level.Room next) { // try and move to neighboring room with given name
        if (next == null) { return false; }
        currentRoom.decNumPlayers();
        setCurrentRoom( next );
        currentRoom.incNumPlayers();
        return true;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
